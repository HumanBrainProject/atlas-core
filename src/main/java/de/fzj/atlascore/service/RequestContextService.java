package de.fzj.atlascore.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

/**
 * The RequestContextService is used to store request information.
 * It is used by other services and interceptors to get information about the current request.
 */
@Service
@RequestScope
public class RequestContextService {

    private String authorizationHeader = "";

    public String getAuthorizationHeader() {
        return authorizationHeader;
    }

    public void setAuthorizationHeader(String headerValue) {
        this.authorizationHeader = headerValue;
    }
}
