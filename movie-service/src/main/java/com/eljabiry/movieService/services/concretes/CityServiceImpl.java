package com.eljabiry.movieService.services.concretes;

import com.eljabiry.movieService.services.abstracts.CityService;
import com.eljabiry.movieService.services.abstracts.MovieService;
import com.eljabiry.movieService.repositories.CityRepository;
import com.eljabiry.movieService.entity.City;
import com.eljabiry.movieService.entity.Movie;
import com.eljabiry.movieService.dto.CityRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final MovieService movieService;
    private final WebClient.Builder webClientBuilder;


    @Override
    public List<City> getCitiesByMovieId(int movieId) {
        return cityRepository.getCitiesByMovieMovieId(movieId);
    }

    @Cacheable(value = "cities")
    @Override
    public List<City> getall() {
        return cityRepository.findAll(Sort.by(Sort.Direction.ASC, "cityName"));
    }

    @CacheEvict(value = "cities", allEntries = true)
    @Override
    public void add(CityRequestDto cityRequestDto) {
        Boolean result = webClientBuilder.build().get()
                .uri("http://USER-SERVICE/api/user/isUserAdmin")
                .header("Authorization", "Bearer " + cityRequestDto.getToken())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (result) {
            Movie movie = movieService.getMovieById(cityRequestDto.getMovieId());
            for (String cityName: cityRequestDto.getCityNameList()) {
                City city = City.builder()
                        .cityName(cityName)
                        .movie(movie)
                        .build();
                cityRepository.save(city);
            }
        }
    }
}
