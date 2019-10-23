package de.fzj.atlascore.knowledgegraph;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KnowledgeGraphServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private KnowledgeGraphService knowledgeGraphService;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(knowledgeGraphService, "kgUrl", "kg");
    }

    @Test
    public void shouldReturnAllSpecies() {
        knowledgeGraphService.getAllSpecies();

        verify(restTemplate).getForObject(
                "kg/query/minds/core/species/v1.0.0/species-tmp/instances",
                Object.class
        );
    }
}