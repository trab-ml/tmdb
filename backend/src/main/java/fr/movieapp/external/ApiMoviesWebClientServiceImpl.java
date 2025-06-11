package fr.movieapp.external;

import fr.movieapp.external.dto.MovieApiResponseDto;
import fr.movieapp.external.dto.MovieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ApiMoviesWebClientServiceImpl implements ApiMoviesWebClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiMoviesWebClientServiceImpl.class);

    private final WebClient webClient;
    private static String TMDB_API_KEY = "";
    private static String TMDB_API_BASE_URL = "";
    private static String TMDB_MOST_RATED_MOVIES_API_PATH = "";

    public ApiMoviesWebClientServiceImpl(@Value("${TMDB_API_BASE_URL}") String apiBaseUrl,
                                         @Value("${TMDB_MOST_RATED_MOVIES_API_URL}") String mostRatedMoviesPath,
                                         @Value("${TMDB_API_KEY}") String apiKey) {
        TMDB_API_BASE_URL = apiBaseUrl;
        TMDB_MOST_RATED_MOVIES_API_PATH = mostRatedMoviesPath;
        TMDB_API_KEY = apiKey;
        this.webClient = WebClient.builder().baseUrl(TMDB_API_BASE_URL).build();
    }

//    Movie
    public List<MovieDto> getTopRatedMovies() {
        Mono<MovieApiResponseDto> responseAsMono = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path(TMDB_MOST_RATED_MOVIES_API_PATH)
                                .queryParam("language", "fr-FR")
                                .queryParam("sort_by", "created_at.desc")
                                .build()
                )
                .retrieve()
                .bodyToMono(MovieApiResponseDto.class);

        try {
            // mapping: ToResponseMapper.
        } catch (HttpClientErrorException except) {
            LOGGER.error("Error encountered during API call", except);
            throw except;
        }

        return List.of();
    }
}
