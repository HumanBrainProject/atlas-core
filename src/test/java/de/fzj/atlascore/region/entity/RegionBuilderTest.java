package de.fzj.atlascore.region.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegionBuilderTest {

    private static final String NAME = "RegionA";
    private static final Double AREA = 1000.0;
    private static final Vector AVERAGEORIENTATION = new Vector(1,2,3);
    private static final Vector CENTRES = new Vector(4,5,6);
    private static final Integer CORTICAL = 1;
    private static final TractLength[] TRACTLENGTHS = new TractLength[]{new TractLength(), new TractLength()};
    private static final Double VOLUME = 2000.0;
    private static final Weights[] WEIGHTS = new Weights[]{new Weights()};
    private static final String LABEL = "AREA_LABEL";
    private static final String HEMISPHERE = "left";

    @Test
    public void shouldCreateRegionWithAllValues() {
        Region buildRegion = RegionBuilder
                .aRegion()
                .withName(NAME)
                .withArea(AREA)
                .withAverageOrientation(AVERAGEORIENTATION)
                .withCentres(CENTRES)
                .withCortical(CORTICAL)
                .withTractLengths(TRACTLENGTHS)
                .withVolume(VOLUME)
                .withWeights(WEIGHTS)
                .withLabel(LABEL)
                .withHemisphere(HEMISPHERE)
                .build();

        assertEquals(NAME, buildRegion.getName());
        assertEquals(AREA, buildRegion.getArea());
        assertEquals(AVERAGEORIENTATION, buildRegion.getAverageOrientation());
        assertEquals(CENTRES, buildRegion.getCentres());
        assertEquals(CORTICAL, buildRegion.getCortical());
        assertNotNull(buildRegion.getTractLengths());
        assertEquals(2, buildRegion.getTractLengths().length);
        assertEquals(VOLUME, buildRegion.getVolume());
        assertNotNull(buildRegion.getWeights());
        assertEquals(1, buildRegion.getWeights().length);
        assertEquals(LABEL, buildRegion.getLabel());
        assertEquals(HEMISPHERE, buildRegion.getHemisphere());
    }
}
