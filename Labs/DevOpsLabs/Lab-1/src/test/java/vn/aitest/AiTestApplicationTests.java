package vn.aitest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import vn.aitest.item.AiModel;
import vn.aitest.item.AiModelRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class AccessTests {

    @Autowired private MockMvc mockMvc;
    @Autowired private AiModelRepository repo;

    private static final String JSON = """
        { "aiName": "TestAI", "strengths": "test" }
        """;

    private String testId;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        AiModel model = new AiModel();
        model.setAiName("Grok");
        model.setStrengths("truth");
        testId = repo.save(model).getId();
    }

    // === GET ALL ===
    @Test @WithUserDetails("user")       void userCanGetAll()       throws Exception { mockMvc.perform(get("/ai-models")).andExpect(status().isOk()); }
    @Test @WithUserDetails("publisher") void pubCanGetAll()        throws Exception { mockMvc.perform(get("/ai-models")).andExpect(status().isOk()); }
    @Test @WithUserDetails("admin")     void adminCanGetAll()      throws Exception { mockMvc.perform(get("/ai-models")).andExpect(status().isOk()); }

    // === GET ONE ===
    @Test @WithUserDetails("user")       void userCannotGetOne()    throws Exception { mockMvc.perform(get("/ai-models/" + testId)).andExpect(status().isForbidden()); }
    @Test @WithUserDetails("publisher") void pubCannotGetOne()     throws Exception { mockMvc.perform(get("/ai-models/" + testId)).andExpect(status().isForbidden()); }
    @Test @WithUserDetails("admin")     void adminCanGetOne()      throws Exception { mockMvc.perform(get("/ai-models/" + testId)).andExpect(status().isOk()); }

    // === POST ===
    @Test @WithUserDetails("user")       void userCannotPost()      throws Exception { mockMvc.perform(post("/ai-models").contentType(MediaType.APPLICATION_JSON).content(JSON)).andExpect(status().isForbidden()); }
    @Test @WithUserDetails("publisher") void pubCanPost()          throws Exception { mockMvc.perform(post("/ai-models").contentType(MediaType.APPLICATION_JSON).content(JSON)).andExpect(status().isOk()); }
    @Test @WithUserDetails("admin")     void adminCanPost()        throws Exception { mockMvc.perform(post("/ai-models").contentType(MediaType.APPLICATION_JSON).content(JSON)).andExpect(status().isOk()); }

    // === PUT ===
    @Test @WithUserDetails("user")       void userCannotPut()       throws Exception { mockMvc.perform(put("/ai-models/" + testId).contentType(MediaType.APPLICATION_JSON).content(JSON)).andExpect(status().isForbidden()); }
    @Test @WithUserDetails("publisher") void pubCannotPut()        throws Exception { mockMvc.perform(put("/ai-models/" + testId).contentType(MediaType.APPLICATION_JSON).content(JSON)).andExpect(status().isForbidden()); }
    @Test @WithUserDetails("admin")     void adminCanPut()         throws Exception { mockMvc.perform(put("/ai-models/" + testId).contentType(MediaType.APPLICATION_JSON).content(JSON)).andExpect(status().isOk()); }

    // === DELETE ===
    @Test @WithUserDetails("user")       void userCannotDelete()    throws Exception { mockMvc.perform(delete("/ai-models/" + testId)).andExpect(status().isForbidden()); }
    @Test @WithUserDetails("publisher") void pubCannotDelete()     throws Exception { mockMvc.perform(delete("/ai-models/" + testId)).andExpect(status().isForbidden()); }
    @Test @WithUserDetails("admin")     void adminCanDelete()      throws Exception { mockMvc.perform(delete("/ai-models/" + testId)).andExpect(status().isOk()); }
}