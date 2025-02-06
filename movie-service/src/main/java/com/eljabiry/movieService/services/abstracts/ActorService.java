package com.eljabiry.movieService.services.abstracts;

import com.eljabiry.movieService.entity.Actor;
import com.eljabiry.movieService.dto.ActorRequestDto;

import java.util.List;

public interface ActorService {

    List<String> getActor_actorNameByMovieMovieId(int movieId);

    List<Actor> getall();

    void addActors(ActorRequestDto actorRequestDto);
}
