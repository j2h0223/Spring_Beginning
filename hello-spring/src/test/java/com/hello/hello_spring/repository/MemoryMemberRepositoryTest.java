package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    // 명명은 뒤에 Test를 붙임

//    MemberRepository repository = new MemoryMemberRepository();
    MemoryMemberRepository repository = new MemoryMemberRepository();
        // afterEach를 위해

    @AfterEach
    public void afterEach(){
        // 데이터 클리어 시켜주기 위한 콜백 메서드
        // 하나의 테스트가 끝나면 메서드가 실행
        repository.clearStore();
            // 이것도 인터에서 해주나?
    }



    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findByID(member.getId()).get();

//        System.out.println("result = " + (result == member));
        // 저장하는 객체와 저장된 객체를 비교
            // 참조 주소값이 같을 것이다
//        Assertions.assertEquals(null, member);
//        Assertions.assertEquals(result, member);
            // 얘는 JUnit
        assertThat(member).isEqualTo(result);
            // 얘는 assertj
                // 실무에서는 빌드툴에서 엮어서 빌드툴에서 테스트케이스를 통과하지 못하면 다음단계로 넘어가지 못한다
//        Assertions.assertThat(member).isEqualTo(result);
            // static import : 옵션 엔터
//        assertThat(member).isEqualTo(null);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();
//        Member result = repository.findByName("Spring2").get();

        assertThat(result).isEqualTo(member1);
//        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring1");
        repository.save(member2);

        List<Member> result = repository.findALL();

        assertThat(result.size()).isEqualTo(2);
    }

    // 여기까지 전체 테스트를 실행하면
    // findByName은 오류가 발생
    // 실행 순서가 보장되지 않음
    // 하지만 테스트 순서에 따라 결과가 달라지면 안된다
        // 따라서 테스트가 끝나고 나면 데이터를 클리어해줘야한다
}


// 레포를 개발 후 테스트 작성
// 뒤집어서 테스트 먼저 작성후 레포를 작성
    // 테스트를 위한 틀을 먼저 작성
    // 테스트 주도 개발 - TDD