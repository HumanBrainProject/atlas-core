package de.fzj.atlascore.referencespace;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReferencespaceServiceTest {

    private static final HashMap<String, Object> PROPERTIES = new HashMap<>() {{
        put("key1", "value1");
        put("key2", "value2");
    }};
    private static final String REFERENCESPACE_BIGBRAIN = "bigbrain";
    private static final String REFERENCESPACE_COLIN = "colin";
    private static final String REFERENCESPACE_NAME_INVALID = "smallbrain";

    @Mock
    private ReferencespaceRepository referencespaceRepository;

    @InjectMocks
    private ReferencespaceService referencespaceService;

    @Before
    public void setUp() {
        when(referencespaceRepository.findAll()).thenReturn(Arrays.asList(
                new Referencespace(PROPERTIES),
                new Referencespace(PROPERTIES)
        ));
        when(referencespaceRepository.findOneById(REFERENCESPACE_BIGBRAIN))
                .thenReturn(new Referencespace(PROPERTIES));
    }

    @Test
    public void shouldReturnStaticReferencespaceList() {
        assertThat(referencespaceService.getReferencespaces(), hasSize(2));
    }

    @Test
    public void shouldReturnReferenceSpaceForValidName() {
        Referencespace referencespace = referencespaceService.getReferencespaceByName(REFERENCESPACE_BIGBRAIN);

        assertEquals(REFERENCESPACE_BIGBRAIN, referencespace.getName());
    }

    @Test
    public void shouldReturnNullForInvalidSpace() {
        assertNull(referencespaceService.getReferencespaceByName(REFERENCESPACE_NAME_INVALID));
    }
}
