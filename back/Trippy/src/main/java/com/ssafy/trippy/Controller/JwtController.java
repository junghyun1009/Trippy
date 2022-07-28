package com.ssafy.trippy.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @PostMapping("/api/valid")
    public String isValid(){
        return "검증 성공";
    }
}
