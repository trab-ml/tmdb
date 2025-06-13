package fr.movieapp.services;

import fr.movieapp.exceptions.AlreadyExistsException;
import fr.movieapp.models.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> getProfiles();
    Profile addProfile(Profile profile) throws AlreadyExistsException;
}
