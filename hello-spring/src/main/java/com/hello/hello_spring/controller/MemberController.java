package com.hello.hello_spring.controller;


import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

//    @Autowired    // 필드 주입
    private MemberService memberService;

    @Autowired      // 생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @Autowired      // setter 주입
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//    }

    @GetMapping("/members/new")
    // 주소창에 url을 입력하면 get방식으로 들어오는것
    public String CreateForm(){
        return "members/createMemberForm";
        // 여기로 이동
        // 템플릿에서 members/createMemberForm가 뷰리졸브를 통해서 선택이되고
        // thymeleaf 텔플릿 엔진이 렌더링함 members/createMemberForm.html을
        // html이 뿌려진다
    }

    @PostMapping("/members/new")
    // postmapping 데이터를 폼에 넣어 전달하는 방식 - 일반적인 등록방식
    // get은 조회할 때 사용
    // get이냐 post냐에 따라 다르게 맵핑할 수 있음
    public String Create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

//        System.out.println("member.getName() = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

}
