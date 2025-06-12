package fr.movieapp.external;

import fr.movieapp.models.Movie;

import java.util.List;

public interface ApiMoviesWebClientService {
    List<Movie> getTopRatedMovies();
    List<Movie> getPopularMovies();
}
