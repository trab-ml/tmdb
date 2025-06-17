package fr.movieapp.services;

import fr.movieapp.entities.ProfileEntity;
import fr.movieapp.exceptions.AlreadyExistsException;
import fr.movieapp.exceptions.ProfileEntityAlreadyExistsException;
import fr.movieapp.external.ApiMoviesWebClientService;
import fr.movieapp.mappers.ToModelMapper;
import fr.movieapp.models.Profile;
import fr.movieapp.repositories.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ApiMoviesWebClientService apiMoviesWebClientService;

    public ProfileServiceImpl(ProfileRepository profileRepository, ApiMoviesWebClientService apiMoviesWebClientService) {
        this.profileRepository = profileRepository;
        this.apiMoviesWebClientService = apiMoviesWebClientService;
    }

    @Override
    public List<Profile> getProfiles() {
        return ToModelMapper.entitiesToModel(
                profileRepository.findAll()
        );
    }

    @Override
    public Profile addProfile(Profile profile) throws AlreadyExistsException {
        ProfileEntity pe = new ProfileEntity();
        profile.getAdult().ifPresent(pe::setAdult);
        pe.setGenreList(profile.getGenreList());

        ProfileEntity saved;
        try {
            saved = profileRepository.save(pe);
        } catch (Exception e) {
            throw new ProfileEntityAlreadyExistsException();
        }

        return ToModelMapper.toModel(saved);
    }
}
