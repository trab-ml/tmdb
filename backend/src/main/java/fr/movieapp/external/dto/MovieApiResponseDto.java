package fr.movieapp.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record MovieApiResponseDto(@JsonProperty("results") List<MovieDto> movieList) {}
