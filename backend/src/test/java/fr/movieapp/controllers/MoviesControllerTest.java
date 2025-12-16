package fr.movieapp.controllers;

import fr.movieapp.controllers.responses.MovieResponse;
import fr.movieapp.exceptions.ProfileEntityNotFoundException;
import fr.movieapp.external.MoviesApiRestClientService;
import fr.movieapp.models.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MoviesControllerTest {
    @InjectMocks
    MoviesController moviesController;

    @Mock
    MoviesApiRestClientService moviesApiRestClientService;

    @Test
    void whenGetMovies_thenTopRatedMoviesAreReturned() {
        // given
        Mockito.when(moviesApiRestClientService.getTopRatedMovies())
                .thenReturn(List.of(
                        Mockito.mock(Movie.class),
                        Mockito.mock(Movie.class)
                ));

        // when
        List<MovieResponse> movies = moviesController.getMovies().getBody();

        // then
        assertNotNull(movies);
        assertEquals(2, movies.size());
    }

    @Test
    void whenGetPopularMovies_thenBestMoviesAreFetched() {
        // given
        Mockito.when(moviesApiRestClientService.getBestMovies())
                .thenReturn(List.of(
                        Mockito.mock(Movie.class),
                        Mockito.mock(Movie.class),
                        Mockito.mock(Movie.class)
                ));

        // when
        List<MovieResponse> movies = moviesController.getPopularMovies().getBody();

        // then
        assertNotNull(movies);
        assertEquals(3, movies.size());
    }

    @Test
    void whenSearchByProfile_thenMatchingMoviesAreFetched() throws ProfileEntityNotFoundException {
        // given
        Mockito.when(moviesApiRestClientService.searchByProfile(Mockito.anyInt()))
                .thenReturn(List.of(
                        Mockito.mock(Movie.class)
                ));

        // when
        List<MovieResponse> movies = moviesController.searchByProfile("1").getBody();

        // then
        assertNotNull(movies);
        assertEquals(1, movies.size());
    }

    @Test
    void whenSearchByProfileWithInvalidId_thenProfileEntityNotFoundExceptionIsThrown() throws ProfileEntityNotFoundException {
        // given
        Mockito.when(moviesApiRestClientService.searchByProfile(Mockito.anyInt()))
                .thenThrow(new ProfileEntityNotFoundException());

        // when / then
        assertThrows(ProfileEntityNotFoundException.class, () -> moviesController.searchByProfile("999"));
    }
}