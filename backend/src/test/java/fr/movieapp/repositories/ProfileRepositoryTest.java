package fr.movieapp.repositories;

import fr.movieapp.entities.ProfileEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProfileRepositoryTest {
    @InjectMocks
    private ProfileRepository profileRepository = Mockito.mock(ProfileRepository.class);

    @Test
    void whenSaveProfileEntity_thenProfileIsSaved() {
        // Given
        ProfileEntity profileEntity = ProfileEntity.builder().build();
        Mockito.when(profileRepository.save(any(ProfileEntity.class)))
                .thenReturn(profileEntity);

        // When
        ProfileEntity savedProfile = profileRepository.save(profileEntity);

        // Then
        assertNotNull(savedProfile);
        assertEquals(profileEntity, savedProfile);
    }

    @Test
    void whenFindAllProfiles_thenReturnProfileList() {
        // Given
        ProfileEntity profileEntity1 = ProfileEntity.builder().build();
        ProfileEntity profileEntity2 = ProfileEntity.builder().build();
        Mockito.when(profileRepository.findAll())
                .thenReturn(java.util.List.of(profileEntity1, profileEntity2));

        // When
        java.util.List<ProfileEntity> profiles = profileRepository.findAll();

        // Then
        assertNotNull(profiles);
        assertEquals(2, profiles.size());
        assertTrue(profiles.contains(profileEntity1));
        assertTrue(profiles.contains(profileEntity2));
    }
}