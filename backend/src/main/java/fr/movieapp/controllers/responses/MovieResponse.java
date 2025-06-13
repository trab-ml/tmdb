package fr.movieapp.controllers.responses;

import java.time.LocalDate;
import java.util.List;

public record MovieResponse (
        int id,
        boolean adult,
        List<GenreResponse> genreList,
        String originalLanguage,
        String originalTitle,
        String title,
        String overview,
        String coverImgUrl,
        LocalDate releaseDate) {}
