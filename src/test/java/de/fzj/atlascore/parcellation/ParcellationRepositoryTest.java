package de.fzj.atlascore.parcellation;

import de.fzj.atlascore.referencespace.Referencespace;
import de.fzj.atlascore.referencespace.ReferencespaceRepository;
import de.fzj.atlascore.service.FilenameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParcellationRepositoryTest {

    private static final String REF_SPACE = "colin";
    private static final String REF_SPACE_INVALID = "invalid";
    private static final String PARCELLATION_NAME = "JuBrain Cytoarchitectonic Atlas";

    @Mock
    private FilenameService filenameService;

    @Mock
    private ReferencespaceRepository referencespaceRepository;

    @InjectMocks
    private ParcellationRepository parcellationRepository;

    @Before
    public void setUp() {
        when(filenameService.getFilenameForReferencespace(anyString())).then(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    public void shouldReturnAllParcellationsForValidSpace() {
        when(referencespaceRepository.findAll()).thenReturn(Arrays.asList(
           new Referencespace(REF_SPACE)
        ));

        List<Parcellation> parcellations = parcellationRepository.findAllByReferencespace(REF_SPACE);

        assertThat(parcellations, hasSize(1));
    }

    @Test
    public void shouldReturnEmptyListIfInvalidSpace() {
        when(referencespaceRepository.findAll()).thenReturn(Collections.emptyList());

        List<Parcellation> parcellations = parcellationRepository.findAllByReferencespace(REF_SPACE);

        assertThat(parcellations, hasSize(0));
    }

    @Test
    public void shouldReturnEmptyListIfSpaceIsValidButNoFilePresent() {
        when(referencespaceRepository.findAll()).thenReturn(Arrays.asList(
                new Referencespace(REF_SPACE_INVALID)
        ));

        List<Parcellation> parcellations = parcellationRepository.findAllByReferencespace(REF_SPACE_INVALID);

        assertThat(parcellations, hasSize(0));
    }

    @Test
    public void shouldReturnParcellationForValidSpace() {
        when(referencespaceRepository.findAll()).thenReturn(Arrays.asList(
                new Referencespace(REF_SPACE)
        ));

        Parcellation parcellation = parcellationRepository
                .findOneByReferencespaceAndName(REF_SPACE, PARCELLATION_NAME);

        assertEquals(PARCELLATION_NAME, parcellation.getName());
    }

    @Test
    public void shouldReturnNullIfNoParcellationFound() {
        when(referencespaceRepository.findAll()).thenReturn(Arrays.asList(
                new Referencespace(REF_SPACE_INVALID)
        ));

        Parcellation parcellation = parcellationRepository
                .findOneByReferencespaceAndName(REF_SPACE, PARCELLATION_NAME);

        assertNull(parcellation);
    }
}
