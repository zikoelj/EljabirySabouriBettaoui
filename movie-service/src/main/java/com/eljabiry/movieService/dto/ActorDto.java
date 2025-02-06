package com.eljabiry.movieService.dto;

import com.eljabiry.movieService.entity.ActorImage;
import com.eljabiry.movieService.entity.Movie;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActorDto {

    private int actorId;

    private String actorName;

    //private Movie movie;

    private ActorImage actorImage;
}
