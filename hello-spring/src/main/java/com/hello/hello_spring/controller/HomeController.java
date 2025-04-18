package com.hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}

// 웰컴페이지의 우선순위
// 정적컨텐츠
    // 요청이 오면 먼저 관련된 스프링 컨트롤러를 찾고
    // 없으면 static을 찾는다
