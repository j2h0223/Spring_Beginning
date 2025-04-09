package com.hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam("name") String name, Model model){
        // 위와 다름
            // 위는 코드에서 지정
        // 파라미터로 받아서 리턴
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API방식
    @GetMapping("hello-string")
    @ResponseBody
    // HTTP 응답 body 부분에 return 값을 넣어주는
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    // Data를 받을려고 할때 주로 API방식을 많이 쓴다
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // json 파일의 형태로 전달된다
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
