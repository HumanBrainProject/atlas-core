package de.fzj.atlascore.referencespace;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReferencespaceControllerTest {

    private static final HashMap<String, Object> PROPERTIES = new HashMap<>() {{
        put("key1", "value1");
        put("key2", "value2");
    }};
    private static final String BIGBRAIN = "bigbrain";
    private static final String COLIN = "colin";
    private static final String INVALID_SPACE = "invalid";
    private static final Referencespace REFERENCESPACE_BIGBRAIN = new Referencespace(PROPERTIES);
    private static final Referencespace REFERENCESPACE_COLIN = new Referencespace(PROPERTIES);

    private static final List<Referencespace> REFERENCESPACES = Arrays.asList(
            REFERENCESPACE_BIGBRAIN, REFERENCESPACE_COLIN
    );

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReferencespaceService referencespaceService;

    @Before
    public void setUp() {
        when(referencespaceService.getReferencespaces()).thenReturn(REFERENCESPACES);
        when(referencespaceService.getReferencespaceById(BIGBRAIN)).thenReturn(REFERENCESPACE_BIGBRAIN);
    }

    @Test
    public void shouldReturnAllReferencespaces() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/referencespaces"))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonResult = new JSONObject(mvcResult.getResponse().getContentAsString());

        assertEquals(2, jsonResult.getJSONObject("_embedded").getJSONArray("referencespaces").length());
    }

    @Test
    public void shouldReturnReferencespaceForValidName() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/referencespaces/" + BIGBRAIN))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonResult = new JSONObject(mvcResult.getResponse().getContentAsString());

        assertEquals(BIGBRAIN, jsonResult.getJSONObject("referencespace").get("name").toString());
    }

    @Test
    public void shouldReturnNotFoundForInvalidSpace() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/referencespaces/" + INVALID_SPACE))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals("referencespace: " + INVALID_SPACE + " not found", mvcResult.getResponse().getErrorMessage());
    }
}
