package fr.charly.businessCase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FavoriteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test@gmail.com")
    public void testAddFavoriteSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/favorite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonByData(
                                "ca40490a-7ec4-4e13-89ff-bd2d9e211a2a",
                                "319154c4-7c0a-4f13-8a2c-4d21af2bf49c"
                        )));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isBoolean());
    }

    private String getJsonByData(String chargingStationId, String userId) {
        return "{\n" +
                "  \"chargingStationUuid\": \""+chargingStationId+"\",\n" +
                "  \"userUuid\": \""+userId+"\"\n" +
                "}";
    }

}
