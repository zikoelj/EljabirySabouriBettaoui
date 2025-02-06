package com.eljabiry.movieService.services.concretes;

import com.eljabiry.movieService.services.abstracts.MovieImageService;
import com.eljabiry.movieService.services.abstracts.MovieService;
import com.eljabiry.movieService.repositories.MovieImageRepository;
import com.eljabiry.movieService.entity.Movie;
import com.eljabiry.movieService.entity.MovieImage;
import com.eljabiry.movieService.dto.ImageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class MovieImageServiceImpl implements MovieImageService {

    private final MovieImageRepository movieImageRepository;
    private final MovieService movieService;
    private final WebClient.Builder webClientBuilder;


    @Override
    public MovieImage addMovieImage(ImageRequestDto imageRequestDto) {

        Boolean result = webClientBuilder.build().get()
                .uri("http://USER-SERVICE/api/user/isUserAdmin")
                .header("Authorization", "Bearer " + imageRequestDto.getToken())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (result) {
            Movie movie = movieService.getMovieById(imageRequestDto.getMovieId());

            MovieImage image = new MovieImage();
            image.setImageUrl(image.getImageUrl());
            image.setMovie(movie);

            return movieImageRepository.save(image);
        }
        throw new RuntimeException("Yetki hatası");
    }
}
