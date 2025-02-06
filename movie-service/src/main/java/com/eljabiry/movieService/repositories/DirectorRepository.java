package com.eljabiry.movieService.repositories;

import com.eljabiry.movieService.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DirectorRepository extends JpaRepository<Director, Integer> {

    Director getDirectorByDirectorId(int directorId);
}
