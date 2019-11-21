package de.fzj.atlascore.allen;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AllenBrainServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AllenBrainService allenBrainService;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(allenBrainService, "allenBrainUrl", "allen");
    }

    @Test
    public void shouldReturnOntology() {
        allenBrainService.getOntologyStructure();

        verify(restTemplate).getForObject(
                "allen/structure_graph_download/1.json",
                Object.class
        );
    }
}