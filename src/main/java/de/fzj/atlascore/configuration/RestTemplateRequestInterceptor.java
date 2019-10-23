package de.fzj.atlascore.configuration;

import com.google.common.base.Strings;
import de.fzj.atlascore.service.RequestContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

/**
 * An interceptor for the custom {@link org.springframework.web.client.RestTemplate} bean.
 *
 * @see RestTemplateConfiguration#restTemplate(RestTemplateBuilder)
 */
@Component
public class RestTemplateRequestInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    private RequestContextService requestContextService;

    /**
     * Each request with the {@link org.springframework.web.client.RestTemplate} is extended by headers.
     * Accept and ContentType header are always set.
     * An Authorization header is only set, when it is present in the current request context.
     *
     * @param request the request to be performed
     * @param body the body of the request
     * @param execution used to invoke the next interceptor
     * @return a new ClientHttpResponse
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        if(!Strings.isNullOrEmpty(requestContextService.getAuthorizationHeader())) {
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, requestContextService.getAuthorizationHeader());
        }
        request.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        ClientHttpResponse response = execution.execute(request, body);
        return response;
    }
}
