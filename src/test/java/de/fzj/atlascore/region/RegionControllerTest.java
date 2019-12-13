package de.fzj.atlascore.region;

import de.fzj.atlascore.region.entity.Region;
import de.fzj.atlascore.region.entity.RegionBuilder;
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
public class RegionControllerTest {

    private static final String REF_SPACE_NAME = "bigbrain";
    private static final String INVALID_REF_SPACE_NAME = "smallbrain";
    private static final String PARCELLATION_NAME = "par-a";
    private static final String INVALID_PARCELLATION_NAME = "par-b";
    private static final String REGION_NAME_A = "region-a";
    private static final String REGION_NAME_B = "region-b";
    private static final Region REGION_A = RegionBuilder.aRegion().withName(REGION_NAME_A).build();
    private static final Region REGION_B = RegionBuilder.aRegion().withName(REGION_NAME_B).build();

    private static final List<Region> REGIONS = Arrays.asList(
            REGION_A, REGION_B
    );

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegionService regionService;

    @Before
    public void setUp() {
        when(regionService.getAllRegions(REF_SPACE_NAME, PARCELLATION_NAME)).thenReturn(REGIONS);
        when(regionService.getRegionByName(REF_SPACE_NAME, PARCELLATION_NAME, REGION_NAME_A)).thenReturn(REGION_A);
    }

    @Test
    public void shouldReturnAllRegions() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/referencespaces/" + REF_SPACE_NAME + "/parcellations/" + PARCELLATION_NAME + "/regions"))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonResult = new JSONObject(mvcResult.getResponse().getContentAsString());

        assertEquals(2, jsonResult.getJSONObject("_embedded").getJSONArray("regions").length());
    }

    @Test
    public void shouldReturnRegionByName() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/referencespaces/" + REF_SPACE_NAME + "/parcellations/" + PARCELLATION_NAME + "/regions/" + REGION_NAME_A))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonResult = new JSONObject(mvcResult.getResponse().getContentAsString());

        assertEquals(REGION_NAME_A, jsonResult.getJSONObject("region").get("name").toString());
    }

    @Test
    public void shouldReturnNotFoundForInvalidRegion() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/referencespaces/" + REF_SPACE_NAME + "/parcellations/" + PARCELLATION_NAME + "/regions/" + REGION_NAME_B))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(
                String.format("Region: %s not found in parcellation %s", REGION_NAME_B, PARCELLATION_NAME),
                mvcResult.getResponse().getErrorMessage()
        );
    }
}
