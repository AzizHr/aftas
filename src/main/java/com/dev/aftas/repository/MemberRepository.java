package com.dev.aftas.repository;

import com.dev.aftas.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByNum(Integer num);
}
