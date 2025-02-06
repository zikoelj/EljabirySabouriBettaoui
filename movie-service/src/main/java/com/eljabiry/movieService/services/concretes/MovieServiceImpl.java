package com.eljabiry.movieService.services.concretes;

import com.eljabiry.movieService.services.abstracts.CategoryService;
import com.eljabiry.movieService.services.abstracts.DirectorService;
import com.eljabiry.movieService.services.abstracts.MovieService;
import com.eljabiry.movieService.repositories.MovieRepository;
import com.eljabiry.movieService.entity.Category;
import com.eljabiry.movieService.entity.Director;
import com.eljabiry.movieService.entity.Movie;
import com.eljabiry.movieService.dto.MovieRequestDto;
import com.eljabiry.movieService.dto.MovieResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final DirectorService directorService;
    private final WebClient.Builder webClientBuilder;

    @Cacheable(value = "displaying_movies")
    @Override
    public List<MovieResponseDto> getAllDisplayingMoviesInVision() {
        return movieRepository.getAllDisplayingMoviesInVision();
    }

    @Cacheable(value = "comingSoon_movies")
    @Override
    public List<MovieResponseDto> getAllComingSoonMovies() {
        return movieRepository.getAllComingSoonMovies();
    }

    @Override
    public MovieResponseDto getMovieByMovieId(int movieId) {
        return movieRepository.getMovieById(movieId);
    }

    @Override
    public Movie getMovieById(int movieId) {
        return movieRepository.getMovieByMovieId(movieId);
    }

    @CacheEvict(value = "comingSoonMovie", allEntries = true)
    @Override
    public Movie addMovie(MovieRequestDto movieRequestDto) {

        Boolean result = webClientBuilder.build().get()
                .uri("http://USER-SERVICE/api/user/users/isUserAdmin")
                .header("Authorization", "Bearer " + movieRequestDto.getUserAccessToken())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (result) {
            Category category = categoryService.getCategoryById(movieRequestDto.getCategoryId());
            Director director = directorService.getDirectorById(movieRequestDto.getDirectorId());

            Movie movie = Movie.builder()
                    .movieName(movieRequestDto.getMovieName())
                    .description(movieRequestDto.getDescription())
                    .duration(movieRequestDto.getDuration())
                    .releaseDate(movieRequestDto.getReleaseDate())
                    .movieTrailerUrl(movieRequestDto.getTrailerUrl())
                    .category(category)
                    .director(director)
                    .isDisplay(movieRequestDto.isInVision())
                    .build();
            return movieRepository.save(movie);
        }

        throw new RuntimeException("Erreur d'authentification");
    }
}
