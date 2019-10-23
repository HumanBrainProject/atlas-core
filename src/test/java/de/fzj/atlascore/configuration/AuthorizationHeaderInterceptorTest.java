package de.fzj.atlascore.configuration;

import de.fzj.atlascore.service.RequestContextService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationHeaderInterceptorTest {

    private static final String BEARER_DUMMY_TOKEN = "Bearer DUMMY_TOKEN";

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Spy
    private RequestContextService requestContextService;

    @InjectMocks
    private AuthorizationHeaderInterceptor authorizationHeaderInterceptor;

    @Test
    public void shouldAddAuthorizationHeaderToContextIfPresent() {
        doReturn(BEARER_DUMMY_TOKEN).when(request).getHeader(HttpHeaders.AUTHORIZATION);

        authorizationHeaderInterceptor.preHandle(request, response, null);

        verify(requestContextService).setAuthorizationHeader(BEARER_DUMMY_TOKEN);
        assertEquals(
                requestContextService.getAuthorizationHeader(),
                BEARER_DUMMY_TOKEN
        );
    }

    @Test
    public void keepAuthorizationEmptyIfNotSetOnRequest() {
        doReturn("").when(request).getHeader(HttpHeaders.AUTHORIZATION);

        authorizationHeaderInterceptor.preHandle(request, response, null);

        verifyZeroInteractions(requestContextService);
        assertEquals(
                requestContextService.getAuthorizationHeader(),
                ""
        );
    }
}