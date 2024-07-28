package com.example.InstarCloneBe.member.controller;

import com.example.InstarCloneBe.member.dto.UserDto;
import com.example.InstarCloneBe.member.entity.Member;
import com.example.InstarCloneBe.member.service.MemberService;
//import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public UserDto.Read getUserById(@PathVariable Long id) {
        Member member = memberService.findMemberById(id);
        return new UserDto.Read(member);
    }
}
