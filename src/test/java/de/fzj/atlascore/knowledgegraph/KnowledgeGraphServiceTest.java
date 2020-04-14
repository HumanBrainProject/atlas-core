package de.fzj.atlascore.knowledgegraph;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KnowledgeGraphServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private KnowledgeGraphIdConverter knowledgeGraphIdConverter;

    @InjectMocks
    private KnowledgeGraphService knowledgeGraphService;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(knowledgeGraphService, "kgUrl", "kg");
        when(restTemplate.getForObject(anyString(), eq(LinkedHashMap.class)))
                .thenReturn(DummyData.getDatasets());
    }

    @Test
    public void shouldReturnAllSpecies() {
        knowledgeGraphService.getAllSpecies();

        verify(restTemplate).getForObject(
                "kg/query/minds/core/species/v1.0.0/species-tmp/instances",
                Object.class
        );
    }

    //region === getAllDatasets
    //endregion

    //region === getParcellationDatasets
    @Test
    public void shouldReturnAllParcellationsWithoutFiltering() {
        ArrayList parcellationDatasets = knowledgeGraphService.getParcellationDatasets(null);
        assertEquals(2, parcellationDatasets.size());
        verifyZeroInteractions(knowledgeGraphIdConverter);
    }

    @Test
    public void shouldCallFilteredParcellations() {
        knowledgeGraphService.getParcellationDatasets(DummyData.PARCELLATION_ID_WAXHOLM);
        verify(restTemplate).getForObject(
                eq("kg/query/minds/core/dataset/v1.0.0/ac_ds_parcellation/instances?parcellation=" + DummyData.PARCELLATION_ID_WAXHOLM),
                any()
        );
        verify(knowledgeGraphIdConverter).convertParcellationId(DummyData.PARCELLATION_ID_WAXHOLM);
    }
    //endregion

    //region === getReferencespaceDatasets
    @Test
    public void shouldReturnAllReferencespacesWithoutFiltering() {
        assertEquals(2, knowledgeGraphService.getReferencespaceDatasets(null).size());
        verifyZeroInteractions(knowledgeGraphIdConverter);
    }

    @Test
    public void shouldReturnFilteredReferencespaces() {
        knowledgeGraphService.getReferencespaceDatasets(DummyData.REFERENCESPACE_ID_WAXHOLM);
        verify(restTemplate).getForObject(
                eq("kg/query/minds/core/dataset/v1.0.0/ac_ds_referencespace/instances?referencespace=" + DummyData.REFERENCESPACE_ID_WAXHOLM),
                any()
        );
        verify(knowledgeGraphIdConverter).convertReferencespaceId(DummyData.REFERENCESPACE_ID_WAXHOLM);
    }
    //endregion

    //region === getRegionsDatasets
    @Test
    public void shouldReturnAllRegionsWithoutFiltering() {
        assertEquals(2, knowledgeGraphService.getRegionsDatasets(null).size());
        verifyZeroInteractions(knowledgeGraphIdConverter);
    }

    @Test
    public void shouldReturnFilteredRegion() {
        knowledgeGraphService.getRegionsDatasets(DummyData.REGION_ID_RAT_WHOLE);
        verify(restTemplate).getForObject(
                eq("kg/query/minds/core/dataset/v1.0.0/ac_ds_regions/instances?region=" + DummyData.REGION_ID_RAT_WHOLE),
                any()
        );
        verify(knowledgeGraphIdConverter).convertRegionId(DummyData.REGION_ID_RAT_WHOLE);
    }
    //endregion

    //region === getRegionsFilteredByList
    @Test
    public void shouldReturnAllRegionFilteredByList() {
        assertEquals(1, knowledgeGraphService.getRegionsFilteredByList(Arrays.asList(DummyData.REGION_ID_MOUSE_WHOLE)).size());
        verify(knowledgeGraphIdConverter, times(2)).convertRegionId(DummyData.REGION_ID_MOUSE_WHOLE);
    }
    //endregion
}
