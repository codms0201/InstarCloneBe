package com.example.InstarCloneBe.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uid;

    private String name;

    private String nickname;

    private String email;

    private String pImg_url;

    private String intro;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Member(String uid, String nickname, String email, String name) {
        this.uid = uid;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }
}
