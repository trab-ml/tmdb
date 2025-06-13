package fr.movieapp.controllers;

import fr.movieapp.controllers.responses.MovieResponse;
import fr.movieapp.external.ApiMoviesWebClientService;
import fr.movieapp.mappers.ToResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movies")
@CrossOrigin("http://localhost:5173")
public class MoviesController {
    private final ApiMoviesWebClientService apiMoviesWebClientService;

    MoviesController(ApiMoviesWebClientService apiMoviesWebClientService) {
        this.apiMoviesWebClientService = apiMoviesWebClientService;
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<MovieResponse>> getMovies() {
        return ResponseEntity.ok().body(
                ToResponseMapper.toResponse(apiMoviesWebClientService.getTopRatedMovies())
        );
    }

    @GetMapping("/best-movies")
    public ResponseEntity<List<MovieResponse>> getPopularMovies() {
        return ResponseEntity.ok().body(
                ToResponseMapper.toResponse(apiMoviesWebClientService.getPopularMovies())
        );
    }
}
