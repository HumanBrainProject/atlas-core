package de.fzj.atlascore.region;

import de.fzj.atlascore.region.entity.Region;
import de.fzj.atlascore.region.entity.RegionBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class RegionResourceTest {

    public static final String BIGBRAIN_REFERENCESPACE = "bigbrain";
    public static final String PARCELLATION_NAME = "par1";
    public static final String REGION_1 = "region1";
    public static final String REGION_2 = "region2";

    private String refSpaceName;
    private String parcellationName;
    private String regionName;

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] {
                {BIGBRAIN_REFERENCESPACE, PARCELLATION_NAME, REGION_1},
                {BIGBRAIN_REFERENCESPACE, PARCELLATION_NAME, REGION_2},
        });
    }

    public RegionResourceTest(String refSpaceName, String parcellationName, String regionName) {
        this.refSpaceName = refSpaceName;
        this.parcellationName = parcellationName;
        this.regionName = regionName;
    }

    @Test
    public void shouldCreateResourceAndAddLink() {
        Region region = RegionBuilder.aRegion().withName(regionName).build();
        RegionResource regionResource = new RegionResource(region,  refSpaceName, parcellationName);

        assertEquals(regionName, regionResource.getRegion().getName());
        assertEquals(
                "/referencespaces/" + refSpaceName + "/parcellations/" + parcellationName + "/regions/" + regionName,
                regionResource.getLink("self").getHref()
        );
        assertThat(regionResource.getLinks(), hasSize(1));
    }
}
