package fr.movieapp.mappers;

import fr.movieapp.controllers.responses.GenreResponse;
import fr.movieapp.controllers.responses.MovieResponse;
import fr.movieapp.controllers.responses.ProfileResponse;
import fr.movieapp.models.Movie;
import fr.movieapp.models.Profile;

import java.util.List;

public class ToResponseMapper {
    public static MovieResponse toResponse(Movie model) {
        return new MovieResponse(
                model.id(),
                model.adult(),
                model.genreList().stream()
                        .map(g -> GenreResponse.getGenre(g.getValue()))
                        .toList(),
                model.originalLanguage(),
                model.originalTitle(),
                model.title(),
                model.overview(),
                model.coverImgUrl(),
                model.releaseDate()
        );
    }

    public static List<MovieResponse> toResponse(List<Movie> movieList) {
        return movieList.stream()
                .map(ToResponseMapper::toResponse)
                .toList();
    }

    public static ProfileResponse toResponse(Profile p) {
        return new ProfileResponse(p.getId(), p.getAdult(), p.getGenreList());
    }

    public static List<ProfileResponse> profilesToResponse(List<Profile> profiles) {
        return profiles.stream()
                .map(ToResponseMapper::toResponse)
                .toList();
    }
}
