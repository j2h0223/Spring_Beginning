package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
        // 공유되는 변수는 : 동시성 문제로 인해 실무에서는 ConcurrentMap 사용
    private static long sequence = 0L;
        // sequence : 키값을 생성해주는 변수
        // 실무에서도 동시성 고려해야함

    @Override
    public Member save(Member member) {
        member.setId(++sequence);       // 키값 증가후 저장
        store.put(member.getId(), member);  // Map에 저장
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
//        return store.get(id);
            // store.get(id)가 null이면 어떻게 할것인가?
        return Optional.ofNullable(store.get(id));
            // null이어도 한번 랩핑해서 넘겨준다
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
                    // 돌다가 하나라도 찾으면 리턴
    }

    @Override
    public List<Member> findALL() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
        // 테스트를 위한
    }

}

// 테스트 케이스
// 개발한 기능을 실행해서 테스트 할 때
// 자바의 main 메서드를 통해 실행하거나 웹 애플리케이션의 컨트롤러를 통해서 해당 기능 실행
// 하지만 준비/실행까지 오래 걸리고, 반복/다양한 테스트를 한번에 실행하기 어려움
// JUint Framework 이용하여 테스트를 실행하여 문제 해결
