package com.eljabiry.movieService.services.abstracts;

import com.eljabiry.movieService.entity.MovieSaloonTime;

import java.util.List;

public interface MovieSaloonTimeService {

    List<MovieSaloonTime> getMovieSaloonTimeSaloonAndMovieId(int saloonId, int movieId);
}
