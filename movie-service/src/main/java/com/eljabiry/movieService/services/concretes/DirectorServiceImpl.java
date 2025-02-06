package com.eljabiry.movieService.services.concretes;

import com.eljabiry.movieService.services.abstracts.DirectorService;
import com.eljabiry.movieService.repositories.DirectorRepository;
import com.eljabiry.movieService.entity.Director;
import com.eljabiry.movieService.dto.DirectorRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final WebClient.Builder webClientBuilder;

    @Cacheable(value = "directors")
    @Override
    public List<Director> getall() {
        return directorRepository.findAll(Sort.by(Sort.Direction.ASC, "directorName"));
    }

    @Override
    public Director getDirectorById(int directorId) {
        return directorRepository.getDirectorByDirectorId(directorId);
    }

    @CacheEvict(value = "directors", allEntries = true)
    @Override
    public Director add(DirectorRequestDto directorRequestDto)
    {
        Boolean result = webClientBuilder.build().get()
                .uri("http://USER-SERVICE/api/user/isUserAdmin")
                .header("Authorization", "Bearer " + directorRequestDto.getToken())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (result) {
            Director director = Director.builder()
                    .directorName(directorRequestDto.getDirectorName())
                    .build();
            return directorRepository.save(director);
        }
        throw new RuntimeException("Yetki hatası");
    }
}
