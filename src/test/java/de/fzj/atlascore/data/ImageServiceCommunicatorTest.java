package de.fzj.atlascore.data;


import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ImageServiceCommunicatorTest {

    ImageServiceCommunicator imageServiceCommunicator = new ImageServiceCommunicator();

    @Test
    public void shouldReadData() {
        assertFalse(imageServiceCommunicator.getCellDensities().isEmpty());
    }
}
