package com.example.InstarCloneBe.member.service;

import com.example.InstarCloneBe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.InstarCloneBe.member.entity.Member;
import com.example.InstarCloneBe.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }


    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
