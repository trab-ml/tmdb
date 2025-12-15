package fr.movieapp.controllers.requests;

import lombok.Builder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Builder
public record ProfileRequest(
        @RequestMapping Optional<Long> id,
        @RequestMapping Optional<Boolean> adult,
        @RequestMapping Optional<String> genreIdsList) {}
