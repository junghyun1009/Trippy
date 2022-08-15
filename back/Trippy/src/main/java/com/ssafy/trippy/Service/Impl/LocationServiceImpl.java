package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Dto.Request.RequestDetailLocationDto;
import com.ssafy.trippy.Dto.Response.ResponseDetailLocationDto;
import com.ssafy.trippy.Dto.Response.ResponseLocationDto;
import com.ssafy.trippy.Repository.DetailLocationRepository;
import com.ssafy.trippy.Repository.LocationRepository;
import com.ssafy.trippy.Service.DetailLocationService;
import com.ssafy.trippy.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public List<ResponseLocationDto> findAllLocation() {
        List<Location> locations = locationRepository.findAll();
        List<ResponseLocationDto> locationDtos = new ArrayList<>();
        for (Location location : locations) {
            ResponseLocationDto dto = new ResponseLocationDto(location);
            locationDtos.add(dto);
        }
        return locationDtos;
    }
}