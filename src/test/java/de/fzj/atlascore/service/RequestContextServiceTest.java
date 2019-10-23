package de.fzj.atlascore.service;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class RequestContextServiceTest {

    private  static final String HEADER_VALUE = "HEADER_VALUE";

    private RequestContextService requestContextService = new RequestContextService();

    @Test
    public void defaultHeaderValueShouldBeEmpty() {
        assertEquals("", requestContextService.getAuthorizationHeader());
    }

    @Test
    public void shouldSetHeader() {
        requestContextService.setAuthorizationHeader(HEADER_VALUE);

        assertEquals(HEADER_VALUE, requestContextService.getAuthorizationHeader());
    }
}