package de.fzj.atlascore.tvb;

import de.fzj.atlascore.entity.Node;
import de.fzj.atlascore.entity.TractLength;
import de.fzj.atlascore.entity.Vector;
import de.fzj.atlascore.entity.Weights;
import org.junit.Test;

import static org.hamcrest.Matchers.arrayWithSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ITVBServiceTest {

    private static final String NODE_VALUE = "nodeValue";
    private static final Double AREA = 1.0;
    private static final Vector AVERAGE_ORIENTATION = new Vector(1.0, 2.0, 3.0);
    private static final Vector CENTRE = new Vector(4.0, 5.0, 6.0);
    private static final Integer CORTICAL = 0;
    private static final TractLength[] TRACT_LENGTH = new TractLength[]{};
    private static final Double VOLUME = 42.0;
    private static final Weights[] WEIGHTS = new Weights[]{};

    @Test
    public void shouldReturnNodeInformationFromDefaultMethod() {
        ITVBService service = mock(ITVBService.class);
        when(service.getAreaForNode(NODE_VALUE)).thenReturn(AREA);
        when(service.getAverageOrientationForNode(NODE_VALUE)).thenReturn(AVERAGE_ORIENTATION);
        when(service.getCentreForNode(NODE_VALUE)).thenReturn(CENTRE);
        when(service.getCorticalForNode(NODE_VALUE)).thenReturn(CORTICAL);
        when(service.getTractLengthForNode(NODE_VALUE)).thenReturn(TRACT_LENGTH);
        when(service.getVolumeForNode(NODE_VALUE)).thenReturn(VOLUME);
        when(service.getWeightsForNode(NODE_VALUE)).thenReturn(WEIGHTS);
        when(service.getAllNodeInformation(anyString())).thenCallRealMethod();

        Node node = service.getAllNodeInformation(NODE_VALUE);

        assertEquals(AREA, node.getArea());
        assertEquals(AVERAGE_ORIENTATION, node.getAverageOrientation());
        assertEquals(CENTRE, node.getCentre());
        assertEquals(CORTICAL, node.getCortical());
        assertThat(node.getTractLength(), arrayWithSize(0));
        assertEquals(VOLUME, node.getVolume());
        assertThat(node.getWeights(), arrayWithSize(0));
    }
}