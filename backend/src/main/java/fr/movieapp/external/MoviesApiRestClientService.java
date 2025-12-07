package fr.movieapp.external;

import fr.movieapp.exceptions.ProfileEntityNotFoundException;
import fr.movieapp.models.Movie;

import java.util.List;

public interface MoviesApiRestClientService {
    List<Movie> getTopRatedMovies();
    List<Movie> getBestMovies();
    List<Movie> searchByProfile(int profileId) throws ProfileEntityNotFoundException;
}
