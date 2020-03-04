package de.fzj.atlascore.referencespace;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReferencespaceServiceTest {

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
                new Referencespace(REFERENCESPACE_BIGBRAIN),
                new Referencespace(REFERENCESPACE_COLIN)
        ));
        when(referencespaceRepository.findOneById(REFERENCESPACE_BIGBRAIN))
                .thenReturn(new Referencespace(REFERENCESPACE_BIGBRAIN));
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
