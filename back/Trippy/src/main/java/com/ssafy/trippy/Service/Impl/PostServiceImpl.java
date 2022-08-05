package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.*;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Update.UpdatePostDto;
import com.ssafy.trippy.Repository.*;
import com.ssafy.trippy.Service.PostService;
import com.sun.mail.iap.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final DetailLocationRepository detailLocationRepository;
    private final PostTransportRepository postTransportRepository;

    private final TransportRepository transportRepository;

    private final Long busId = 1L;
    private final Long walkId = 2L;
    private final Long taxiId = 3L;




    // 모든 post찾기
    @Transactional(readOnly = true)
    @Override
    public List<ResponsePostDto> findAll() {
        List<Post> all = postRepository.findAll();
        List<ResponsePostDto> responsePostDtoList = new ArrayList<>();
        for (Post post : all) {
            ResponsePostDto dto = ResponsePostDto.builder()
                    .id(post.getId())
                    .company(post.getCompany())
                    .endDate(post.getEndDate())
                    .count(post.getCount())
                    .memberId(post.getMember().getId())
                    .postTransports(post.getPostTransports())
                    .detailLocations(post.getDetailLocations())
                    .startDate(post.getStartDate())
                    .title(post.getTitle())
                    .isDelete(post.getIsDelete())
                    .representiveImg(post.getRepresentiveImg()).build();
            responsePostDtoList.add(dto);
        }
        return responsePostDtoList;
    }

    // 유저(본인)가 작성한 게시글 조회
    @Transactional(readOnly = true)
    @Override
    public List<ResponsePostDto> findAllByMember(Member member) {
        List<Post> posts = postRepository.findAllByMember(member);
        List<ResponsePostDto> responsePostDtoUserList = new ArrayList<>();
        for (Post post : posts) {
            ResponsePostDto dto = ResponsePostDto.builder()
                    .id(post.getId())
                    .company(post.getCompany())
                    .endDate(post.getEndDate())
                    .count(post.getCount())
                    .memberId(post.getMember().getId())
                    .postTransports(post.getPostTransports())
                    .detailLocations(post.getDetailLocations())
                    .startDate(post.getStartDate())
                    .title(post.getTitle())
                    .isDelete(post.getIsDelete())
                    .representiveImg(post.getRepresentiveImg()).build();
            responsePostDtoUserList.add(dto);
        }
        return responsePostDtoUserList;
    }

    // post 등록
    @Transactional
    @Override
    public void savePost(RequestPostDto requestPostDto) {
        Member member = memberRepository.findById(requestPostDto.getMember_id()).get();
        requestPostDto.setMember_id(member.getId());
        Post post = postRepository.save(requestPostDto.toEntity());

        System.out.println("requestPostDto = " + requestPostDto.getPostTransports().get(0).getTransport().getName());
        System.out.println("requestPostDto = " + requestPostDto.getPostTransports().get(0).getTransport().getId());

        for (DetailLocation detailLocation:requestPostDto.toEntity().getDetailLocations()){
            detailLocation.setPost(post);
            detailLocationRepository.save(detailLocation);
        }
        for(PostTransport postTransport:requestPostDto.toEntity().getPostTransports()){
            postTransport.setPost(post);
            postTransportRepository.save(postTransport);
        }

    }

    // post 삭제
    @Transactional
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // post 수정
    @Transactional
    @Override
    public void updatePost(Long id, RequestPostDto requestPostDto) {
        Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        List<DetailLocation> detailLocationList = post.getDetailLocations();

        // PostTransport 테이블에도 수정된 값 넣어주기
        for (PostTransport postTransport : requestPostDto.toEntity().getPostTransports()) {
            if(postTransport.getTransport().getName().equals("버스")){
                postTransport.getTransport().builder().id(busId).build();
            }else if(postTransport.getTransport().getName().equals("택시")) {
                postTransport.getTransport().builder().id(walkId).build();
            }else {
                postTransport.getTransport().builder().id(taxiId).build();
            }
        }



        // detailLocation 테이블에도 수정된 값 넣어주기
        List<DetailLocation> detailLocation = requestPostDto.toEntity().getDetailLocations();
        List<DetailLocation> detailLocations = new ArrayList<>();
        for(DetailLocation detail:detailLocation){
            detail.setPost(post);
            detailLocations.add(detail);
        }

        for (int i = 0; i < detailLocation.size(); i++) {
            detailLocationList.get(i).update(detailLocations.get(i).getDetailLocationContent(),
                    detailLocations.get(i).getDetailLocationName(),
                    detailLocations.get(i).getRating(),
                    detailLocations.get(i).getImgPath());
        }

        // PostTransport 테이블에도 수정된 값 넣어주기
        List<PostTransport> oldPostTransport = postTransportRepository.findAllPostTransportByPost(post);
        List<PostTransport> postTransport = requestPostDto.toEntity().getPostTransports();
        List<PostTransport> changePostTransportList= new ArrayList<>();
        for(PostTransport postTrans:postTransport){
            postTrans.setPost(post);
            changePostTransportList.add(postTrans);
        }
        System.out.println("changePostTransportList.toString() = " + changePostTransportList.toString());

        for (int i = 0; i < postTransport.size(); i++) {
            oldPostTransport.get(i).update(changePostTransportList.get(i).getTransport());
        }

        post.update(
                requestPostDto.getTitle(),
                requestPostDto.getIsDelete(),
                requestPostDto.getCompany(),
                requestPostDto.getCount(),
                requestPostDto.getStartDate(),
                requestPostDto.getEndDate(),
                requestPostDto.getRepresentiveImg(),
                postTransport,
                detailLocations);
//        System.out.println(postTransportRepository.findAll().toString());
        }


    @Override
    public ResponsePostDto findPostId(Long id) {
        Post post= postRepository.findById(id).get();
        return new ResponsePostDto(post);
    }

}
