package com.dev.aftas.repository;

import com.dev.aftas.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByNum(Integer num);
    Optional<Member> findByName(String name);
    Optional<Member> findByFamilyName(String familyName);
}
