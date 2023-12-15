package com.dev.aftas.repository;

import com.dev.aftas.dto.member.TopMemberDTO;
import com.dev.aftas.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByNum(Integer num);
    Optional<Member> findByName(String name);
    Optional<Member> findByFamilyName(String familyName);
    @Query("SELECT new com.dev.aftas.dto.member.TopMemberDTO(" +
            "M.num, M.name, M.familyName, M.accessionDate, M.nationality, M.identityDocument, M.identityNumber, R.score) " +
            "FROM Member M " +
            "INNER JOIN Ranking R ON M.num = R.id.memberNum " +
            "INNER JOIN Competition C ON R.id.competitionCode = C.code " +
            "WHERE C.code = :code " +
            "ORDER BY R.score DESC")
    List<TopMemberDTO> getTop3MembersInCompetition(@Param("code") String competitionCode);




}
