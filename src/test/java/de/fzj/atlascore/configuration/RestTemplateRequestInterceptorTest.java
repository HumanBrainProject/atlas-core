package de.fzj.atlascore.configuration;

import de.fzj.atlascore.service.RequestContextService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RestTemplateRequestInterceptorTest {

    private static final String BEARER_DUMMY_TOKEN = "Bearer DUMMY_TOKEN";
    @Mock
    private RequestContextService requestContextService;

    @Mock
    private ClientHttpRequestExecution execution;

    @Mock
    private HttpRequest request;

    @Mock
    private HttpHeaders httpHeaders;

    @InjectMocks
    private RestTemplateRequestInterceptor restTemplateRequestInterceptor;

    @Test
    public void shouldNotSetAuthorizationHeaderIfNoIsSetOnRequest() throws Exception {
        doReturn("").when(requestContextService).getAuthorizationHeader();
        doReturn(httpHeaders).when(request).getHeaders();

        restTemplateRequestInterceptor.intercept(request, new byte[]{}, execution);

        verify(httpHeaders).setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        verify(httpHeaders).setContentType(MediaType.APPLICATION_JSON);
        verify(httpHeaders, times(0)).add(anyString(), anyString());
        verify(execution).execute(eq(request), any());
    }

    @Test
    public void shouldSetAuthorizationHeaderIfSetOnRequest() throws Exception {
        doReturn(BEARER_DUMMY_TOKEN).when(requestContextService).getAuthorizationHeader();
        doReturn(httpHeaders).when(request).getHeaders();

        restTemplateRequestInterceptor.intercept(request, new byte[]{}, execution);

        verify(httpHeaders).setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        verify(httpHeaders).setContentType(MediaType.APPLICATION_JSON);
        verify(httpHeaders).add(HttpHeaders.AUTHORIZATION, BEARER_DUMMY_TOKEN);
        verify(execution).execute(eq(request), any());
    }
}