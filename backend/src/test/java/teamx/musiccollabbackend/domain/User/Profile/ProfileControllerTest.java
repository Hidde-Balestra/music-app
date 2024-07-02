package teamx.musiccollabbackend.domain.User.Profile;

import org.hamcrest.core.IsNull;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {

    @Autowired
    MockMvc mvc;

    private final String endpoint = "/profile";

    @Test
    public void searchingNonExistentProfileReturnsEmpty() throws Exception {

        String invalidUserId = "5000";

        mvc.perform( MockMvcRequestBuilders.get(endpoint).param("idUser", invalidUserId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.user").value(IsNull.nullValue()));
    }

    @Test
    public void searchingProfileReturnsProfile() throws Exception {

        String cenaUserId = "91";
        String cenaEmail = "john@cena.com";

        mvc.perform( MockMvcRequestBuilders.get(endpoint).param("idUser", cenaUserId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())

        .andExpect(jsonPath("$.user.email").value(cenaEmail));
    }
}
