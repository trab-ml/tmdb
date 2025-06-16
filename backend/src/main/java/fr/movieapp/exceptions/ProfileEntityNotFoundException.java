package fr.movieapp.exceptions;


public class ProfileEntityNotFoundException extends Exception {
    private static final String PROFILE_NOT_FOUND = "Profile not found";

    public ProfileEntityNotFoundException() {
        super(PROFILE_NOT_FOUND);
    }
}