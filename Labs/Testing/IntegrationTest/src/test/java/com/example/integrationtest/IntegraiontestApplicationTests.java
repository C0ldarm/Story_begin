package com.example.integrationtest;

import com.example.integrationtest.model.Item;
import com.example.integrationtest.request.ItemCreateRequest;
import com.example.integrationtest.request.ItemUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Item item;
    private ItemCreateRequest createRequest;
    private ItemUpdateRequest updateRequest;

    @BeforeEach
    void setUp() {
        item = Item.builder()
                .id("1")
                .name("Freddie Mercury")
                .code("Queen")
                .description("vocal, piano")
                .createDate(LocalDateTime.now())
                .updateDate(new ArrayList<>())
                .build();

        createRequest = new ItemCreateRequest("John Lennon", "Beatles", "guitar, vocal");
        updateRequest = new ItemUpdateRequest("1", "Freddie Mercury Updated", "Queen", "vocal, piano");
    }

    
    @Test
    void givenInvalidId_whenGetById_thenReturnNotFound() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/items/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenValidCreateRequest_whenCreate_thenReturnCreatedItem() throws Exception {
        // Given
        String requestJson = objectMapper.writeValueAsString(createRequest);

        // When & Then
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Lennon"))
                .andExpect(jsonPath("$.code").value("Beatles"))
                .andExpect(jsonPath("$.description").value("guitar, vocal"));
    }

    @Test
    void givenCreateRequestWithNullFields_whenCreate_thenReturnCreatedItem() throws Exception {
        // Given
        ItemCreateRequest nullRequest = new ItemCreateRequest(null, null, null);
        String requestJson = objectMapper.writeValueAsString(nullRequest);

        // When & Then
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").doesNotExist())
                .andExpect(jsonPath("$.code").doesNotExist())
                .andExpect(jsonPath("$.description").doesNotExist());
    }

    @Test
    void givenInvalidJson_whenCreate_thenReturnBadRequest() throws Exception {
        // Given
        String invalidJson = "{";

        // When & Then
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenInvalidUpdateRequest_whenUpdate_thenReturnNotFound() throws Exception {
        // Given
        ItemUpdateRequest invalidRequest = new ItemUpdateRequest("999", "Invalid", "Unknown", "Unknown");
        String requestJson = objectMapper.writeValueAsString(invalidRequest);

        // When & Then
        mockMvc.perform(put("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenUpdateRequestWithNullFields_whenUpdate_thenReturnUpdatedItem() throws Exception {
        // Given
        ItemUpdateRequest nullRequest = new ItemUpdateRequest("1", null, null, null);
        String requestJson = objectMapper.writeValueAsString(nullRequest);

        // When & Then
        mockMvc.perform(put("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").doesNotExist());
    }

    @Test
    void givenValidId_whenDelete_thenReturnNoContent() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void givenInvalidId_whenDelete_thenReturnNoContent() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/items/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void givenNewItemCreated_whenGetAll_thenIncludeNewItem() throws Exception {
        // Given
        String requestJson = objectMapper.writeValueAsString(createRequest);
        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        // When & Then
        mockMvc.perform(get("/api/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name == 'John Lennon')]").exists());
    }

    @Test
    void givenEmptyFieldsCreateRequest_whenCreate_thenReturnCreatedItem() throws Exception {
        // Given
        ItemCreateRequest emptyRequest = new ItemCreateRequest("", "", "");
        String requestJson = objectMapper.writeValueAsString(emptyRequest);

        // When & Then
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(""))
                .andExpect(jsonPath("$.code").value(""))
                .andExpect(jsonPath("$.description").value(""));
    }

    @Test
    void givenSameDataUpdateRequest_whenUpdate_thenReturnUpdatedItem() throws Exception {
        // Given
        ItemUpdateRequest sameDataRequest = new ItemUpdateRequest("1", "Freddie Mercury", "Queen", "vocal, piano");
        String requestJson = objectMapper.writeValueAsString(sameDataRequest);

        // When & Then
        mockMvc.perform(put("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Freddie Mercury"));
    }

    @Test
    void givenItemUpdated_whenGetById_thenReturnUpdatedItem() throws Exception {
        // Given
        String requestJson = objectMapper.writeValueAsString(updateRequest);
        mockMvc.perform(put("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        // When & Then
        mockMvc.perform(get("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Freddie Mercury Updated"));
    }

    @Test
    void givenMultipleCreateRequests_whenCreate_thenAllItemsSaved() throws Exception {
        // Given
        ItemCreateRequest request1 = new ItemCreateRequest("David Bowie", "Ziggy Stardust", "vocal");
        ItemCreateRequest request2 = new ItemCreateRequest("Jimi Hendrix", "Experience", "guitar");
        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request1)));
        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request2)));

        // When & Then
        mockMvc.perform(get("/api/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name == 'David Bowie')]").exists())
                .andExpect(jsonPath("$[?(@.name == 'Jimi Hendrix')]").exists());
    }

    @Test
    void givenInvalidJsonUpdateRequest_whenUpdate_thenReturnBadRequest() throws Exception {
        // Given
        String invalidJson = "{";

        // When & Then
        mockMvc.perform(put("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenItemCreatedAndUpdated_whenGetById_thenDatesAreSet() throws Exception {
        // Given
        String createJson = objectMapper.writeValueAsString(createRequest);
        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson));

        String updateJson = objectMapper.writeValueAsString(new ItemUpdateRequest("1", "John Lennon Updated", "Beatles", "guitar, vocal"));
        mockMvc.perform(put("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson));

        // When & Then
        mockMvc.perform(get("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.createDate").exists())
                .andExpect(jsonPath("$.updateDate").isArray())
                .andExpect(jsonPath("$.updateDate").isNotEmpty());
    }
}