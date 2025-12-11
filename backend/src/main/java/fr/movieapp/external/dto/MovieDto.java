package fr.movieapp.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record MovieDto(
        int id,
        boolean adult,
        @JsonProperty("genre_ids") List<Integer> genreIds,
        @JsonProperty("original_language") String originalLanguage,
        @JsonProperty("original_title") String originalTitle,
        String title,
        String overview,
        @JsonProperty("backdrop_path") String coverImgUrl,
        @JsonProperty("release_date") String releaseDate
) {}
