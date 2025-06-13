package fr.movieapp.exceptions;

public class ProfileEntityAlreadyExistsException extends AlreadyExistsException {
    private  static final String PROFILE_ALREADY_EXISTS = "Profile already exists";
    public ProfileEntityAlreadyExistsException() {
        super(PROFILE_ALREADY_EXISTS);
    }
}
