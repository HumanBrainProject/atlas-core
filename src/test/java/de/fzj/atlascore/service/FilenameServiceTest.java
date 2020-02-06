package de.fzj.atlascore.service;

import de.fzj.atlascore.referencespace.ReferencespaceRepository;
import org.junit.Test;

import static org.junit.Assert.*;

public class FilenameServiceTest {

    private FilenameService filenameService = new FilenameService();

    @Test
    public void shouldReturnFilenameForBigBrain() {
        assertEquals("bigbrain", filenameService.getFilenameForReferencespace(ReferencespaceRepository.BIG_BRAIN_NAME));
    }

    @Test
    public void shouldReturnFilenameForColin() {
        assertEquals("colin", filenameService.getFilenameForReferencespace(ReferencespaceRepository.MNI_COLIN_27));
    }

    @Test
    public void shouldReturnFilenameForMni() {
        assertEquals("MNI152", filenameService.getFilenameForReferencespace(ReferencespaceRepository.MNI_152));
    }
}
