package com.eljabiry.movieService.services.concretes;

import com.eljabiry.movieService.services.abstracts.MovieSaloonTimeService;
import com.eljabiry.movieService.repositories.MovieSaloonTimeRepository;
import com.eljabiry.movieService.entity.MovieSaloonTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieSaloonTimeServiceImpl implements MovieSaloonTimeService {

    private final MovieSaloonTimeRepository movieSaloonTimeRepository;

    @Override
    public List<MovieSaloonTime> getMovieSaloonTimeSaloonAndMovieId(int saloonId, int movieId) {
        return movieSaloonTimeRepository.getMovieSaloonTimeBySaloonSaloonIdAndMovieMovieId(saloonId, movieId);
    }
}
