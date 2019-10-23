package de.fzj.atlascore.knowledgegraph;

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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KnowledgeGraphControllerTest {

    private static final String SPECIES_RETURN = "SPECIES_RETURN";
    private static final String KG_SPECIES_URL = "/kg/species";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KnowledgeGraphService knowledgeGraphService;

    @Before
    public void setUp() {
        doReturn(SPECIES_RETURN).when(knowledgeGraphService).getAllSpecies();
    }

    @Test
    public void shouldGetAllSpecies() throws Exception {
        MvcResult result = mockMvc.perform(get(KG_SPECIES_URL)).andExpect(status().isOk()).andReturn();

        assertEquals(SPECIES_RETURN, result.getResponse().getContentAsString());
    }
}