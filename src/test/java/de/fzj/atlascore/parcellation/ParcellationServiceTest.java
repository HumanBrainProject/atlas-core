package de.fzj.atlascore.parcellation;

import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ParcellationServiceTest {

    private static final String REF_SPACE_BIGBRAIN = "bigbrain";
    private static final String BIGBRAIN_ONE_PARCELLATION = "Cytoarchitectonic Maps";
    private static final String REF_SPACE_COLIN = "colin";
    private static final String COLIN_ONE_PARCELLATION = "JuBrain Cytoarchitectonic Atlas";
    private static final String REF_SPACE_MIN = "MNI152";
    private static final String MNI_ONE_PARCELLATION = "JuBrain Cytoarchitectonic Atlas";
    private static final String INVALID_REF_SPACE_NAME = "smallbrain";
    private static final String PARCELLATION_NAME = "par1";

    private ParcellationService parcellationService = new ParcellationService();

    @Test
    public void shouldReturnParcellationsForBigBrain() {
        List<Parcellation> parcellations = parcellationService.getParcellations(REF_SPACE_BIGBRAIN);

        assertThat(parcellations, not(empty()));
        assertThat(parcellations, hasItem(new Parcellation(BIGBRAIN_ONE_PARCELLATION)));
    }

    @Test
    public void shouldReturnParcellationsForColin() {
        List<Parcellation> parcellations = parcellationService.getParcellations(REF_SPACE_COLIN);

        assertThat(parcellations, not(empty()));
        assertThat(parcellations, hasItem(new Parcellation(COLIN_ONE_PARCELLATION)));
    }

    @Test
    public void shouldReturnParcellationsForMni() {
        List<Parcellation> parcellations = parcellationService.getParcellations(REF_SPACE_MIN);

        assertThat(parcellations, not(empty()));
        assertThat(parcellations, hasItem(new Parcellation(MNI_ONE_PARCELLATION)));
    }

    @Test
    public void shouldThrowNotFoundExceptionForInvalidRefSpace() {
        try {
            parcellationService.getParcellations(INVALID_REF_SPACE_NAME);
            fail("ResponseStatusException expected");
        } catch(ResponseStatusException e) {
            assertEquals(String.format("Referencespace: %s not found", INVALID_REF_SPACE_NAME), e.getReason());
        }
    }

    @Test
    public void shouldReturnParcellationByName() {
        Parcellation parcellation = parcellationService.getParcellationByName(PARCELLATION_NAME);

        assertEquals(new Parcellation(PARCELLATION_NAME), parcellation);
    }
}
