package fr.movieapp.external;

import fr.movieapp.entities.ProfileEntity;
import fr.movieapp.exceptions.ProfileEntityNotFoundException;
import fr.movieapp.external.dto.MovieApiResponseDto;
import fr.movieapp.external.dto.MovieDto;
import fr.movieapp.models.Genre;
import fr.movieapp.models.Movie;
import fr.movieapp.repositories.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class MoviesApiRestClientServiceImplTest {
    @Mock
    private static ProfileRepository profileRepository;

    @Mock
    private RestTemplate restTemplate;

    private MoviesApiRestClientServiceImpl moviesApiRestClientServiceImpl;

    @BeforeEach
    void setUp() {
        String TMBD_API_KEY = "64a0984238236a5e707ba525be333151";
        String TMBD_API_BASE_URL = "https://api.themoviedb.org/3/";
        String TMBD_MOST_RATED_MOVIES_API_PATH = "movie/top_rated";
        String TMDB_POPULAR_MOVIES_API_PATH = "movie/popular";
        String LANGUAGE = "fr-FR";
        String SORT_BY = "created_at.desc";

        moviesApiRestClientServiceImpl = new MoviesApiRestClientServiceImpl(
                restTemplate,
                TMBD_API_BASE_URL,
                TMBD_MOST_RATED_MOVIES_API_PATH,
                TMDB_POPULAR_MOVIES_API_PATH,
                TMBD_API_KEY,
                LANGUAGE,
                SORT_BY,
                1,
                profileRepository
        );
    }

    @Test
    void whenGetTopRatedMovies_thenReturnMoviesList() {
        //given
        Mockito.when(restTemplate.getForObject(anyString(), any()))
                .thenReturn(MockedData.movieApiResponseDto);

        //when
        List<Movie> moviesList = moviesApiRestClientServiceImpl.getTopRatedMovies();

        //then
        Assertions.assertNotNull(moviesList);
        Assertions.assertEquals(2, moviesList.size());
    }

    @Test
    void whenGetBestMovies_thenReturnMoviesList() {
        //given
        Mockito.when(restTemplate.getForObject(anyString(), any()))
                .thenReturn(MockedData.movieApiResponseDto);

        //when
        List<Movie> moviesList = moviesApiRestClientServiceImpl.getTopRatedMovies();

        //then
        Assertions.assertNotNull(moviesList);
        Assertions.assertEquals(2, moviesList.size());
        Assertions.assertTrue(moviesList.stream()
                .anyMatch(movie -> movie.title().equals(MockedData.BEST_MOVIES_LIST_OF_TWO.getFirst().title())));
    }

    @Test
    void whenSearchByProfile_thenReturnMoviesListBasedOnProfile() throws ProfileEntityNotFoundException {
        //given
        ProfileEntity profileEntity = MockedData.getAnimationAndFantasyAndAbenteurAdultProfileEntity();
        int targetProfileId = Math.toIntExact(profileEntity.getId());
        Mockito.when(profileRepository.findById(targetProfileId))
                .thenReturn(Optional.of(profileEntity));
        Mockito.when(restTemplate.getForObject(anyString(), any()))
                .thenReturn(MockedData.movieApiResponseDto);

        //when
        List<Movie> moviesList = moviesApiRestClientServiceImpl.searchByProfile(targetProfileId);

        //then
        Assertions.assertNotNull(moviesList);
        Assertions.assertEquals(1, moviesList.size());
        Assertions.assertTrue(moviesList.stream()
                .anyMatch(movie -> movie.title()
                        .equals(MockedData.TENDRES_COUSINES_DTO.title())));
    }
}

class MockedData {
    static final List<Genre> DRAMA_GENRE_LIST = List.of(Genre.DRAMA);
    static final List<Genre> ANIMATION_AND_KOMODIE_GENRE_LIST = List.of(Genre.ANIMATION, Genre.KOMODIE);
    static final List<Genre> DRAMA_AND_LIEBESFILM_GENRE_LIST = List.of(Genre.DRAMA, Genre.LIEBESFILM);
    static final List<Genre> ANIMATION_AND_FANTASY_AND_ABENTEUR_GENRE_LIST = List.of(Genre.ANIMATION, Genre.FANTASY, Genre.ABENTEUR);

    static final Movie SOUTH_PARK = new Movie(9473, false, ANIMATION_AND_KOMODIE_GENRE_LIST, "en", "South Park: Bigger, Longer & Uncut", "South Park, le film", "Quatre garnements ont réussi a assister a la projection d'un film canadien de Terance et Phillip interdit au moins de dix-huit ans. Tétanisés de bonheur et transportes au septième ciel, Stan, Kyle, Kenny et Cartman n'ont plus qu'une idée: transmettre leur savoir a leurs copines et copains verts d'envie. Bientôt les enseignants sont impuissants face a l'anarchie qui s'installe. Alertées, les mères s'unissent pour que leurs rejetons se calment mais par leurs méthodes expéditives provoquent la guerre entre le gouvernement canadien et la Maison-Blanche.", "/2q3B90h2hZ6xJTvna9CIFDNaIr4.jpg", LocalDate.parse("1999-06-30"));
    static final Movie THE_HOURS = new Movie(590, false, DRAMA_GENRE_LIST, "en", "The Hours", "The Hours", "Dans la banlieue de Londres, au début des années vingt, Virginia Woolf lutte contre la folie qui la guette. Elle entame l'écriture de son grand roman, Mrs Dalloway. Plus de vingt ans après, à Los Angeles, Laura Brown lit cet ouvrage : une expérience si forte qu'elle songe à changer radicalement de vie. À New York, aujourd'hui, Clarissa Vaughn, version moderne de Mrs Dalloway, soutient Richard, un ami poète atteint du sida. Comment ces histoires vont-elles se rejoindre, comment ces trois femmes vont-elles former une seule et même chaîne ? La littérature est si puissante qu'un chef-d’œuvre peut, par-delà les époques, modifier irrévocablement l'existence de celles qui le côtoient.", "/3oHFSEr4TRnsyTxWGrmY8Xi2FrR.jpg", LocalDate.parse("2002-12-27"));
    static final Movie TENDRE_COUSINES = new Movie(anyInt(), true, DRAMA_GENRE_LIST, "fr", "Tendres Cousines", "Tendres Cousines", "Juin 1939. Julien, quinze ans, vient passer ses grandes vacances dans le domaine campagnard de ses parents. Avec eux se trouvent un savant allemand, le professeur Unrath, et sa fille; Lieselotte. Julien est amoureux de sa cousine Julia, un peu plus âgée que lui, mais celle-ci l'ignore. Charles, un jeune homme d'une vingtaine d'année, fait la cour à Julia. Julien fait tout ce qu'il peut pour séduire sa dulcinée, mais c'est Lieselotte qui lui fait des avances. C'est alors que la guerre éclate ; les hommes doivent partir pour l'armée. Maintenant il y a un peu moins d'hommes, le jeune Julien devient intéressant pour les femmes. Entre autres, il essaie de séduire la femme de chambre, mais sa mère l'en empêche. Au bout du compte, Julien et Julia peuvent se retrouver discrètement et vivre une première fois très romantique.", "/o4IjYTVjfg89bG3oJqUnURp7MgW.jpg", LocalDate.parse("1980-11-19"));
    static final Movie AMELIE_POULETTES = new Movie(Genre.DRAMA.getId(), false, DRAMA_AND_LIEBESFILM_GENRE_LIST, "fr", "Le Fabuleux Destin d'Amélie Poulain", "Le Fabuleux Destin d'Amélie Poulain", "Amélie Poulain est une jeune serveuse dans un bar de Montmartre. Un jour, elle découvre une boîte à souvenirs cachée dans son appartement. Elle décide alors de retrouver le propriétaire de la boîte et de lui rendre ses souvenirs. Touchée par la gratitude de cet inconnu, Amélie se prend au jeu de faire le bien autour d'elle, en aidant les gens de son quartier de manière anonyme. Au fil de ses actions, Amélie découvre l'amour et la joie de vivre.", "/hLQl3WQQoQv45X4kS4cQpXfF1gE.jpg", LocalDate.parse("2001-04-25"));
    static final Movie GOODBY_MONSTER = new Movie(1028318, false, ANIMATION_AND_FANTASY_AND_ABENTEUR_GENRE_LIST, "zh", "山海经之再见怪兽", "Goodbye Monster", "Le monde de Kunlun, composé de milliers d'îles, est menacé par des forces maléfiques. Bai Ze, grand guérisseur, met son île en danger en voulant la protéger et se retrouve banni par ses maîtres. Sept ans plus tard, il y retourne pour se racheter.", "/yGtI321lhUPAoOVIJrm1JI8x7sB.jpg", LocalDate.parse("2022-08-13"));

    static final MovieDto TENDRES_COUSINES_DTO = new MovieDto(590, true, List.of(Genre.DRAMA.getId()), "fr", "Tendres Cousines", "Tendres Cousines", "Juin 1939. Julien, quinze ans, vient passer ses grandes vacances dans le domaine campagnard de ses parents. Avec eux se trouvent un savant allemand, le professeur Unrath, et sa fille; Lieselotte. Julien est amoureux de sa cousine Julia, un peu plus âgée que lui, mais celle-ci l'ignore. Charles, un jeune homme d'une vingtaine d'année, fait la cour à Julia. Julien fait tout ce qu'il peut pour séduire sa dulcinée, mais c'est Lieselotte qui lui fait des avances. C'est alors que la guerre éclate ; les hommes doivent partir pour l'armée. Maintenant il y a un peu moins d'hommes, le jeune Julien devient intéressant pour les femmes. Entre autres, il essaie de séduire la femme de chambre, mais sa mère l'en empêche. Au bout du compte, Julien et Julia peuvent se retrouver discrètement et vivre une première fois très romantique.", "/o4IjYTVjfg89bG3oJqUnURp7MgW.jpg", "1980-11-19");
    static final MovieDto THE_MINUTES_DTO = new MovieDto(600, false, List.of(Genre.DRAMA.getId()), "en", "The Minutes", "The Minutes", "Dans la banlieue de Paris, au début des années quatrevingt, Virginia Woolf lutte contre la folie qui la guette. Elle entame l'écriture de son grand roman, Mrs Dalloway. Plus de vingt ans après, à Los Angeles, Laura Brown lit cet ouvrage : une expérience si forte qu'elle songe à changer radicalement de vie. À New York, aujourd'hui, Clarissa Vaughn, version moderne de Mrs Dalloway, soutient Richard, un ami poète atteint du sida. Comment ces histoires vont-elles se rejoindre, comment ces trois femmes vont-elles former une seule et même chaîne ? La littérature est si puissante qu'un chef-d’œuvre peut, par-delà les époques, modifier irrévocablement l'existence de celles qui le côtoient.", "/3oHFSEr4TRnsyTxWGrmY8Xi2FrR.jpg", "1990-12-27");

    static final List<Movie> BEST_MOVIES_LIST_OF_TWO = List.of(TENDRE_COUSINES, AMELIE_POULETTES);

    static final MovieApiResponseDto movieApiResponseDto = new MovieApiResponseDto(
            List.of(TENDRES_COUSINES_DTO, THE_MINUTES_DTO)
    );

    public static ProfileEntity getAnimationAndFantasyAndAbenteurAdultProfileEntity() {
        ProfileEntity DRAMA_PROFILE_ENTITY = new ProfileEntity();

        DRAMA_PROFILE_ENTITY.setId(1L);
        DRAMA_PROFILE_ENTITY.setAdult(true);
        DRAMA_PROFILE_ENTITY.setGenreList(DRAMA_GENRE_LIST);

        return DRAMA_PROFILE_ENTITY;
    }
}
