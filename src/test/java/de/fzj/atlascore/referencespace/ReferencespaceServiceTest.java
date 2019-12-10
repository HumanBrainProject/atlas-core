package de.fzj.atlascore.referencespace;

import org.junit.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class ReferencespaceServiceTest {

    private static final String REFERENCESPACE_NAME = "bigbrain";
    private static final String REFERENCESPACE_NAME_INVALID = "smallbrain";

    private ReferencespaceService referencespaceService = new ReferencespaceService();

    @Test
    public void shouldReturnStaticReferencespaceList() {
        assertThat(referencespaceService.getReferencespaces(), hasSize(5));
    }

    @Test
    public void shouldReturnReferenceSpaceForValidName() {
        Referencespace referencespace = referencespaceService.getReferencespaceByName(REFERENCESPACE_NAME);

        assertEquals(REFERENCESPACE_NAME, referencespace.getName());
    }

    @Test
    public void shouldReturnNullForInvalidSpace() {
        assertNull(referencespaceService.getReferencespaceByName(REFERENCESPACE_NAME_INVALID));
    }
}
