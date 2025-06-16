package fr.movieapp.controllers;

import fr.movieapp.controllers.responses.MovieResponse;
import fr.movieapp.controllers.responses.ProfileResponse;
import fr.movieapp.exceptions.ProfileEntityNotFoundException;
import fr.movieapp.external.ApiMoviesWebClientService;
import fr.movieapp.mappers.ToResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                ToResponseMapper.toResponse(apiMoviesWebClientService.getBestMovies())
                        .subList(0, 10)
        );
    }

    @GetMapping("/best-movies-by-profile")
    public ResponseEntity<List<MovieResponse>> searchByProfile(@RequestParam String id) throws ProfileEntityNotFoundException {
        return ResponseEntity.ok()
                .body(ToResponseMapper.toResponse(apiMoviesWebClientService.searchByProfile(Integer.parseInt(id))));
    }
}
