package fr.movieapp.models;

import java.time.LocalDate;
import java.util.List;

public record Movie (
    int id,
    boolean adult,
    List<Genre> genreList,
    String originalLanguage,
    String originalTitle,
    String title,
    String overview,
    String coverImgUrl,
    LocalDate releaseDate) {}
