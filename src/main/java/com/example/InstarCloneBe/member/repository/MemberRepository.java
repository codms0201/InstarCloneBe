package com.example.InstarCloneBe.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.InstarCloneBe.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByUid(String uid);

    Optional<Member> findByNickname(String nickname);
}
