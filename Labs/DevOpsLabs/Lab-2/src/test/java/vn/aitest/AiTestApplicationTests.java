package vn.aitest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import vn.aitest.item.AiModel;
import vn.aitest.item.AiModelRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class AccessTests {

    // ====================== CONSTANTS ======================
    private static final String BASE_URL = "/ai-models";

    private static final String JSON = """
            { "aiName": "TestAI", "strengths": "test" }
            """;

    private static final String USER = "user";
    private static final String PUBLISHER = "publisher";
    private static final String ADMIN = "admin";

    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AiModelRepository repo;

    private String testId;

    @DynamicPropertySource
    static void setMongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        AiModel model = new AiModel("Grok", "truth");
        testId = repo.save(model).getId();
    }

    // ====================== GET ALL ======================
    @Test
    @WithUserDetails(USER)
    void userCanGetAll() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(PUBLISHER)
    void pubCanGetAll() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(ADMIN)
    void adminCanGetAll() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    // ====================== GET ONE ======================
    @Test
    @WithUserDetails(USER)
    void userCannotGetOne() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + testId))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(PUBLISHER)
    void pubCannotGetOne() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + testId))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(ADMIN)
    void adminCanGetOne() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + testId))
                .andExpect(status().isOk());
    }

    // ====================== POST ======================
    @Test
    @WithUserDetails(USER)
    void userCannotPost() throws Exception {
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(PUBLISHER)
    void pubCanPost() throws Exception {
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(ADMIN)
    void adminCanPost() throws Exception {
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON))
                .andExpect(status().isOk());
    }

    // ====================== PUT ======================
    @Test
    @WithUserDetails(USER)
    void userCannotPut() throws Exception {
        mockMvc.perform(put(BASE_URL + "/" + testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(PUBLISHER)
    void pubCannotPut() throws Exception {
        mockMvc.perform(put(BASE_URL + "/" + testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(ADMIN)
    void adminCanPut() throws Exception {
        mockMvc.perform(put(BASE_URL + "/" + testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON))
                .andExpect(status().isOk());
    }

    // ====================== DELETE ======================
    @Test
    @WithUserDetails(USER)
    void userCannotDelete() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/" + testId))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(PUBLISHER)
    void pubCannotDelete() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/" + testId))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(ADMIN)
    void adminCanDelete() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/" + testId))
                .andExpect(status().isOk());
    }
}