package com.ssafy.trippy.Service;


import com.ssafy.trippy.Dto.Response.ResponseLocationDto;

import java.util.List;

public interface LocationService {

    List<ResponseLocationDto> findAllLocation();


}
