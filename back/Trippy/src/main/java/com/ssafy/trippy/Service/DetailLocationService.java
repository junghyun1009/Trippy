package com.ssafy.trippy.Service;

import com.ssafy.trippy.Dto.Request.RequestDetailLocationDto;
import com.ssafy.trippy.Dto.Response.ResponseDetailLocationDto;

import java.util.List;

public interface DetailLocationService {
    ResponseDetailLocationDto findDetailLocation(Long id);
    Long saveDetailLocation(RequestDetailLocationDto requestDetailLocationDto);
    List<ResponseDetailLocationDto> findDetailLocationByPostId(Long postId);
}
