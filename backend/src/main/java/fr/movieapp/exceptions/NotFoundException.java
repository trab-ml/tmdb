package fr.movieapp.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class NotFoundException extends Exception {
    private final HttpStatus status;

    public NotFoundException(String msg) {
        super(msg);
        this.status = HttpStatus.NOT_FOUND;
    }
}
