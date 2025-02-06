package com.eljabiry.movieService.services.abstracts;

import com.eljabiry.movieService.entity.Director;
import com.eljabiry.movieService.dto.DirectorRequestDto;

import java.util.List;

public interface DirectorService {

    List<Director> getall();

    Director getDirectorById(int directorId);

    Director add(DirectorRequestDto directorRequestDto);
}
