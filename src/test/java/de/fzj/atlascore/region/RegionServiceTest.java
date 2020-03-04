package de.fzj.atlascore.region;

import de.fzj.atlascore.tvb.TVBDummyDataService;
import de.fzj.atlascore.tvb.TVBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegionServiceTest {

    private static final String BIGBRAIN_REFERENCESPACE = "bigbrain";
    private static final String PARCELLATION_NAME = "par1";
    private static final String REGION_1 = "Region 1";
    private static final String TVB = "tvb";
    private static final String JUBRAIN = "jubrain";

    @Mock
    private RegionRepository regionRepository;
    @Mock
    private TVBDummyDataService tvbDummyDataService;
    @Mock
    private TVBService tvbService;

    @InjectMocks
    private RegionService regionService;

    @Test
    public void shouldReturnTvbData() {
        regionService.getAll(TVB, PARCELLATION_NAME);

        verify(tvbDummyDataService).getAllRegions();
        verifyZeroInteractions(tvbService);
        verify(regionRepository, times(0))
                .findAllByReferencespaceAndParcellation(anyString(), anyString());
    }

    @Test
    public void shouldReturnJubrainData() {
        regionService.getAll(JUBRAIN, PARCELLATION_NAME);

        verify(tvbService).getAllRegions();
        verifyZeroInteractions(tvbDummyDataService);
        verify(regionRepository, times(0))
                .findAllByReferencespaceAndParcellation(anyString(), anyString());
    }

    @Test
    public void shouldReturnDataFromRegionRepository() {
        regionService.getAll(BIGBRAIN_REFERENCESPACE, PARCELLATION_NAME);

        verifyZeroInteractions(tvbDummyDataService);
        verifyZeroInteractions(tvbService);
        verify(regionRepository).findAllByReferencespaceAndParcellation(BIGBRAIN_REFERENCESPACE, PARCELLATION_NAME);
    }

    @Test
    public void shouldReturnOneRegionForTvb() {
        regionService.getByName(TVB, PARCELLATION_NAME, REGION_1);

        verify(tvbDummyDataService).getRegionByName(REGION_1);
        verifyZeroInteractions(regionRepository);
    }

    @Test
    public void shoulReturnOneRegionFromRepository() {
        regionService.getByName(BIGBRAIN_REFERENCESPACE, PARCELLATION_NAME, REGION_1);

        verifyZeroInteractions(tvbDummyDataService);
        verify(regionRepository)
                .findOneByReferencespaceAndParcellationAndName(BIGBRAIN_REFERENCESPACE, PARCELLATION_NAME, REGION_1);
    }
}
