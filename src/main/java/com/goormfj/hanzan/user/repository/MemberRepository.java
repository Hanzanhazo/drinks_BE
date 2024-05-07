package com.goormfj.hanzan.user.repository;

import com.goormfj.hanzan.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsMemberByName(String name); // 이름 존재 체크

    Boolean existsMemberByEmail(String email); // 이메일 존재 체크

    Boolean existsMemberByUserId(String userId); // 회원 아이디 존재 체크

    Member findMemberByEmail(String email); // 이메일로 회원 정보 조회

    Member findMemberByUserId(String userId); // 아이디로 회원 정보 조회

    Boolean existsByNameAndEmail(String name, String email); // 사용자 이름과 이메일로 존재 여부 확인 (쿼리 메소드 사용)

    Member findMemberByNameAndEmail(String name, String email);


}
