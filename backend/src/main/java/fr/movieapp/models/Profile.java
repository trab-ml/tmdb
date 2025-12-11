package fr.movieapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
public class Profile {
    private Optional<Long> id;
    private Optional<Boolean> adult;
    private List<Genre> genreList;
}
