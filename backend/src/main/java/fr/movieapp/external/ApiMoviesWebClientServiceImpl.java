package fr.movieapp.external;

import fr.movieapp.external.dto.MovieApiResponseDto;
import fr.movieapp.mappers.ToModelMapper;
import fr.movieapp.models.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ApiMoviesWebClientServiceImpl implements ApiMoviesWebClientService {
    private final RestTemplate REST_TEMPLATE;
    private final String TMBD_API_KEY;
    private final String TMBD_API_BASE_URL;
    private final String TMBD_MOST_RATED_MOVIES_API_PATH;
    private final String TMDB_POPULAR_MOVIES_API_PATH;
    private final String LANGUAGE;
    private final String SORT_BY;

    public ApiMoviesWebClientServiceImpl(@Value("${TMDB_API_BASE_URL}") String baseUrl,
                                         @Value("${TMDB_MOST_RATED_MOVIES_API_PATH}") String mostRatedMoviesPath,
                                         @Value("${TMDB_POPULAR_MOVIES_API_PATH}") String popularMoviesPath,
                                         @Value("${TMDB_API_KEY}") String apiKey,
                                         @Value("${LANGUAGE}") String language,
                                         @Value("${SORT_BY}") String sortBy) {
        this.TMBD_API_BASE_URL = baseUrl;
        this.TMBD_MOST_RATED_MOVIES_API_PATH = mostRatedMoviesPath;
        this.TMDB_POPULAR_MOVIES_API_PATH = popularMoviesPath;
        this.TMBD_API_KEY = apiKey;
        this.LANGUAGE = language;
        this.SORT_BY = sortBy;
        this.REST_TEMPLATE = new RestTemplate();
    }

    @Override
    public List<Movie> getTopRatedMovies() {
        return fetchMovies(TMBD_API_BASE_URL + TMBD_MOST_RATED_MOVIES_API_PATH +
                "?api_key=" + TMBD_API_KEY +
                "&language=" + LANGUAGE +
                "&sort_by=" + SORT_BY
        );
    }

    @Override
    public List<Movie> getPopularMovies() {
        return fetchMovies(TMBD_API_BASE_URL + TMDB_POPULAR_MOVIES_API_PATH +
                "?api_key=" + TMBD_API_KEY +
                "&language=" + LANGUAGE
        );
    }

    public List<Movie> fetchMovies(String url) {
        try {
            MovieApiResponseDto response = REST_TEMPLATE.getForObject(url, MovieApiResponseDto.class);

            if (response == null) {
                log.warn("api response is null");
                return new ArrayList<>();
            }
            return ToModelMapper.toModel(response);
        } catch (RestClientException ex) {
            throw new RuntimeException("error while fetching movies", ex);
        } catch (Exception ex) {
            log.error("Unexpected error: {}", ex.getMessage(), ex);
            throw new RuntimeException("Unexpected error", ex);
        }
    }
}