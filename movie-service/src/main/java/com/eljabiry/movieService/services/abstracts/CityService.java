package com.eljabiry.movieService.services.abstracts;

import com.eljabiry.movieService.entity.City;
import com.eljabiry.movieService.dto.CityRequestDto;

import java.util.List;

public interface CityService {

    List<City> getCitiesByMovieId(int movieId);

    List<City> getall();

    void add(CityRequestDto cityRequestDto);
}
