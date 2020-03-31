package de.fzj.atlascore.referencespace;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReferencespaceControllerTest {

    private static final HashMap<String, Object> PROPERTIES = new HashMap<>() {{
        put("name", BIGBRAIN);
        put("id", ID);
    }};
    private static final String BIGBRAIN = "bigbrain";
    private static final String ID = "42-1337";
    private static final String INVALID_SPACE = "invalid";
    private static final Referencespace REFERENCESPACE_BIGBRAIN = new Referencespace(PROPERTIES);

    private static final List<Referencespace> REFERENCESPACES = Arrays.asList(
            REFERENCESPACE_BIGBRAIN
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
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResult = mvcResult.getResponse().getContentAsString();

        assertTrue(jsonResult.contains(ID));
    }

    @Test
    public void shouldReturnReferencespaceForValidName() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/referencespaces/" + BIGBRAIN))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResult = mvcResult.getResponse().getContentAsString();

        assertTrue(jsonResult.contains(ID));
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
