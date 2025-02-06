package com.eljabiry.movieService.controller;

import com.eljabiry.movieService.services.abstracts.CityService;
import com.eljabiry.movieService.entity.City;
import com.eljabiry.movieService.dto.CityRequestDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/cities/")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("getCitiesByMovieId/{movieId}")
    public List<City> getCitiesByMovieId(@PathVariable int movieId) {
        return cityService.getCitiesByMovieId(movieId);
    }

    @GetMapping("getall")
    public List<City> getall() {
        return cityService.getall();
    }

    @PostMapping("add")
    @CircuitBreaker(name="city", fallbackMethod = "fallback")
    @Retry(name="city")
    public void add(@RequestBody CityRequestDto cityRequestDto) {
        cityService.add(cityRequestDto);
    }

    private void fallback(CityRequestDto cityRequestDto, RuntimeException runtimeException) { }

}
