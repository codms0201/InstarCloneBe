package com.example.InstarCloneBe.User.controller;

import com.example.InstarCloneBe.User.entity.UserEntity;
import com.example.InstarCloneBe.User.repository.UserRepository;
import com.example.InstarCloneBe.User.dto.UserDto;
import com.example.InstarCloneBe.User.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    //localToSignUp
    @PostMapping({"/register"})
    @Operation(summary = "유저 등록", description = "여기서 쓰시면 됩니다.")
    public void createUser(@RequestBody UserDto.Create dto) {
        log.info("\uD83D\uDCCD make Post");
        userService.createUser(dto);
    }

    @GetMapping("/find/email/{email}")
    @Operation(summary = "이메일로 유저 ID 검색",description = "이메일을 통해 DB 내 해당 유저 검색")
    public UUID readByEmail(@PathVariable String email){
        log.info("\uD83D\uDCCD read User by using email");
        return userService.readByEmail(email);
    }

    @GetMapping("/find/nickname/{ownerId}")
    @Operation(summary = "userID로 닉네임 검색",description = "ID를 통해 반려동물 이름 검색")
    public String readNickNameByID(@PathVariable UUID ownerId){
        log.info("\uD83D\uDCCD get pet name by using ownerId");
        return userService.readNickNameByID(ownerId);
    }

    @GetMapping("/find/id/{ownerId}")
    @Operation(summary = "userID로 유저 검색",description = "ID를 통해 DB 내 해당 유저 검색")
    public UserDto.Read readById(@PathVariable UUID ownerId){
        log.info("\uD83D\uDCCD get user by using ownerId");
        return userService.readById(ownerId);
    }

    @DeleteMapping("/delete/{ownerId}")
    @Operation(summary = "유저 삭제", description = "User Id를 주면 해당 user 삭제")
    public void deleteUser(@PathVariable UUID ownerId){
        log.info("\uD83D\uDCCD delete user");
        userService.deleteUser(ownerId);
    }
}
