package com.eljabiry.movieService.repositories;

import com.eljabiry.movieService.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieImageRepository extends JpaRepository<MovieImage, Integer> {
}
