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
                        ReferencespaceRepository.BIG_BRAIN_NAME,
                        ReferencespaceRepository.MNI_COLIN_27,
                        ReferencespaceRepository.MNI_152,
                        ReferencespaceRepository.WAXHOLM,
                        ReferencespaceRepository.ALLEN_MOUSE
                )
        );
        assertThat(referencespaces, hasSize(5));
    }

    @Test
    public void shouldReturnReferencespaceForColin() {
        Referencespace colin = referencespaceRepository.findOneByName(ReferencespaceRepository.MNI_COLIN_27);

        assertEquals(ReferencespaceRepository.MNI_COLIN_27, colin.getName());
    }

    @Test
    public void shouldReturnTrueForValidSpace() {
        assertTrue(referencespaceRepository.isValidReferenceSpace(ReferencespaceRepository.MNI_COLIN_27));
        assertTrue(referencespaceRepository.isValidReferenceSpace(ReferencespaceRepository.BIG_BRAIN_NAME));
        assertTrue(referencespaceRepository.isValidReferenceSpace(ReferencespaceRepository.MNI_152));
        assertTrue(referencespaceRepository.isValidReferenceSpace(ReferencespaceRepository.WAXHOLM));
        assertTrue(referencespaceRepository.isValidReferenceSpace(ReferencespaceRepository.ALLEN_MOUSE));
    }

    @Test
    public void shouldReturnFalseForInvalidSpace() {
        assertFalse(referencespaceRepository.isValidReferenceSpace("colinn"));
        assertFalse(referencespaceRepository.isValidReferenceSpace("space"));
        assertFalse(referencespaceRepository.isValidReferenceSpace("smallbrain"));
    }
}
