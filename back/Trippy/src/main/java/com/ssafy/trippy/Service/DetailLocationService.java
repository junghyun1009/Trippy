package com.ssafy.trippy.Service;

import com.ssafy.trippy.Dto.Request.RequestDetailLocationDto;

public interface DetailLocationService {
    RequestDetailLocationDto findDetailLocation(Long id);
    Long saveDetailLocation(RequestDetailLocationDto requestDetailLocationDto);
}
