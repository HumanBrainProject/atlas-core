package de.fzj.atlascore.tvb;

import de.fzj.atlascore.entity.TractLength;
import de.fzj.atlascore.entity.Weights;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TVBServiceTest {

    private static final String NODE_1 = "node1";
    private static final String NODE_2 = "node2";
    private static final Double VALUE_1 = 1.0;
    private static final Double VALUE_2 = 2.0;
    private static final LinkedHashMap REST_RESULT = new LinkedHashMap();

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TVBService tvbService;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(tvbService, "connectivityUrl", "connectivity");

        REST_RESULT.put(NODE_1, VALUE_1);
        REST_RESULT.put(NODE_2, VALUE_2);
    }

    @Test
    public void shouldReturnAListWithNodes() {
        when(restTemplate.getForObject(anyString(), eq(LinkedHashMap.class))).thenReturn(REST_RESULT);

        List<String> allNodes = tvbService.getAllNodes();
        //Call a second time to trigger and test the cache functionality
        tvbService.getAllNodes();

        assertThat(allNodes, hasItems(NODE_1, NODE_2));
        assertThat(allNodes, hasSize(2));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(LinkedHashMap.class));
    }

    @Test
    public void shouldReturnArea() {
        //Must be implemented, when data is provided
        assertNull(tvbService.getAreaForNode(NODE_1));
    }

    @Test
    public void shouldReturnAverageOrientation() {
        //Must be implemented, when data is provided
        assertNull(tvbService.getAverageOrientationForNode(NODE_1));
    }

    @Test
    public void shouldReturnCenter() {
        //Must be implemented, when data is provided
        assertNull(tvbService.getCentreForNode(NODE_1));
    }

    @Test
    public void shouldReturnCortical() {
        //Must be implemented, when data is provided
        assertNull(tvbService.getCorticalForNode(NODE_1));
    }

    @Test
    public void shouldReturnVolume() {
        //Must be implemented, when data is provided
        assertNull(tvbService.getVolumeForNode(NODE_1));
    }

    @Test
    public void shouldReturnTractLength() {
        //Must be implemented, when data is provided
        TractLength[] tractLengthForNode = tvbService.getTractLengthForNode(NODE_1);

        assertThat(tractLengthForNode, arrayWithSize(0));
    }

    @Test
    public void shouldReturnWeights() {
        when(restTemplate.postForObject(anyString(), anyString(), eq(LinkedHashMap.class)))
                .thenReturn(REST_RESULT);

        Weights[] weightsForNode = tvbService.getWeightsForNode(NODE_1);
        //Call a second time to trigger and test the cache functionality
        tvbService.getWeightsForNode(NODE_2);

        assertThat(weightsForNode, arrayWithSize(2));
        assertThat(weightsForNode, hasItemInArray(new Weights(NODE_1, VALUE_1)));
        assertThat(weightsForNode, hasItemInArray(new Weights(NODE_2, VALUE_2)));
        verify(restTemplate, times(1))
                .postForObject(anyString(), anyString(), eq(LinkedHashMap.class));
    }
}