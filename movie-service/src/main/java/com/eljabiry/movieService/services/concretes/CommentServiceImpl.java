package com.eljabiry.movieService.services.concretes;

import com.eljabiry.movieService.services.abstracts.CommentService;
import com.eljabiry.movieService.services.abstracts.MovieService;
import com.eljabiry.movieService.repositories.CommentRepository;
import com.eljabiry.movieService.entity.Comment;
import com.eljabiry.movieService.entity.Movie;
import com.eljabiry.movieService.dto.CommentRequestDto;
import com.eljabiry.movieService.dto.DeleteCommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final MovieService movieService;
    private final WebClient.Builder webClientBuilder;

    @Override
    public List<Comment> getCommentsByMovieId(int movieId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return commentRepository.getCommentsByMovieMovieId(movieId, pageable);
    }

    @Override
    public int getNumberOfCommentsByMovieId(int movieId) {
        return commentRepository.countCommentByMovieMovieId(movieId);
    }

    @Override
    public void deleteComment(DeleteCommentRequestDto deleteCommentRequestDto) {

        Boolean result = webClientBuilder.build().get()
                .uri("http://USER-SERVICE/api/user/users/isUserCustomer")
                .header("Authorization","Bearer " + deleteCommentRequestDto.getToken())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (result) {
            commentRepository.deleteById(deleteCommentRequestDto.getCommentId());
        }

    }

    @Override
    public Comment addComment(CommentRequestDto commentRequestDto) {

        Boolean result = webClientBuilder.build().get()
                .uri("http://USER-SERVICE/api/user/users/isUserCustomer")
                .header("Authorization","Bearer " + commentRequestDto.getToken())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (result) {
            Movie movie = movieService.getMovieById(commentRequestDto.getMovieId());

            Comment comment = Comment.builder()
                    .commentByUserId(commentRequestDto.getCommentByUserId())
                    .commentBy(commentRequestDto.getCommentBy())
                    .commentText(commentRequestDto.getCommentText())
                    .movie(movie)
                    .build();

            return commentRepository.save(comment);
        }
        throw new RuntimeException("Erreur d'autorisation");
    }
}
