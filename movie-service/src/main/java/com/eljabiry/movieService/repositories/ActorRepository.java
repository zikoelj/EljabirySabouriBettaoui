package com.eljabiry.movieService.repositories;

import com.eljabiry.movieService.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    @Query("select a.actorName from Actor a  where a.movie.movieId=:movieId ")
    List<String> getActor_actorNameByMovieMovieId(@Param("movieId") int movieId);
}
