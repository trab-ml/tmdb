package fr.movieapp.controllers;

import fr.movieapp.controllers.responses.MovieResponse;
import fr.movieapp.exceptions.ProfileEntityNotFoundException;
import fr.movieapp.external.MoviesApiRestClientService;
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
    private final MoviesApiRestClientService moviesApiRestClientService;

    public MoviesController(MoviesApiRestClientService moviesApiRestClientService) {
        this.moviesApiRestClientService = moviesApiRestClientService;
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<MovieResponse>> getMovies() {
        return ResponseEntity.ok().body(
                ToResponseMapper.toResponse(moviesApiRestClientService.getTopRatedMovies())
        );
    }

    @GetMapping("/best-movies")
    public ResponseEntity<List<MovieResponse>> getPopularMovies() {
        return ResponseEntity.ok().body(
                ToResponseMapper.toResponse(moviesApiRestClientService.getBestMovies())
        );
    }

    @GetMapping("/best-movies-by-profile")
    public ResponseEntity<List<MovieResponse>> searchByProfile(@RequestParam String id) throws ProfileEntityNotFoundException {
        return ResponseEntity.ok()
                .body(ToResponseMapper.toResponse(moviesApiRestClientService.searchByProfile(Integer.parseInt(id))));
    }
}
