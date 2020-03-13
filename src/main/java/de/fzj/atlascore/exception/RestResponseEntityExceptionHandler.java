package de.fzj.atlascore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Exceptions handling for {@link HttpClientErrorException} which can be thrown by the {@link org.springframework.web.client.RestTemplate}
 * The exceptions are rewritten to fit the spring boot default error response.
 *
 *{
 *     "timestamp": "YYYY-MM-DD TIME",
 *     "status": HTTP-Status code,
 *     "error": "Error reason",
 *     "message": "Message with further explanation",
 *     "path": "application path that caused the error"
 * }
 *
 * @author Vadim Marcenko
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * The http status codes for unauthorized (401), forbidden (403) and not found (404) get all a custom message.
     * All other error are mapped an internal server error (500) and have a default message.
     *
     * @param exception a {@link HttpClientErrorException}
     * @param response the error response, that needs to be customized
     * @throws IOException is thrown if error could not be send
     */
    @ExceptionHandler(value = { HttpClientErrorException.class })
    protected void handleClientErrorException(
            HttpClientErrorException exception, HttpServletResponse response) throws IOException {
        HttpStatus statusCode = exception.getStatusCode();
        HttpStatus responseStatusCode = statusCode;
        String responseMessage = "An unexpected error occurred";

        switch(statusCode) {
            case UNAUTHORIZED: { responseMessage = "You need a valid authorization for this request"; break; }
            case FORBIDDEN: { responseMessage = "You do not have the right permission for this request"; break; }
            case NOT_FOUND: { responseMessage = "The requested resource could not be found"; break; }
            default: responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.sendError(responseStatusCode.value(), responseMessage);
    }
}
