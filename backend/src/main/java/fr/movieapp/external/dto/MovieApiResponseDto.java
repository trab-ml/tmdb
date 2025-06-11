package fr.movieapp.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MovieApiResponseDto(@JsonProperty("results") List<MovieDto> movieList) {}
