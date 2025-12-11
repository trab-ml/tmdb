package fr.movieapp.mappers;

import fr.movieapp.external.dto.MovieApiResponseDto;
import fr.movieapp.external.dto.MovieDto;
import fr.movieapp.models.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

    // Will Throw NullPointerException
    @Test
    void givenMovieApiResponseDtoWithInvalidMovieDto_thenItShouldSkipInvalidEntries() {
        // given
        MovieDto invalidMovieDto = MovieDto.builder()
                .id((Integer) null)
                .build();
        MovieApiResponseDto responseDto = MovieApiResponseDto.builder()
                .movieList(List.of(invalidMovieDto))
                .build();

        // when
        List<Movie> movies = ToModelMapper.toModel(responseDto);

        // then
        Assertions.assertNotNull(movies);
        Assertions.assertTrue(movies.isEmpty());
    }

    @Test
    void givenValidProfileEntity_thenItShouldBeMappedCorrectly() {
        // given when then
    }
}