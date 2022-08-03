package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Domain.PostComment;
import com.ssafy.trippy.Dto.Request.RequestPostCommentDto;
import com.ssafy.trippy.Dto.Response.ResponsePostCommentDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Repository.MemberRepository;
import com.ssafy.trippy.Repository.PostCommentRepository;
import com.ssafy.trippy.Repository.PostRepository;
import com.ssafy.trippy.Service.PostCommentService;
import jdk.nashorn.internal.ir.RuntimeNode;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostCommentServiceImpl {
    private final PostCommentRepository postCommentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public List<RequestPostCommentDto> findPostCommentByPostId(Long postId){
        postRepository.findById(postId);
        return convertNestedStructure(postCommentRepository.findPostCommentByPostId(postId));
    }

    @Transactional
    public RequestPostCommentDto createComment(ResponsePostCommentDto responsePostCommentDto){
        PostComment comment = postCommentRepository.save(
                PostComment.createComment(responsePostCommentDto.getContent(),
                        postRepository.findById(responsePostCommentDto.getPostId()).get(),
                        memberRepository.findById(responsePostCommentDto.getMemberId()).get(),
                        responsePostCommentDto.getParentId() != null ?
                        postCommentRepository.findById(responsePostCommentDto.getParentId()).get():null)
        );
        return RequestPostCommentDto.convertCommentToDto(comment);
    }



    private List<RequestPostCommentDto> convertNestedStructure(List<PostComment> postComments){
        List<RequestPostCommentDto> result = new ArrayList<>();
        Map<Long, RequestPostCommentDto> map = new HashMap<>();

        postComments.stream().forEach(c -> {
            RequestPostCommentDto dto = RequestPostCommentDto.convertCommentToDto(c);
            map.put(dto.getId(),dto);
            if(c.getParent() != null) map.get(c.getParent().getId()).getChildren().add(dto);
            else result.add(dto);
        });
        return result;
    }

}
