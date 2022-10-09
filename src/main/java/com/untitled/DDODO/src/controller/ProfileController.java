package com.untitled.DDODO.src.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProfileController {

    private Environment env;

    @GetMapping("/profile")
    public String profile () {
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        // env.getActiveProfiles() - 현재 실행중인 ActiveProfile을 모두 가져옴
        // ex) real, oauth, real-db 등이 활성화되어 있으면 3개 모두 담겨있음
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");

        // real, real1, real2 모두 배포에 사용될 profile이라 이 중 하나라도 있으면 그 값을 반환하도록 함
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }

}
