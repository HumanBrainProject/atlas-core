package de.fzj.atlascore.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TosControllerTest {

    @InjectMocks
    private TosController tosController;

    @Test
    public void shouldReturnTosSite() {
        assertEquals("tos", tosController.getTos());
    }
}
