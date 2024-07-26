package com.example.InstarCloneBe.User.entity;

import com.example.InstarCloneBe.User.dto.UserDto;
import com.example.InstarCloneBe.util.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(nullable = false, unique = true)
    @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "이메일 형식이 잘못되었습니다.")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password",length = 30)
    @Size(min = 8, max = 30, message = "Username must be between 8 and 30 characters")
    private String password;

    public static UserEntity localToEntity(UserDto.Create dto){
        return UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .build();
    }

    public void localToUpdate(String name, String petName){
        this.name = name;
        this.nickname = nickname;
    }
}