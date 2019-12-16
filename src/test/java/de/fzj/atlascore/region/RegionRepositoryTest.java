package de.fzj.atlascore.region;

import de.fzj.atlascore.region.entity.Region;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class RegionRepositoryTest {

    private static final String COLIN = "colin";
    private static final String COLIN_PARCELLATION = "JuBrain Cytoarchitectonic Atlas";
    private static final String COLIN_REGION = "Area 3a (PostCG)";
    private static final String INVALID_REFSPACE = "invalid-referencespace";
    private static final String INVALID_PARCELLATION = "invalid-parcellation";
    private static final String INVALID_REGION = "invalid-region";

    private RegionRepository regionRepository;

    @Before
    public void setUp() {
        regionRepository = new RegionRepository();
    }

    //region === findAllByReferencespaceAndParcellation
    @Test
    public void shouldReturnAllRegionsForValidRefSpaceAndParcellation() {
        List<Region> regions = regionRepository.findAllByReferencespaceAndParcellation(COLIN, COLIN_PARCELLATION);

        assertThat(regions, hasSize(106));
    }

    @Test
    public void shouldReturnEmptyListForInvalidRefSpace() {
        List<Region> regions = regionRepository.findAllByReferencespaceAndParcellation(INVALID_REFSPACE, COLIN_PARCELLATION);

        assertThat(regions, hasSize(0));
    }

    @Test
    public void shouldReturnEmptyListForInvalidParcellation() {
        List<Region> regions = regionRepository.findAllByReferencespaceAndParcellation(COLIN, INVALID_PARCELLATION);

        assertThat(regions, hasSize(0));
    }

    @Test
    public void shouldReturnEmptyListForInvalidRefSpaceAndInvalidParcellation() {
        List<Region> regions = regionRepository.findAllByReferencespaceAndParcellation(INVALID_REFSPACE, INVALID_PARCELLATION);

        assertThat(regions, hasSize(0));
    }

    @Test
    public void shouldUseCachedValuesOnSecondCall() {
        regionRepository.findAllByReferencespaceAndParcellation(COLIN, COLIN_PARCELLATION);
    }
    //endregion

    //region === findOneByReferencespaceAndParcellationAndName
    @Test
    public void shouldReturnOneRegionForValidData() {
        Region region = regionRepository
                .findOneByReferencespaceAndParcellationAndName(COLIN, COLIN_PARCELLATION, COLIN_REGION);

        assertEquals(COLIN_REGION, region.getName());
    }

    @Test
    public void shouldReturnNullForInvalidReferencespace() {
        Region region = regionRepository
                .findOneByReferencespaceAndParcellationAndName(INVALID_REFSPACE, COLIN_PARCELLATION, COLIN_REGION);

        assertNull(region);
    }

    @Test
    public void shouldReturnNullForInvalidParcellation() {
        Region region = regionRepository
                .findOneByReferencespaceAndParcellationAndName(COLIN, INVALID_PARCELLATION, COLIN_REGION);

        assertNull(region);
    }

    @Test
    public void shouldReturnNullForInvalidRegion() {
        Region region = regionRepository
                .findOneByReferencespaceAndParcellationAndName(COLIN, INVALID_PARCELLATION, INVALID_REGION);

        assertNull(region);
    }
    //endregion
}
