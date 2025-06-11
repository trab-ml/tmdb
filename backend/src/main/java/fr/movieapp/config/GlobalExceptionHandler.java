package fr.movieapp.config;

import fr.movieapp.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public ProblemDetail handleException(final Exception ex) {
        return getProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Service unavailable", ex, new HashMap<>());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ProblemDetail handleException(final IllegalArgumentException ex) {
        return getProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage(), ex, new HashMap<>());
    }

    @ExceptionHandler({NotFoundException.class})
    public ProblemDetail handleException(final NotFoundException ex) {
        return getProblemDetail(ex.getStatus(), ex.getMessage(), ex, new HashMap<>());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(final MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return getProblemDetail(HttpStatus.BAD_REQUEST, "Invalid data", ex, getErrorsMap(errors));
    }

    private Map<String, List<String>> getErrorsMap(final List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    private ProblemDetail getProblemDetail(final HttpStatus httpStatus, final String title, final Exception ex, final Map<String, List<String>> errors) {
        log.error(ex.getMessage(), ex);

        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
        problemDetail.setTitle(title);
        problemDetail.setDetail(errors.toString());

        return problemDetail;
    }
}
