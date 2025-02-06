package com.eljabiry.movieService.repositories;

import com.eljabiry.movieService.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> getCitiesByMovieMovieId(int movieId);

}
