package de.fzj.atlascore.tvb;

import de.fzj.atlascore.region.entity.Region;
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
    public void shouldReturnAllRegions() {
        when(restTemplate.getForObject(anyString(), eq(LinkedHashMap.class))).thenReturn(REST_RESULT);
        when(restTemplate.postForObject(anyString(), anyString(), eq(LinkedHashMap.class))).thenReturn(REST_RESULT);

        List<Region> regions = tvbService.getAllRegions();
        //Call a second time to trigger and test the cache functionality
        tvbService.getAllRegions();

        assertThat(regions, hasSize(2));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(LinkedHashMap.class));
    }
}
