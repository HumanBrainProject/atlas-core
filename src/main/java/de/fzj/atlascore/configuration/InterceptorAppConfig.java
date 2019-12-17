package de.fzj.atlascore.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures all interceptors used in the application
 *
 * @see AuthorizationHeaderInterceptor
 *
 * @author Vadim Marcenko
 */
@Configuration
public class InterceptorAppConfig implements WebMvcConfigurer {

    private final AuthorizationHeaderInterceptor authorizationHeaderInterceptor;

    public InterceptorAppConfig(AuthorizationHeaderInterceptor authorizationHeaderInterceptor) {
        this.authorizationHeaderInterceptor = authorizationHeaderInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(authorizationHeaderInterceptor);
    }
}
