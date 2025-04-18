package com.hello.hello_spring;

import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import com.hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memoryMemberRepository());
    }

    @Bean
    public MemberRepository memoryMemberRepository(){
        return new MemoryMemberRepository();
    }
}


// 과거에는 xml로 설정했음

// 컴포넌트 스캔 방식
// 자바 코드로 직접 스프링 빈등록

// DI
// 필드, 생성자, setter 주입
// 필드 주입 별로 안좋음 - 별로 변경할 방법이 없음 시작이후로 내가 변경해줄 방법이 없다
// setter 주입 - setter가 public으로 열려 있어야한다. 이것은 굉장히 불안정함을 초래

// 실무에서 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용
// 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경(데이터 저장소가 선정이 안되어서 나중에 교체 필요 - MemoryMemberRepository를 변경)해야 하면
// 설정(config 파일?)을 통해 스프링 빈으로 등록
// 데이터베이스를 사용하는 레포로 바꿀건데 (구현체를 바꾸게 되는) 기존 맴버 서비스나 나머지 코드에 변경 없이 구현체를 바꾸기 윟마
// config 파일의  DBMemberRepository 로 변경

// Autowired를 통한 스프링이 관리하는 객체에서만 동작
// 스프링빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않음



