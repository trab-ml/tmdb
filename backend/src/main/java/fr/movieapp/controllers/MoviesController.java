package fr.movieapp.controllers;

import fr.movieapp.external.ApiMoviesWebClientService;
import fr.movieapp.models.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MoviesController {
    private final ApiMoviesWebClientService apiMoviesWebClientService;

    MoviesController(ApiMoviesWebClientService apiMoviesWebClientService) {
        this.apiMoviesWebClientService = apiMoviesWebClientService;
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok().body(apiMoviesWebClientService.getTopRatedMovies());
    }
}
