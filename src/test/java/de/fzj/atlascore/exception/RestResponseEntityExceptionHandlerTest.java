package de.fzj.atlascore.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RestResponseEntityExceptionHandlerTest {

    private static final String AUTHORIZATION_ERROR_MSG = "You need a valid authorization for this request";
    private static final String FORBIDDEN_ERROR_MSG = "You do not have the right permission for this request";
    private static final String NOT_FOUND_ERROR_MSG = "The requested resource could not be found";
    private static final String DEFAULT_ERROR_MSG = "An unexpected error occurred";

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private RestResponseEntityExceptionHandler exceptionHandler;

    ///region === Tests for handling HttpClientErrorException
    @Test
    public void shouldHandleAuthorizationException() throws IOException {
        exceptionHandler.handleClientErrorException(
                new HttpClientErrorException(HttpStatus.UNAUTHORIZED),
                response
        );

        verify(response).sendError(HttpStatus.UNAUTHORIZED.value(), AUTHORIZATION_ERROR_MSG);
    }

    @Test
    public void shouldHandleForbiddenException() throws IOException {
        exceptionHandler.handleClientErrorException(
                new HttpClientErrorException(HttpStatus.FORBIDDEN),
                response
        );

        verify(response).sendError(HttpStatus.FORBIDDEN.value(), FORBIDDEN_ERROR_MSG);
    }

    @Test
    public void shouldHandleNotFoundException() throws IOException {
        exceptionHandler.handleClientErrorException(
                new HttpClientErrorException(HttpStatus.NOT_FOUND),
                response
        );

        verify(response).sendError(HttpStatus.NOT_FOUND.value(), NOT_FOUND_ERROR_MSG);
    }

    @Test
    public void shouldHandleInternalServerErrorExceptionsAsDefault() throws IOException {
        exceptionHandler.handleClientErrorException(
                new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR),
                response
        );

        verify(response).sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), DEFAULT_ERROR_MSG);
    }

    @Test
    public void shouldHandleBadRequestsAsDefault() throws IOException {
        exceptionHandler.handleClientErrorException(
                new HttpClientErrorException(HttpStatus.BAD_REQUEST),
                response
        );

        verify(response).sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), DEFAULT_ERROR_MSG);
    }
    ///endregion
}