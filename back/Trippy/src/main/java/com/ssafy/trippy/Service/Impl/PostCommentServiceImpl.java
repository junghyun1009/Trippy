package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Dto.Request.RequestPostCommentDto;
import com.ssafy.trippy.Dto.Response.ResponsePostCommentDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Entity.Post;
import com.ssafy.trippy.Entity.PostComment;
import com.ssafy.trippy.Repository.PostCommentRepository;
import com.ssafy.trippy.Service.PostCommentService;
import jdk.nashorn.internal.ir.RuntimeNode;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {
    private final PostCommentRepository postCommentRepository;

    @Transactional
    @Override
    public Long savePostComment(RequestPostCommentDto requestPostCommentDto) {
        return postCommentRepository.save(requestPostCommentDto.toEntity()).getId();
    }

    @Transactional
    @Override
    public void deletePostComment(RequestPostCommentDto requestPostCommentDto) {
        postCommentRepository.delete(requestPostCommentDto.toEntity());

    }

    // 각 게시물에 해당하는 댓글 불러오기.
    @Transactional(readOnly = true)
    @Override
    public List<ResponsePostCommentDto> getAllPostComments(Post post) {
        List<PostComment> postComment = postCommentRepository.findAllByPostId(post.getId());
        List<ResponsePostCommentDto> responsePostCommentDtos = new ArrayList<>();
        for (PostComment comment : postComment) {
            ResponsePostCommentDto responsePostCommentDto = ResponsePostCommentDto.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .regDt(comment.getRegDt())
                    .post(comment.getPost())
                    .build();
            responsePostCommentDtos.add(responsePostCommentDto);
        }
        return responsePostCommentDtos;
    }
}
