package com.eljabiry.movieService.controller;

import com.eljabiry.movieService.services.abstracts.MovieService;
import com.eljabiry.movieService.entity.Movie;
import com.eljabiry.movieService.dto.MovieRequestDto;
import com.eljabiry.movieService.dto.MovieResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/movies/")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("displayingMovies")
    public List<MovieResponseDto> getAllDisplayingMoviesInVision() {
        return movieService.getAllDisplayingMoviesInVision();
    }

    @GetMapping("comingSoonMovies")
    public List<MovieResponseDto> getAllComingSoonMovies() {
        return movieService.getAllComingSoonMovies();
    }

    @GetMapping("{movieId}")
    public MovieResponseDto getMovieById(@PathVariable int movieId) {
        return movieService.getMovieByMovieId(movieId);
    }

    @PostMapping("add")
    @CircuitBreaker(name="movie", fallbackMethod = "fallback")
    @Retry(name="movie")
    public Movie addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        return movieService.addMovie(movieRequestDto);
    }

    private Movie fallback(MovieRequestDto movieRequestDto, RuntimeException runtimeException) throws RuntimeException {
        return null;
    }

}