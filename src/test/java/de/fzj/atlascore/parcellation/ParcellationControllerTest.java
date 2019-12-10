package de.fzj.atlascore.parcellation;

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
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParcellationControllerTest {

    private static final String REF_SPACE_NAME = "bigbrain";
    private static final String INVALID_REF_SPACE_NAME = "smallbrain";
    private static final String PARCELLATION_NAME_A = "par-a";
    private static final String PARCELLATION_NAME_B = "par-b";
    private static final Parcellation PARCELLATION_A = new Parcellation(PARCELLATION_NAME_A);
    private static final Parcellation PARCELLATION_B = new Parcellation(PARCELLATION_NAME_B);

    private static final List<Parcellation> PARCELLATIONS = Arrays.asList(
        PARCELLATION_A, PARCELLATION_B
    );

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParcellationService parcellationService;

    @Before
    public void setUp() {
        when(parcellationService.getParcellations(REF_SPACE_NAME)).thenReturn(PARCELLATIONS);
        when(parcellationService.getParcellationByName(PARCELLATION_NAME_A)).thenReturn(PARCELLATION_A);
    }

    @Test
    public void shouldReturnAllParcellations() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/referencespaces/" + REF_SPACE_NAME + "/parcellations/"))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonResult = new JSONObject(mvcResult.getResponse().getContentAsString());

        assertEquals(2, jsonResult.getJSONObject("_embedded").getJSONArray("parcellations").length());
    }

    @Test
    public void shouldReturnNotFoundIfReferencespaceIsNotKnown() throws Exception {
        when(parcellationService.getParcellations(INVALID_REF_SPACE_NAME)).thenCallRealMethod();
        MvcResult mvcResult = mockMvc
                .perform(get("/referencespaces/" + INVALID_REF_SPACE_NAME + "/parcellations/"))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(
                "Referencespace: " + INVALID_REF_SPACE_NAME + " not found",
                mvcResult.getResponse().getErrorMessage()
        );
    }

    @Test
    public void shouldReturnParcellationForValidName() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/referencespaces/" + REF_SPACE_NAME + "/parcellations/" + PARCELLATION_NAME_A))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonResult = new JSONObject(mvcResult.getResponse().getContentAsString());

        assertEquals(PARCELLATION_NAME_A, jsonResult.getJSONObject("parcellation").get("name").toString());
    }

    @Test
    public void shoulReturnNotFoundForInvalidParcellation() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/referencespaces/" + REF_SPACE_NAME + "/parcellations/" + PARCELLATION_NAME_B))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(
                String.format("Parcellation: %s not found for referencespace: %s", PARCELLATION_NAME_B, REF_SPACE_NAME),
                mvcResult.getResponse().getErrorMessage()
        );
    }



}
