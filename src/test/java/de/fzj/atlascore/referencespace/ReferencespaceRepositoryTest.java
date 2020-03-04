package de.fzj.atlascore.referencespace;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReferencespaceRepositoryTest {

    private ReferencespaceRepository referencespaceRepository = new ReferencespaceRepository();

    @Test
    public void shouldReturnAllValidReferencespaces() {
        List<Referencespace> referencespaces = referencespaceRepository.getAll();

        assertThat(
                referencespaces.stream().map(Referencespace::getId).collect(Collectors.toList()),
                hasItems(
                        ReferencespaceRepository.BIG_BRAIN,
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
        Referencespace colin = referencespaceRepository.findOneById(ReferencespaceRepository.MNI_COLIN_27);

        assertEquals(ReferencespaceRepository.MNI_COLIN_27, colin.getId());
    }

    @Test
    public void shouldReturnTrueForValidSpace() {
        assertTrue(referencespaceRepository.isValid(ReferencespaceRepository.MNI_COLIN_27));
        assertTrue(referencespaceRepository.isValid(ReferencespaceRepository.BIG_BRAIN));
        assertTrue(referencespaceRepository.isValid(ReferencespaceRepository.MNI_152));
        assertTrue(referencespaceRepository.isValid(ReferencespaceRepository.WAXHOLM));
        assertTrue(referencespaceRepository.isValid(ReferencespaceRepository.ALLEN_MOUSE));
    }

    @Test
    public void shouldReturnFalseForInvalidSpace() {
        assertFalse(referencespaceRepository.isValidReferenceSpace("colinn"));
        assertFalse(referencespaceRepository.isValidReferenceSpace("space"));
        assertFalse(referencespaceRepository.isValidReferenceSpace("smallbrain"));
    }
}
