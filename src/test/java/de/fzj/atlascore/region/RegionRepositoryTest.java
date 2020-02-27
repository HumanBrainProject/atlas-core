package de.fzj.atlascore.region;

import de.fzj.atlascore.region.entity.Region;
import de.fzj.atlascore.service.FilenameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegionRepositoryTest {

    private static final String COLIN = "colin";
    private static final String COLIN_PARCELLATION = "JuBrain Cytoarchitectonic Atlas";
    private static final String COLIN_REGION = "Area 6d1 (PreCG) - right hemisphere";
    private static final String INVALID_REFSPACE = "invalid-referencespace";
    private static final String INVALID_PARCELLATION = "invalid-parcellation";
    private static final String INVALID_REGION = "invalid-region";
    private static final String BIG_BRAIN = "bigbrain";
    private static final String BIG_BRAIN_PARCELLATION_MAPS = "Cytoarchitectonic Maps";
    private static final String BIG_BRAIN_PARCELLATION_LAYERS = "BigBrain Cortical Layers Segmentation";

    @Mock
    private FilenameService filenameService;

    @InjectMocks
    private RegionRepository regionRepository;

    @Before
    public void setUp() {
        when(filenameService.getFilenameForReferencespace(anyString())).then(AdditionalAnswers.returnsFirstArg());
    }

    //region === findAllByReferencespaceAndParcellation
    @Test
    public void shouldReturnAllRegionsForValidRefSpaceAndParcellation() {
        List<Region> regions = regionRepository.findAllByReferencespaceAndParcellation(COLIN, COLIN_PARCELLATION);

        assertThat(regions, hasSize(222));
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

    //region === testing cache
    @Test
    public void shouldGetDataFromCacheOnSecondCall() {
        regionRepository.findAllByReferencespaceAndParcellation(BIG_BRAIN, BIG_BRAIN_PARCELLATION_MAPS);
        List<Region> regions = regionRepository.findAllByReferencespaceAndParcellation(BIG_BRAIN, BIG_BRAIN_PARCELLATION_MAPS);


        assertNotNull(regions);
        assertFalse(regions.isEmpty());
        verify(filenameService, times(1)).getFilenameForReferencespace(anyString());
        verify(filenameService, times(1)).getFilenameForReferencespace(BIG_BRAIN);

        regionRepository.findAllByReferencespaceAndParcellation(BIG_BRAIN, BIG_BRAIN_PARCELLATION_LAYERS);

        verify(filenameService, times(2)).getFilenameForReferencespace(anyString());
        verify(filenameService, times(2)).getFilenameForReferencespace(BIG_BRAIN);
    }
    //endregion
}
