package fr.movieapp.controllers.responses;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Getter
public enum GenreResponse {
    DEFAULT(0, "all"), ACTION(28, "action"), ABENTEUR(12, "abenteur"), ANIMATION(16, "animation"), KOMODIE(35, "komodie"), KRIMI(80, "krimi"),
    DOCUMENTARFILM(99, "documentarfilm"), DRAMA(18, "drama"), FAMILIE(10751, "familie"), FANTASY(14, "fantasy"), HISTORIES(36, "histories"),
    HORROR(27, "horror"), MUSIKMYSTERY(10402, "musikmystery"), LIEBESFILM(10749, "liebesfilm"), SCIENCEFICTION(878, "science fiction"), TVFILM(10770, "tv film"),
    THRILLER(53, "thriller"), KRIEGSFILM(10752, "kriegsfilm"), WESTERN(37, "western");

    private final int id;
    private final String value;

    GenreResponse(int id, String val) {
        this.id = id;
        this.value = val;
    }

    public static GenreResponse getGenre(String val) {
        return Arrays.stream(GenreResponse.values())
                .filter(g -> g.getValue().equals(val))
                .findFirst()
                .orElse(GenreResponse.DEFAULT);
    }

    public static Optional<GenreResponse> getGenre(int id) {
        Optional<GenreResponse> genre = Arrays.stream(GenreResponse.values())
                .filter(g -> g.getId() == id)
                .findFirst();

        if (genre.isEmpty()) {
            log.info("Genre {} not found", id);
        }

        return genre;
    }
}
