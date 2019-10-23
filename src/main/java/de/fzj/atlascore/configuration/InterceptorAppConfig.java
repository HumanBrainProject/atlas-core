package de.fzj.atlascore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures all interceptors used in the application
 *
 * @see AuthorizationHeaderInterceptor
 */
@Configuration
public class InterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorizationHeaderInterceptor authorizationHeaderInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(authorizationHeaderInterceptor);
    }
}
