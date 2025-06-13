package fr.movieapp.models;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@Getter
public enum Genre {
    DEFAULT(0, "all"), ACTION(28, "action"), ABENTEUR(12, "abenteur"), ANIMATION(16, "animation"), KOMODIE(35, "komodie"), KRIMI(80, "krimi"),
    DOCUMENTARFILM(99, "documentarfilm"), DRAMA(18, "drama"), FAMILIE(10751, "familie"), FANTASY(14, "fantasy"), HISTORIES(36, "histories"),
    HORROR(27, "horror"), MUSIKMYSTERY(10402, "musikmystery"), LIEBESFILM(10749, "liebesfilm"), SCIENCEFICTION(878, "science fiction"), TVFILM(10770, "tv film"),
    THRILLER(53, "thriller"), KRIEGSFILM(10752, "kriegsfilm"), WESTERN(37, "western");

    private final int id;
    private final String value;

    Genre(int id, String val) {
        this.id = id;
        this.value = val;
    }

    public static Genre getGenre(String val) {
        return Arrays.stream(Genre.values())
                .filter(g -> g.getValue().equals(val))
                .findFirst()
                .orElse(Genre.DEFAULT);
    }

    public static Genre getGenre(int id) {
        return Arrays.stream(Genre.values())
                .filter(g -> g.getId() == id)
                .findFirst()
                .orElse(Genre.DEFAULT);
    }
}
