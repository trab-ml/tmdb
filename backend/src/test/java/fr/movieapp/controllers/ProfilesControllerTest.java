package fr.movieapp.controllers;

import fr.movieapp.controllers.requests.ProfileRequest;
import fr.movieapp.controllers.responses.ProfileResponse;
import fr.movieapp.entities.ProfileEntity;
import fr.movieapp.exceptions.AlreadyExistsException;
import fr.movieapp.exceptions.ProfileEntityAlreadyExistsException;
import fr.movieapp.mappers.ToModelMapper;
import fr.movieapp.models.Genre;
import fr.movieapp.models.Profile;
import fr.movieapp.repositories.ProfileRepository;
import fr.movieapp.services.ProfileService;
import fr.movieapp.services.ProfileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class ProfilesControllerTest {
    @InjectMocks
    ProfilesController profilesController;

    @Mock
    ProfileService profileService;

    @Mock
    ProfileRepository profileRepository;

    @Test
    void whenGet_thenAllProfilesAreReturned() {
        // given
        Mockito.when(profileService.getProfiles())
                .thenReturn(List.of(Profile.builder().build()));

        // when
        List<ProfileResponse> allProfiles = profilesController.getProfiles().getBody();

        // then
        Assertions.assertNotNull(allProfiles);
        Assertions.assertEquals(1, allProfiles.size());
    }

    @Test
    void givenValidProfile_whenAddProfile_thenItShouldBeAdded() throws AlreadyExistsException {
        // given
        Profile expectedProfile = Profile.builder().build();
        Mockito.when(profileService.addProfile(any(Profile.class)))
                .thenReturn(expectedProfile);

        // when
        ProfileResponse profileResponse = profilesController
                .addProfile(ProfileRequest
                        .builder()
                        .genreIdsList(Optional.of(List.of().toString()))
                        .build()).getBody();

        // then
        Assertions.assertNotNull(profileResponse);
    }

    @Test
    void givenExistingProfile_whenAddProfile_thenAlreadyExistsExceptionShouldBeThrown() throws AlreadyExistsException {
        // given
        ProfileRequest profileRequest = ProfileRequest
                .builder()
                .id(Optional.of(1L))
                .adult(Optional.of(true))
                .genreIdsList(Optional.of(List.of().toString()))
                .build();

        // when
        Mockito.when(profileService.addProfile(any(Profile.class)))
                .thenThrow(new ProfileEntityAlreadyExistsException());

        // then
        Assertions.assertThrows(AlreadyExistsException.class, () -> profilesController.addProfile(profileRequest));
    }
}
