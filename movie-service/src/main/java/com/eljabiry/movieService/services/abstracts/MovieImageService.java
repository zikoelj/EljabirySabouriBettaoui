package com.eljabiry.movieService.services.abstracts;

import com.eljabiry.movieService.entity.MovieImage;
import com.eljabiry.movieService.dto.ImageRequestDto;


public interface MovieImageService {

    MovieImage addMovieImage(ImageRequestDto imageRequestDto);
}
