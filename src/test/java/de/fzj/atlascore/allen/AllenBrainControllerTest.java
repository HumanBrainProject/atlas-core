package de.fzj.atlascore.allen;

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
public class AllenBrainControllerTest {

    private static final String STRUCTURE_RESPONSE = "STRUCTURE_RESPONSE";
    private static final String ALLEN_STRUCTURE_URL = "/allen/structure";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AllenBrainService allenBrainService;

    @Before
    public void setUp() {
        doReturn(STRUCTURE_RESPONSE).when(allenBrainService).getOntologyStructure();
    }

    @Test
    public void shouldGetAllSpecies() throws Exception {
        MvcResult result = mockMvc.perform(get(ALLEN_STRUCTURE_URL)).andExpect(status().isOk()).andReturn();

        assertEquals(STRUCTURE_RESPONSE, result.getResponse().getContentAsString());
    }
}