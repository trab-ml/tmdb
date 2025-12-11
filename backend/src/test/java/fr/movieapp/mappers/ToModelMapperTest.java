package fr.movieapp.mappers;

import fr.movieapp.entities.ProfileEntity;
import fr.movieapp.external.dto.MovieApiResponseDto;
import fr.movieapp.external.dto.MovieDto;
import fr.movieapp.models.Genre;
import fr.movieapp.models.Movie;
import fr.movieapp.models.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ToModelMapperTest {
//    TODO : given when then
    @InjectMocks
    private ToModelMapper toModelMapper;

    @Test
    void givenValidMovieDto_thenItShouldBeMappedCorrectly() {
        // given, when
        MovieDto expected = MovieDto.builder().build();
        Movie movie = ToModelMapper.toModel(expected);

        // then
        Assertions.assertNotNull(movie);
        Assertions.assertEquals(expected.id(), movie.id());
    }

    @Test
    void givenValidMovieApiResponseDto_thenItShouldBeTurnedToMoviesList() {
        // given, when
        List<Movie> movies = ToModelMapper.toModel(MovieApiResponseDto.builder().build());

        // then
        Assertions.assertNotNull(movies);
        Assertions.assertTrue(movies.isEmpty());
    }

    @Test
    void givenNullMovieApiResponseDto_thenItShouldReturnEmptyMoviesList() {
        // given, when
        List<Movie> movies = ToModelMapper.toModel((MovieApiResponseDto) null);

        // then
        Assertions.assertNotNull(movies);
        Assertions.assertTrue(movies.isEmpty());
    }

    @Test
    void givenMovieApiResponseDtoWithNullMovieList_thenItShouldReturnEmptyMoviesList() {
        // given
        MovieApiResponseDto responseDto = MovieApiResponseDto.builder()
                .movieList(null)
                .build();

        // when
        List<Movie> movies = ToModelMapper.toModel(responseDto);

        // then
        Assertions.assertNotNull(movies);
        Assertions.assertTrue(movies.isEmpty());
    }

    @Test
    void givenValidProfileEntity_thenItShouldBeMappedCorrectly() {
        // given
        ProfileEntity profileEntity = ProfileEntity.builder().build();

        // when
        Profile expected = ToModelMapper.toModel(profileEntity);

        // then
        Assertions.assertNotNull(expected);
        Assertions.assertEquals(profileEntity.getId(), expected.getId().orElse(null));
    }

    @Test
    void givenValidProfileEntityList_thenItShouldBeMappedToProfileList() {
        // given
        ProfileEntity profileEntity = ProfileEntity.builder().build();
        List<ProfileEntity> profileEntities = List.of(profileEntity);

        // when
        List<Profile> profiles = ToModelMapper.entitiesToModel(profileEntities);

        // then
        Assertions.assertNotNull(profiles);
        Assertions.assertFalse(profiles.isEmpty());
        Assertions.assertEquals(1, profiles.size());
    }

    @Test
    void givenNullProfileEntityList_thenItShouldReturnEmptyProfileList() {
        // given, when
        List<Profile> profiles = ToModelMapper.entitiesToModel(null);

        // then
        Assertions.assertNotNull(profiles);
        Assertions.assertTrue(profiles.isEmpty());
    }

    @Test
    void givenEmptyProfileEntityList_thenItShouldReturnEmptyProfileList() {
        // given, when
        List<Profile> profiles = ToModelMapper.entitiesToModel(List.of());

        // then
        Assertions.assertNotNull(profiles);
        Assertions.assertTrue(profiles.isEmpty());
    }

    @Test
    void givenGenreString_thenItShouldReturnGenreEnum() {
        // given, when
        List<Genre> genreList = ToModelMapper.extractGenres("action,comedy,drama");

        // then
        Assertions.assertNotNull(genreList);
        Assertions.assertEquals(Genre.ACTION, genreList.getFirst());
    }
}