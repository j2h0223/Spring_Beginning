package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByID(Long id);
    Optional<Member> findByName(String name);
    List<Member> findALL();
}
