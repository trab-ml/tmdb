package fr.movieapp.controllers.requests;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

public record ProfileRequest(
        @RequestMapping Optional<Long> id,
        @RequestMapping Optional<Boolean> adult,
        @RequestMapping Optional<String> genreList) {

    ProfileRequest(
            Optional<Boolean> adult,
            Optional<String> genreList) {
        this(Optional.empty(), adult, genreList);
    }
}
