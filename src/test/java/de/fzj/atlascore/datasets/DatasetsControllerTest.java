package de.fzj.atlascore.datasets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.fzj.atlascore.knowledgegraph.KnowledgeGraphService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DatasetsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KnowledgeGraphService knowledgeGraphService;

    @Before
    public void setUp() {
        when(knowledgeGraphService.getRegionsDatasets(anyString())).thenReturn(new ArrayList());
        when(knowledgeGraphService.getParcellationDatasets(anyString())).thenReturn(new ArrayList());
        when(knowledgeGraphService.getReferencespaceDatasets(anyString())).thenReturn(new ArrayList());
        when(knowledgeGraphService.getAllDatasets(anyString(), anyString(), anyString())).thenReturn(new ArrayList());
        when(knowledgeGraphService.getRegionsFilteredByList(any())).thenReturn(new ArrayList());
    }

    @Test
    public void shouldReturnDatasetsForReferencespaces() throws Exception {
        mockMvc
                .perform(get("/datasets/referencespaces"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnDatasetsForParcellations() throws Exception {
        mockMvc
                .perform(get("/datasets/parcellations"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnDatasetsForRegions() throws Exception {
        mockMvc
                .perform(get("/datasets/regions"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnDatasets() throws Exception {
        mockMvc
                .perform(get("/datasets"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnFilteredDatasetsForRegion() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(Collections.singletonList("REGION_ID"));
        mockMvc
                .perform(post("/datasets/regions")
                        .content(requestJson)
                        .contentType("application/json;charset=UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestForNoInput() throws Exception {
        mockMvc
                .perform(post("/datasets/regions"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
