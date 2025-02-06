package com.eljabiry.movieService.repositories;

import com.eljabiry.movieService.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> getCommentsByMovieMovieId(int movieId, Pageable pageable);

    int countCommentByMovieMovieId(int movieId);
}
