package fr.movieapp.mappers;

import fr.movieapp.controllers.requests.ProfileRequest;
import fr.movieapp.entities.ProfileEntity;
import fr.movieapp.external.dto.MovieApiResponseDto;
import fr.movieapp.external.dto.MovieDto;
import fr.movieapp.models.Genre;
import fr.movieapp.models.Movie;
import fr.movieapp.models.Profile;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.*;

@Slf4j
public class ToModelMapper {
    public static Movie toModel(MovieDto dto) {
        return new Movie(
                dto.id(),
                dto.adult(),
                dto.genreIds() != null ? dto.genreIds().stream()
                        .map(Genre::getGenre)
                        .distinct()
                        .toList() : new ArrayList<>(),
                dto.originalLanguage(),
                dto.originalTitle(),
                dto.title(),
                dto.overview(),
                dto.coverImgUrl(),
                LocalDate.parse(dto.releaseDate())
        );
    }

    public static List<Movie> toModel(MovieApiResponseDto response) {
        if (response == null || response.movieList() == null) {
            log.warn("Received null or empty movie response");
            return new ArrayList<>();
        }

        return response.movieList().stream()
                .map(dto -> {
                    try {
                        return toModel(dto);
                    } catch (Exception e) {
                        log.error("Error mapping movie DTO: {}, error: {}", dto, e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    public static Profile toModel(ProfileEntity entity) {
        return new Profile(Optional.ofNullable(entity.getId()),
                Optional.of(entity.isAdult()),
                entity.getGenreList());
    }

    public static List<Profile> entitiesToModel(List<ProfileEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            log.warn("Received null or empty profile entities");
            return new ArrayList<>();
        }

        return entities.stream()
                .map(ToModelMapper::toModel)
                .toList();
    }

    public static List<Genre> extractGenres(String str) {
        String value = str.toLowerCase();
        List<String> stringValuesOfWeatherTypes = Arrays.asList(value.split(","));

        log.info(stringValuesOfWeatherTypes.toString());

        return stringValuesOfWeatherTypes.stream()
                .map(s -> Genre.getGenre(s.trim()))
                .distinct()
                .toList();
    }

    public static Profile toModel(ProfileRequest req) {
        return new Profile(
                req.id(),
                req.adult(),
                req.genreIdsList().isEmpty() ? List.of() : ToModelMapper.extractGenres(req.genreIdsList().get())
        );
    }

    public static List<Profile> toModel(List<ProfileRequest> req) {
        return req.stream()
                .map(ToModelMapper::toModel)
                .toList();
    }
}