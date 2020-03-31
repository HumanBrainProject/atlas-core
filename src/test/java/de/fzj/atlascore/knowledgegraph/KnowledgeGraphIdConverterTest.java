package de.fzj.atlascore.knowledgegraph;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KnowledgeGraphIdConverterTest {

    private static final String REFERENCESPACE_ID_FULL = "https://nexus.humanbrainproject.org/v0/data/minds/core/referencespace/v1.0.0/7f39f7be-445b-47c0-9791-e971c0b6d992";
    private static final String PARCELLATION_ID_FULL = "https://nexus.humanbrainproject.org/v0/data/minds/core/parcellationatlas/v1.0.0/94c1125b-b87e-45e4-901c-00daee7f2579";
    private static final String REGION_ID_FULL = "https://nexus.humanbrainproject.org/v0/data/minds/core/parcellationregion/v1.0.0/9c6c3c96-8129-4e0e-aa22-a0fb435aab45";
    private static final String REFERENCESPACE_ID_SHORT = "7f39f7be-445b-47c0-9791-e971c0b6d992";
    private static final String PARCELLATION_ID_SHORT = "94c1125b-b87e-45e4-901c-00daee7f2579";
    private static final String REGION_ID_SHORT = "9c6c3c96-8129-4e0e-aa22-a0fb435aab45";

    private KnowledgeGraphIdConverter knowledgeGraphIdConverter = new KnowledgeGraphIdConverter();

    @Test
    public void shouldReturnUnconvertedReferencespaceId() {
        assertEquals(REFERENCESPACE_ID_FULL, knowledgeGraphIdConverter.convertReferencespaceId(REFERENCESPACE_ID_FULL));
    }

    @Test
    public void shouldReturnConvertedReferencespaceId() {
        assertEquals(REFERENCESPACE_ID_FULL, knowledgeGraphIdConverter.convertReferencespaceId(REFERENCESPACE_ID_SHORT));
    }

    @Test
    public void shouldReturnUnconvertedParcellationId() {
        assertEquals(PARCELLATION_ID_FULL, knowledgeGraphIdConverter.convertParcellationId(PARCELLATION_ID_FULL));
    }

    @Test
    public void shouldReturnConvertedParcellationId() {
        assertEquals(PARCELLATION_ID_FULL, knowledgeGraphIdConverter.convertParcellationId(PARCELLATION_ID_SHORT));
    }

    @Test
    public void shouldReturnUnconvertedRegionId() {
        assertEquals(REGION_ID_FULL, knowledgeGraphIdConverter.convertRegionId(REGION_ID_FULL));
    }

    @Test
    public void shouldReturnConvertedRegionId() {
        assertEquals(REGION_ID_FULL, knowledgeGraphIdConverter.convertRegionId(REGION_ID_SHORT));
    }
}
