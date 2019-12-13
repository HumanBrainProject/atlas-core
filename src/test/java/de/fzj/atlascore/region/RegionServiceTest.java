package de.fzj.atlascore.region;

import de.fzj.atlascore.region.entity.Region;
import de.fzj.atlascore.region.entity.RegionBuilder;
import de.fzj.atlascore.tvb.TVBDummyDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RegionServiceTest {

    public static final String BIGBRAIN_REFERENCESPACE = "bigbrain";
    public static final String PARCELLATION_NAME = "par1";
    public static final String REGION_1 = "Region 1";

    @Mock
    private TVBDummyDataService tvbDummyDataService;

    @InjectMocks
    private RegionService regionService;

    @Test
    public void shouldReturnDummyDataForTvb() {
        regionService.getAllRegions("tvb", PARCELLATION_NAME);

        verify(tvbDummyDataService).getAllRegions();
    }

    @Test
    public void shouldReturnRegionList() {
        List<Region> allRegions = regionService.getAllRegions(BIGBRAIN_REFERENCESPACE, PARCELLATION_NAME);

        assertThat(allRegions, hasItem(RegionBuilder.aRegion().withName(REGION_1).build()));
        assertThat(allRegions, hasSize(1));
    }

    @Test
    public void shouldReturnRegionForName() {
        Region region = regionService.getRegionByName(BIGBRAIN_REFERENCESPACE, PARCELLATION_NAME, REGION_1);

        assertEquals(RegionBuilder.aRegion().withName(REGION_1).build(), region);
    }
}
