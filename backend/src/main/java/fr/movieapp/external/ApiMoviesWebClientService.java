package fr.movieapp.external;

import fr.movieapp.external.dto.MovieDto;

import java.util.List;

public interface ApiMoviesWebClientService {
    List<MovieDto> getTopRatedMovies();
}
