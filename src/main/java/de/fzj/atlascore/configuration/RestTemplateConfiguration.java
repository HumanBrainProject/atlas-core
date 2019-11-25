package de.fzj.atlascore.configuration;

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

    @Bean
    public RestTemplate restTemplate(RestTemplateRequestInterceptor restTemplateRequestInterceptor, RestTemplateBuilder builder) {
        return builder.additionalInterceptors(restTemplateRequestInterceptor).build();
    }
}