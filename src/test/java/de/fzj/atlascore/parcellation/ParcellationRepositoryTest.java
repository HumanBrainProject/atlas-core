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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParcellationRepositoryTest {

    private static final HashMap<String, Object> PROPERTIES = new HashMap<>() {{
        put("name", "colin");
        put("id", REF_SPACE);
    }};
    private static final String REF_SPACE = "colin";
    private static final String REF_SPACE_INVALID = "invalid";
    private static final String PARCELLATION_NAME = "JuBrain Cytoarchitectonic Atlas";
    private static final String JSONOBJECT =  "{\"parcellations\": [" +
            "{\"name\": \"" + PARCELLATION_NAME +"\"}]}";

    @Mock
    private BufferedReader reader;

    @Mock
    private FilenameService filenameService;

    @Mock
    private ReferencespaceRepository referencespaceRepository;

    @InjectMocks
    private ParcellationRepository parcellationRepository;

    @Before
    public void setUp() throws IOException {
        when(filenameService.getFilenameForReferencespace(anyString())).then(AdditionalAnswers.returnsFirstArg());
        when(filenameService.getBufferedReaderByReferencespaceId(anyString())).thenReturn(reader);
        when(reader.lines()).thenReturn(Stream.of(JSONOBJECT));
    }

    @Test
    public void shouldReturnAllParcellationsForValidSpace() {
        when(referencespaceRepository.findAll()).thenReturn(Arrays.asList(
           new Referencespace(PROPERTIES)
        ));

        List<Parcellation> parcellations = parcellationRepository.findAllByReferencespace(REF_SPACE);

        assertThat(parcellations, hasSize(1));
    }

    @Test
    public void shouldReturnEmptyListIfInvalidSpace() {
        when(filenameService.getFilenameForReferencespace(anyString())).thenReturn("");
        when(referencespaceRepository.findAll()).thenReturn(Collections.emptyList());

        List<Parcellation> parcellations = parcellationRepository.findAllByReferencespace(REF_SPACE);

        assertThat(parcellations, hasSize(0));
    }

    @Test
    public void shouldReturnEmptyListIfSpaceIsValidButNoFilePresent() {
        when(filenameService.getFilenameForReferencespace(anyString())).thenReturn("");
        when(referencespaceRepository.findAll()).thenReturn(Arrays.asList(
                new Referencespace(PROPERTIES)
        ));

        List<Parcellation> parcellations = parcellationRepository.findAllByReferencespace(REF_SPACE_INVALID);

        assertThat(parcellations, hasSize(0));
    }

    @Test
    public void shouldReturnParcellationForValidSpace() {
        when(referencespaceRepository.findAll()).thenReturn(Arrays.asList(
                new Referencespace(PROPERTIES)
        ));

        Parcellation parcellation = parcellationRepository
                .findOneByReferencespaceAndName(REF_SPACE, PARCELLATION_NAME);

        assertEquals(PARCELLATION_NAME, parcellation.getName());
    }

    @Test
    public void shouldReturnNullIfNoParcellationFound() {
        when(referencespaceRepository.findAll()).thenReturn(Arrays.asList(
                new Referencespace(PROPERTIES)
        ));

        Parcellation parcellation = parcellationRepository
                .findOneByReferencespaceAndName("dummy", "dummy");

        assertNull(parcellation);
    }
}
