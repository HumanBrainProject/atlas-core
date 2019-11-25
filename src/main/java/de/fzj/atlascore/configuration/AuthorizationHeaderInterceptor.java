package de.fzj.atlascore.configuration;

import com.google.common.base.Strings;
import de.fzj.atlascore.service.RequestContextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interceptor is used to extract the Authorization header from the incoming request
 * If an Authorization header is set, it will be stored in the {@link RequestContextService}
 */
@Component
public class AuthorizationHeaderInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LogManager.getLogger(AuthorizationHeaderInterceptor.class);

    @Autowired
    private RequestContextService requestContextService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(!Strings.isNullOrEmpty(authorization)) {
            LOGGER.info("Request with authorization header: " + authorization.substring(0, 12) + "...");
            requestContextService.setAuthorizationHeader(authorization);
        }
        return true;
    }
}
