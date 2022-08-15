package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponseBadgeDto;
import com.ssafy.trippy.Dto.Response.ResponseLocationDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Response.ResponseSavepostDto;
import com.ssafy.trippy.Service.BadgeService;
import com.ssafy.trippy.Service.LocationService;
import com.ssafy.trippy.Service.MemberService;
import com.ssafy.trippy.Service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    private static final String SUCCESS = "OK";
    private static final String FAIL = "ERROR";

    @GetMapping("/locations")
    public ResponseEntity<?> getAllLocation() {
        List< ResponseLocationDto> locationDtos;
        try {
            locationDtos = locationService.findAllLocation();
            return new ResponseEntity<>(locationDtos, HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }

    }

}
