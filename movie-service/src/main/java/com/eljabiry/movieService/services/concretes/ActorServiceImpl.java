package com.eljabiry.movieService.services.concretes;

import com.eljabiry.movieService.services.abstracts.ActorService;
import com.eljabiry.movieService.services.abstracts.MovieService;
import com.eljabiry.movieService.repositories.ActorRepository;
import com.eljabiry.movieService.entity.Actor;
import com.eljabiry.movieService.entity.Movie;
import com.eljabiry.movieService.dto.ActorRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final MovieService movieService;
    private final WebClient.Builder webClientBuilder;

    @Override
    public List<String> getActor_actorNameByMovieMovieId(int movieId) {

        return actorRepository.getActor_actorNameByMovieMovieId(movieId);


    }

    @Cacheable(value = "actors")
    @Override
    public List<Actor> getall() {
        return actorRepository.findAll(Sort.by(Sort.Direction.ASC, "actorName"));
    }

    @CacheEvict(value = "actors", allEntries = true)
    @Override
    public void addActors(ActorRequestDto actorRequestDto) {

        Boolean result = webClientBuilder.build().get()
                .uri("http://USER-SERVICE/api/user/isUserAdmin")
                .header("Authorization", "Bearer " + actorRequestDto.getToken())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (result) {
            Movie movie = movieService.getMovieById(actorRequestDto.getMovieId());

            for (String actorName : actorRequestDto.getActorNameList()) {
                Actor actor = Actor.builder()
                        .actorName(actorName)
                        .movie(movie)
                        .build();
                actorRepository.save(actor);
            }
        }

    }
}
