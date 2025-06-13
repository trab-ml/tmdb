package fr.movieapp.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AlreadyExistsException extends Exception {
    private final HttpStatus status;

    protected AlreadyExistsException(String msg) {
        super(msg);
        this.status = HttpStatus.NOT_ACCEPTABLE;
    }
}
