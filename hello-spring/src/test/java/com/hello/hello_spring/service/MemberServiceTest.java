package com.hello.hello_spring.service;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
//    MemberService memberService = new MemberService();
//
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
//    // 애매한 부분이 있다
//        // 서비스에서도 MemoryMemberRepository를 생성하고
//        // 테스트에서도 MemoryMemberRepository를 생성한다
//            // 다른 객체(DB)이다
//                // 두개를 쓸 이유가 없다
//                // 동일한 객체를 사용하는 것이 좋지
//                    // 내용물이 달라지거나 할 수 있다


    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    // 동작하기 전에 개별 실행
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // MemberService 입장에서 DI 이다
    }


    @AfterEach
    public void afterEach(){
        // 데이터 클리어 시켜주기 위한 콜백 메서드
        // 하나의 테스트가 끝나면 메서드가 실행
        memberRepository.clearStore();
        // 이것도 인터에서 해주나?
    }

    @Test
//    void join() {
    void 회원가입() {
        // 테스트 코드는 한국어로 해도된다
            // production 코드가 나가는게 아니다
            // 빌드될 때 실제 코드에 포함되지 않음

        // given - when - then 문법
            // 무엇인가 주어졌는데 - 실행했을 때 - 이것이 나와야 한다
            // 테스트가 길어질 때 가독성이 좋다
                // 주석을 이용해 나누는 것만으로도 좋다

        // given
        Member member = new Member();
//        member.setName("Hello");
        member.setName("Spring");

        // when
        Long saveID = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveID).get();
            // 저장한 맴버가 레포에 있는지 확인하는 테스트
        assertThat(member.getName()).isEqualTo(findMember.getName());
        // 근데 너무 단순하다
        // 테스트는 예외가 더 중요하다
        // 조인의 핵심은 저장이 되는지도 중요하지만
        // 중복 회원 검증 로직이 잘 작동하는지도 중요하다
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("Spring");
//        member1.setName("Hello");
            /// / 얘를 Hello로 하거나 위에 회원가입에서 Spring으로 바뀌면 테스트 통과가 되지않는다
                // join해서 저장이 누적되고 있기 때문
                // 여기도 클리어 필요
                    // 하지만 memberservice 밖에 없다
                        // 그래서 memberrepository를 가져와서 클리어 해줌

        Member member2 = new Member();
        member2.setName("Spring");

        // when
        memberService.join(member1);

//        try{
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다");
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("Already exist member name!");
////            assertThat(e.getMessage()).isEqualTo("Already exist member name!");
                // 얘는 다르다.
//        }
            // validationDuplicateMember()에서 예외가 발생되어야 한다
                // try-catch로 잡는 방법이 있다
                    // 하지만 애매한 부분이 있다

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("Already exist member name");
        // 람다식을 해야하는데
                // 앞부분 Exception.이 발생해야 한다
            // 반환도 된다
//        assertThrows(NullPointerException.class, () -> memberService.join(member2));
            // 얘는 실패한다

        // then

    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}