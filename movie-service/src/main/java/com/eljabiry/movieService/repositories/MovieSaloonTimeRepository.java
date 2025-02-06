package com.eljabiry.movieService.repositories;

import com.eljabiry.movieService.entity.MovieSaloonTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieSaloonTimeRepository extends JpaRepository<MovieSaloonTime, Integer> {

    List<MovieSaloonTime> getMovieSaloonTimeBySaloonSaloonIdAndMovieMovieId(int saloonId, int movieId);

}
