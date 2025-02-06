package com.eljabiry.movieService.controller;

import com.eljabiry.movieService.services.abstracts.ActorService;
import com.eljabiry.movieService.entity.Actor;
import com.eljabiry.movieService.dto.ActorRequestDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/actors/")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping("getActorsByMovieId/{movieId}")
    public List<String> getActorsByMovieId(@PathVariable int movieId) {
       return actorService.getActor_actorNameByMovieMovieId(movieId);
    }

    @GetMapping("getall")
    public List<Actor> getall() {
       return actorService.getall();
    }

    @PostMapping("add")
    @CircuitBreaker(name="actor", fallbackMethod="fallback")
    @Retry(name="actor")
    public void add(@RequestBody ActorRequestDto actorRequestDto) {
        actorService.addActors(actorRequestDto);
    }

    private void fallback(ActorRequestDto actorRequestDto, RuntimeException runtimeException) { }

}
