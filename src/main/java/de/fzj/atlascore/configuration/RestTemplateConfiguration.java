package de.fzj.atlascore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * This configuration creates a custom {@link RestTemplate} with interceptors to extend the outgoing requests.
 *
 * @see RestTemplateRequestInterceptor
 */
@Configuration
public class RestTemplateConfiguration {

    @Autowired
    private RestTemplateRequestInterceptor restTemplateRequestInterceptor;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.additionalInterceptors(restTemplateRequestInterceptor).build();
    }
}