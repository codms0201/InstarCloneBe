//package com.example.InstarCloneBe.oauth.controller;
//
//import com.example.InstarCloneBe.member.entity.UserEntity;
//import com.example.InstarCloneBe.member.repository.UserRepository;
//import com.example.InstarCloneBe.member.service.UserService;
//import com.example.InstarCloneBe.oauth.service.AuthService;
//import com.example.InstarCloneBe.util.jwt.JwtUtil;
//import io.jsonwebtoken.Claims;
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@Slf4j
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//    private final JwtUtil jwtUtil;
//    private final AuthService authService;
//    private final UserService userService;
//    private final UserRepository userRepository;
//
//    public AuthController(JwtUtil jwtUtil, AuthService authService , UserService userService, UserRepository userRepo, UserRepository userRepository) {
//        this.jwtUtil = jwtUtil;
//        this.authService = authService;
//        this.userService = userService;
//        this.userRepository = userRepository;
//    }
//
//    private void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
//        Cookie cookie = new Cookie(name, value);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        cookie.setPath("/");
//        cookie.setMaxAge(maxAge);
//        response.addCookie(cookie);
//    }
//
//    @GetMapping("/login")
//    @ResponseBody
//    @Operation(summary = "로컬 로그인", description = "이메일과 페스워드로 로그인이 될수도? 안될 수도?")
//    public Map<String, Object> login(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
//        boolean isAuthenticated = userService.validateUser(email, password);
//        if (isAuthenticated) {
//            log.info("\uD83D\uDCCD 로그인 성공: " + email);
//            UserEntity user = userRepository.findByEmail(email).orElseThrow();
//
//            String accessToken = jwtUtil.generateAccessToken(email);
//            String refreshToken = jwtUtil.generateRefreshToken(email);
//
//            setCookie(response, "access_token", accessToken, (int) (JwtUtil.ACCESS_EXPIRATION_TIME / 1000));
//            setCookie(response, "refresh_token", refreshToken, (int) (JwtUtil.REFRESH_EXPIRATION_TIME / 1000));
//
//            Map<String, Object> result = new HashMap<>();
//            result.put("user_id", user.getId().toString());
//            result.put("email", user.getEmail());
//            result.put("name", user.getName());
//
//            return result;
//        } else {
//            log.info("\uD83D\uDCCD  로그인 실패: " + email);
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return Map.of("error", "Invalid email or password");
//        }
//    }
//
//    @GetMapping("/validate")
//    @Operation(summary = "access_token 유효성 감사 ", description = "access_token 이 있는 지 확인하고 정보 전달")
//    public String validateToken(HttpServletRequest request) {
//        Optional<Cookie> accessTokenCookie = Arrays.stream(request.getCookies())
//                .filter(cookie -> "access_token".equals(cookie.getName()))
//                .findFirst();
//
//        if (accessTokenCookie.isPresent()) {
//            Claims claims = jwtUtil.validateToken(accessTokenCookie.get().getValue());
//            if (claims != null) {
//                return "Token is valid for user: " + claims.getSubject();
//            } else {
//                return "Invalid token";
//            }
//        } else {
//            return "Access token not found";
//        }
//    }
//
//    @PostMapping("/refresh")
//    @Operation(summary = "access_token 갱신", description = "새로운 액세스 토큰을 쿠키로 설정")
//    public String refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
//        Optional<Cookie> refreshTokenCookie = Arrays.stream(request.getCookies())
//                .filter(cookie -> "refresh_token".equals(cookie.getName()))
//                .findFirst();
//
//        if (refreshTokenCookie.isPresent()) {
//            try {
//                Claims claims = jwtUtil.validateToken(refreshTokenCookie.get().getValue());
//                String newAccessToken = jwtUtil.generateAccessToken(claims.getSubject());
//
//                Cookie newAccessCookie = new Cookie("access_token", newAccessToken);
//                newAccessCookie.setHttpOnly(true);
//                newAccessCookie.setSecure(true);
//                newAccessCookie.setPath("/");
//                newAccessCookie.setMaxAge((int) (JwtUtil.ACCESS_EXPIRATION_TIME / 1000));
//
//                response.addCookie(newAccessCookie);
//
//                return "Access token refreshed successfully";
//            } catch (Exception e) {
//                return "Invalid refresh token";
//            }
//        } else {
//            return "Refresh token not found";
//        }
//    }
//}

///////////

package com.example.InstarCloneBe.oauth.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import com.example.InstarCloneBe.oauth.dto.AuthDTO;
import com.example.InstarCloneBe.member.entity.Member;
import com.example.InstarCloneBe.member.service.MemberService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @GetMapping("/api/userinfo")
    public ResponseEntity<AuthDTO> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Member> member = memberService.findByEmail(principal.getAttribute("email"));

        AuthDTO authDTO = AuthDTO.builder()
                .memberId(member.get().getId())
                .name(principal.getAttribute("name"))
                .email(principal.getAttribute("email"))
                .build();

        return ResponseEntity.ok(authDTO);
    }
}

