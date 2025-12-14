package fr.movieapp.services;

import fr.movieapp.entities.ProfileEntity;
import fr.movieapp.exceptions.AlreadyExistsException;
import fr.movieapp.models.Profile;
import fr.movieapp.repositories.ProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {
    @InjectMocks
    ProfileServiceImpl profileServiceImpl;

    @Mock
    ProfileRepository profileRepository;

    @Mock
    Profile profile;

    @Test
    void whenGetProfiles_thenAllProfilesAreReturned() {
        // given
        Mockito.when(profileRepository.findAll())
                .thenReturn(List.of(ProfileEntity.builder().build()));

        // when
        List<Profile> profiles = profileServiceImpl.getProfiles();

        // then
        Assertions.assertFalse(profiles.isEmpty());
        Assertions.assertEquals(1, profiles.size());
    }

    @Test
    void givenProfile_whenAddProfile_thenItWillBeSaved() throws AlreadyExistsException {
        // given
        Mockito.when(profileRepository.save(Mockito.any(ProfileEntity.class)))
                .thenReturn(ProfileEntity.builder().build());

        // when
        Profile savedProfile = profileServiceImpl.addProfile(profile);

        // then
        Assertions.assertNotNull(savedProfile);
    }

    @Test
    void givenExistingProfile_whenAddProfile_thenAlreadyExistsExceptionIsThrown() {
        // given
        Mockito.when(profile.getAdult())
                .thenReturn(java.util.Optional.of(true));
        Mockito.when(profile.getGenreList())
                .thenReturn(List.of());
        Mockito.when(profileRepository.save(Mockito.any(ProfileEntity.class)))
                .thenThrow(new RuntimeException());

        // when / then
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            profileServiceImpl.addProfile(profile);
        });
    }
}
