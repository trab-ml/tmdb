package fr.movieapp.external;

import fr.movieapp.external.dto.MovieApiResponseDto;
import fr.movieapp.external.dto.MovieDto;
import fr.movieapp.models.Genre;
import fr.movieapp.models.Movie;
import fr.movieapp.repositories.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Disabled
public class MoviesApiRestClientServiceImplTest {
    @Mock private RestTemplate restTemplate;
     private String TMBD_API_KEY;
     private String TMBD_API_BASE_URL;
     private String TMBD_MOST_RATED_MOVIES_API_PATH;
     private String TMDB_POPULAR_MOVIES_API_PATH;
     private String LANGUAGE;
     private String SORT_BY;
    @Mock static private ProfileRepository profileRepository;

    @InjectMocks
    private MoviesApiRestClientServiceImpl moviesApiRestClientServiceImpl = new MoviesApiRestClientServiceImpl(restTemplate,
            "https://api.themoviedb.org/3/",
            "movie/top_rated",
            "movie/popular",
            "64a0984238236a5e707ba525be333151",
            "fr-FR",
            "created_at.desc",
            1,
            profileRepository);

    private int BEST_MOVIES_TOTAL_PAGES;

    private static final int HUNDRED_FIFTY_FOUR_ID = 654;
    private static final List<Genre> DRAMA_GENRE_LIST = List.of(
            Genre.DRAMA
    );

    private static final List<Genre> ANIMATION_AND_KOMODIE_GENRE_LIST = List.of(
            Genre.ANIMATION,
            Genre.KOMODIE
    );

    private static final List<Genre> DRAMA_AND_LIEBESFILM_GENRE_LIST = List.of(
            Genre.DRAMA,
            Genre.LIEBESFILM
    );

    private static final List<Genre> ANIMATION_AND_FANTASY_AND_ABENTEUR_GENRE_LIST = List.of(
            Genre.ANIMATION,
            Genre.FANTASY,
            Genre.ABENTEUR
    );

    private static final Movie SOUTH_PARK = new Movie(9473,
            false,
            ANIMATION_AND_KOMODIE_GENRE_LIST,
            "en",
            "South Park: Bigger, Longer & Uncut",
            "South Park, le film",
            "Quatre garnements ont réussi a assister a la projection d'un film canadien de Terance et Phillip interdit au moins de dix-huit ans. Tétanisés de bonheur et transportes au septième ciel, Stan, Kyle, Kenny et Cartman n'ont plus qu'une idée: transmettre leur savoir a leurs copines et copains verts d'envie. Bientôt les enseignants sont impuissants face a l'anarchie qui s'installe. Alertées, les mères s'unissent pour que leurs rejetons se calment mais par leurs méthodes expéditives provoquent la guerre entre le gouvernement canadien et la Maison-Blanche.",
            "/2q3B90h2hZ6xJTvna9CIFDNaIr4.jpg",
            LocalDate.parse("1999-06-30"));

    private static final Movie THE_HOURS = new Movie(590,
            false,
            DRAMA_GENRE_LIST,
            "en",
            "The Hours",
            "The Hours",
            "Dans la banlieue de Londres, au début des années vingt, Virginia Woolf lutte contre la folie qui la guette. Elle entame l'écriture de son grand roman, Mrs Dalloway. Plus de vingt ans après, à Los Angeles, Laura Brown lit cet ouvrage : une expérience si forte qu'elle songe à changer radicalement de vie. À New York, aujourd'hui, Clarissa Vaughn, version moderne de Mrs Dalloway, soutient Richard, un ami poète atteint du sida. Comment ces histoires vont-elles se rejoindre, comment ces trois femmes vont-elles former une seule et même chaîne ? La littérature est si puissante qu'un chef-d’œuvre peut, par-delà les époques, modifier irrévocablement l'existence de celles qui le côtoient.",
            "/3oHFSEr4TRnsyTxWGrmY8Xi2FrR.jpg",
            LocalDate.parse("2002-12-27"));

    private static final List<Movie> TOP_RATED_MOVIES_LIST_OF_TWO = List.of(SOUTH_PARK, THE_HOURS);

    private static final List<Movie> BEST_MOVIES_LIST_OF_TWO = List.of(
            new Movie(73134,
                    false,
                    DRAMA_AND_LIEBESFILM_GENRE_LIST,
                    "fr",
                    "Tendres Cousines",
                    "Tendres Cousines",
                    "Juin 1939. Julien, quinze ans, vient passer ses grandes vacances dans le domaine campagnard de ses parents. Avec eux se trouvent un savant allemand, le professeur Unrath, et sa fille; Lieselotte. Julien est amoureux de sa cousine Julia, un peu plus âgée que lui, mais celle-ci l'ignore. Charles, un jeune homme d'une vingtaine d'année, fait la cour à Julia. Julien fait tout ce qu'il peut pour séduire sa dulcinée, mais c'est Lieselotte qui lui fait des avances. C'est alors que la guerre éclate ; les hommes doivent partir pour l'armée. Maintenant il y a un peu moins d'hommes, le jeune Julien devient intéressant pour les femmes. Entre autres, il essaie de séduire la femme de chambre, mais sa mère l'en empêche. Au bout du compte, Julien et Julia peuvent se retrouver discrètement et vivre une première fois très romantique.",
                    "/o4IjYTVjfg89bG3oJqUnURp7MgW.jpg",
                    LocalDate.parse("1980-11-19")
    ));

    private static final List<Movie> BEST_MOVIES_BASED_ON_PROFILE_LIST_OF_ONE = List.of(
            new Movie(1028318,
                    false,
                    ANIMATION_AND_FANTASY_AND_ABENTEUR_GENRE_LIST,
                    "zh",
                    "山海经之再见怪兽",
                    "Goodbye Monster",
                    "Le monde de Kunlun, composé de milliers d'îles, est menacé par des forces maléfiques. Bai Ze, grand guérisseur, met son île en danger en voulant la protéger et se retrouve banni par ses maîtres. Sept ans plus tard, il y retourne pour se racheter.",
                    "/yGtI321lhUPAoOVIJrm1JI8x7sB.jpg",
                    LocalDate.parse("2022-08-13")
    ));

    private static final MovieApiResponseDto movieApiResponseDto = new MovieApiResponseDto(List.of(
            new MovieDto(590,
                    false,
                    List.of(Genre.DRAMA.getId()),
                    "en",
                    "The Hours",
                    "The Hours",
                    "Dans la banlieue de Londres, au début des années vingt, Virginia Woolf lutte contre la folie qui la guette. Elle entame l'écriture de son grand roman, Mrs Dalloway. Plus de vingt ans après, à Los Angeles, Laura Brown lit cet ouvrage : une expérience si forte qu'elle songe à changer radicalement de vie. À New York, aujourd'hui, Clarissa Vaughn, version moderne de Mrs Dalloway, soutient Richard, un ami poète atteint du sida. Comment ces histoires vont-elles se rejoindre, comment ces trois femmes vont-elles former une seule et même chaîne ? La littérature est si puissante qu'un chef-d’œuvre peut, par-delà les époques, modifier irrévocablement l'existence de celles qui le côtoient.",
                    "/3oHFSEr4TRnsyTxWGrmY8Xi2FrR.jpg",
                    "2002-12-27")));

    @Test
    @Order(1)
    void whenGetTopRatedMovies_thenReturnMoviesList() {
        //given
//        Mockito.when(moviesApiRestClientServiceImpl.getTopRatedMovies())
//                .thenReturn(TOP_RATED_MOVIES_LIST_OF_TWO);
//        doReturn(movieApiResponseDto).when(restTemplate).getForObject(any(String.class), any());

        //when
        List<Movie> moviesList = moviesApiRestClientServiceImpl.getTopRatedMovies();

        //then
        Assertions.assertNotNull(moviesList);
        Assertions.assertEquals(10, moviesList.size());
    }

//    @Test
//    @Disabled
//    void whenGetBestMovies_thenReturnMoviesList() {
//        //given
//        Mockito.when(moviesApiRestClientServiceImpl.getTopRatedMovies())
//                .thenReturn(BEST_MOVIES_LIST_OF_TWO);
//
//        //when
//        List<Movie> moviesList = moviesApiRestClientServiceImpl.getTopRatedMovies();
//
//        //then
//        Assertions.assertNotNull(moviesList);
//        Assertions.assertEquals(BEST_MOVIES_LIST_OF_TWO.size(), moviesList.size());
//        Assertions.assertTrue(moviesList.stream()
//                .anyMatch(movie -> movie.title().equals(BEST_MOVIES_LIST_OF_TWO.getFirst().title())));
//    }
//
//    @Test
//    @Disabled
//    void whenSearchByProfile_thenReturnMoviesListBasedOnProfile() throws ProfileEntityNotFoundException {
//        //given
//        Mockito.when(moviesApiRestClientServiceImpl.searchByProfile(any()))
//                .thenReturn(BEST_MOVIES_BASED_ON_PROFILE_LIST_OF_ONE);
//
//        //when
//        List<Movie> moviesList = moviesApiRestClientServiceImpl.searchByProfile(HUNDRED_FIFTY_FOUR_ID);
//
//        //then
//        Assertions.assertNotNull(moviesList);
//        Assertions.assertEquals(BEST_MOVIES_BASED_ON_PROFILE_LIST_OF_ONE.size(), moviesList.size());
//        Assertions.assertTrue(moviesList.stream()
//                .anyMatch(movie -> movie.title().equals(BEST_MOVIES_BASED_ON_PROFILE_LIST_OF_ONE.getFirst().title())));
//    }
}
