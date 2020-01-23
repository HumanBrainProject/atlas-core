package de.fzj.atlascore.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StaticPageControllerTest {

    @InjectMocks
    private StaticPageController staticPageController;

    @Test
    public void shouldReturnTosSite() {
        assertEquals("tos", staticPageController.getTos());
    }
}
