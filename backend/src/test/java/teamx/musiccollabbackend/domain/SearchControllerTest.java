package teamx.musiccollabbackend.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.collection.IsEmptyIterable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import teamx.musiccollabbackend.domain.Search.SearchQueryModel;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void searchingUserReturnsUser() throws Exception {

        SearchQueryModel query = new SearchQueryModel();
        String[] params = {"Cena"};
        query.setQuery(params);
        String cenaEmail = "john@cena.com";

        MvcResult result = mvc.perform( MockMvcRequestBuilders.get("/search/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(query)))
                        .andDo(MockMvcResultHandlers.print())

        .andExpect(jsonPath("$[0].email").value(cenaEmail))
        .andReturn();

        //String content = result.getResponse().getContentAsString(); // This can used for debug, to see the JSON returned by the above
        //System.out.println("JSON Response: " + content);
    }

    @Test
    public void searchingNonExistentUserReturnsEmpty() throws Exception {

        SearchQueryModel query = new SearchQueryModel();
        String[] params = {"Socrates"};
        query.setQuery(params);
        String emptyEmail = "";

        MvcResult result = mvc.perform( MockMvcRequestBuilders.get("/search/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(query)))
                        .andDo(MockMvcResultHandlers.print())

        .andExpect(jsonPath("*").value(IsEmptyIterable.emptyIterable()))
        .andReturn();
    }

    @Test
    public void searchingSampleReturnsSample() throws Exception {

        SearchQueryModel query = new SearchQueryModel();
        String[] params = {"Lofi guitar sample by me"};
        query.setQuery(params);
        String sampleName = "Gates Guitar II";

        MvcResult result = mvc.perform( MockMvcRequestBuilders.get("/search/samples")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(query)))
                        .andDo(MockMvcResultHandlers.print())

        .andExpect(jsonPath("$[0].sampleName").value(sampleName))
        .andReturn();
    }

    @Test
    public void searchingNonExistentSampleReturnsEmpty() throws Exception {

        SearchQueryModel query = new SearchQueryModel();
        String[] params = {"'The Genealogy of Morals' is a book by Nietzsche that deals with the origins of morality."};
        query.setQuery(params);

        MvcResult result = mvc.perform( MockMvcRequestBuilders.get("/search/samples")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(query)))
                .andDo(MockMvcResultHandlers.print())

        .andExpect(jsonPath("*").value(IsEmptyIterable.emptyIterable()))
        .andReturn();
    }

    // Helper method to convert object to JSON string
    private String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
