package com.ssafy.trippy.Service;

import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Dto.Request.RequestPostCommentDto;
import com.ssafy.trippy.Dto.Response.ResponsePostCommentDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;

import java.util.List;

public interface PostCommentService {
    Long savePostComment(RequestPostCommentDto requestPostCommentDto);
    void deletePostComment(RequestPostCommentDto requestPostCommentDto);

    // 게시물에 해당되는 모든 댓글 가져오기
    List<ResponsePostCommentDto> getAllPostComments(Post post);
}
