package com.hello.hello_spring.controller;


public class MemberForm {
    private String name;
    // 이 속성에 스프링이 post 데이터를 넣어준다
    // setname을 스프링을 통해 넣어준다

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
