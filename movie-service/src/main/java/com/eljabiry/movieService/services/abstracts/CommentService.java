package com.eljabiry.movieService.services.abstracts;

import com.eljabiry.movieService.dto.CommentRequestDto;
import com.eljabiry.movieService.entity.Comment;
import com.eljabiry.movieService.dto.DeleteCommentRequestDto;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByMovieId(int movieId, int pageNo, int pageSize);

    void deleteComment(DeleteCommentRequestDto deleteCommentRequestDto);

    Comment addComment(CommentRequestDto commentRequestDto);

    int getNumberOfCommentsByMovieId(int movieId);

}
