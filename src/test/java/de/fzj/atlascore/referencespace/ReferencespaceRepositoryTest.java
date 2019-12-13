package de.fzj.atlascore.referencespace;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class ReferencespaceRepositoryTest {

    private ReferencespaceRepository referencespaceRepository = new ReferencespaceRepository();

    @Test
    public void shouldReturnAllValidReferencespaces() {
        List<Referencespace> referencespaces = referencespaceRepository.findAll();

        assertThat(
                referencespaces.stream().map(Referencespace::getName).collect(Collectors.toList()),
                hasItems(
                        "bigbrain",
                        "colin",
                        "MNI152",
                        "waxholmRatV2_0",
                        "allenMouse"
                )
        );
        assertThat(referencespaces, hasSize(5));
    }

    @Test
    public void shouldReturnReferencespaceForColin() {
        Referencespace colin = referencespaceRepository.findOneByName("colin");

        assertEquals("colin", colin.getName());
    }

    @Test
    public void shouldReturnTrueForValidSpace() {
        assertTrue(referencespaceRepository.isValidReferenceSpace("colin"));
        assertTrue(referencespaceRepository.isValidReferenceSpace("bigbrain"));
        assertTrue(referencespaceRepository.isValidReferenceSpace("MNI152"));
        assertTrue(referencespaceRepository.isValidReferenceSpace("waxholmRatV2_0"));
        assertTrue(referencespaceRepository.isValidReferenceSpace("allenMouse"));
    }

    @Test
    public void shouldReturnFalseForInvalidSpace() {
        assertFalse(referencespaceRepository.isValidReferenceSpace("colinn"));
        assertFalse(referencespaceRepository.isValidReferenceSpace("space"));
        assertFalse(referencespaceRepository.isValidReferenceSpace("smallbrain"));
    }
}
