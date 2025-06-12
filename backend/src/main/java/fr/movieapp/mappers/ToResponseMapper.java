package fr.movieapp.mappers;

import fr.movieapp.external.dto.MovieApiResponseDto;
import fr.movieapp.models.Genre;
import fr.movieapp.models.Movie;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ToResponseMapper {
    public static List<Movie> toMovies(MovieApiResponseDto response) {
        if (response == null || response.movieList() == null) {
            log.warn("Received null or empty movie response");
            return new ArrayList<>();
        }

        return response.movieList().stream()
                .map(dto -> {
                    try {
                        return new Movie(
                                dto.id(),
                                dto.adult(),
                                dto.genreIds() != null ? dto.genreIds().stream()
                                        .map(genreId -> Genre.getGenre(genreId).orElse(Genre.ACTION))
                                        .distinct()
                                        .toList() : new ArrayList<>(),
                                dto.originalLanguage(),
                                dto.originalTitle(),
                                dto.title(),
                                dto.overview(),
                                dto.coverImgUrl(),
                                LocalDate.parse(dto.releaseDate())
                        );
                    } catch (Exception e) {
                        log.error("Error mapping movie DTO: {}, error: {}", dto, e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }
}