package com.eljabiry.movieService.services.abstracts;

import com.eljabiry.movieService.entity.Movie;
import com.eljabiry.movieService.dto.MovieRequestDto;
import com.eljabiry.movieService.dto.MovieResponseDto;

import java.util.List;

public interface MovieService {

    List<MovieResponseDto> getAllDisplayingMoviesInVision();

    List<MovieResponseDto> getAllComingSoonMovies();

    MovieResponseDto getMovieByMovieId(int movieId);

    Movie getMovieById(int movieId);

    Movie addMovie(MovieRequestDto movieRequestDto);
}
