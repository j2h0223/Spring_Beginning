package com.hello.hello_spring.service;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
//@Component
public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 테스트 코드에서 동일한 객체를 사용하기 위해

    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        // 외부에서 주입 받도록 변경
            // 테스트 코드를 위해
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     */
    public Long join(Member member) {
        // 동일한 이름이 있는 중복 회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // Optional을 사용할 때 Optional을 바로 반환하는 것은 좋지 않다
//        result.ifPresent(m -> {
//            throw new IllegalStateException("Already exist member name");
//        });

//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("Already exist member name");});
        // 이런 경우는 메서드로 분리시켜라 SRP
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("Already exist member name");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMember() {
        // 서비스는 비즈니스에 가까운 용어를 사용해야한다
        // 비즈니스 의존적으로
        // 레포는 좀더 기계적으로, 개발스럽게 용어를 사용

        return memberRepository.findALL();
    }

    public Optional<Member> findOne(Long memberID) {
        return memberRepository.findByID(memberID);
    }
}
