package fr.movieapp.controllers.responses;

import fr.movieapp.models.Genre;

import java.util.List;
import java.util.Optional;

public record ProfileResponse(
    Optional<Long> id,
    Optional<Boolean> adult,
    List<Genre> genreList) {}