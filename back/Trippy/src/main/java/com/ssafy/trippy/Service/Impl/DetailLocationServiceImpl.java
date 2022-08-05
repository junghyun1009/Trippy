package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Dto.Request.RequestDetailLocationDto;
import com.ssafy.trippy.Repository.DetailLocationRepository;
import com.ssafy.trippy.Service.DetailLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailLocationServiceImpl implements DetailLocationService {

    private final DetailLocationRepository detailLocationRepository;

    @Override
    public RequestDetailLocationDto findDetailLocation(Long id) {
        DetailLocation detailLocation = detailLocationRepository.findById(id).get();
        RequestDetailLocationDto requestDetailLocationDto = RequestDetailLocationDto.builder()
                .detailLocationContent(detailLocation.getDetailLocationContent())
                .detailLocationName(detailLocation.getDetailLocationName())
                .detailLocationContent(detailLocation.getDetailLocationContent())
                .build();
        return requestDetailLocationDto;
    }

    @Override
    public Long saveDetailLocation(RequestDetailLocationDto requestDetailLocationDto) {
        return detailLocationRepository.save(requestDetailLocationDto.toEntity()).getId();
    }

}
