package com.example.InstarCloneBe.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.InstarCloneBe.member.entity.Member;
import lombok.*;
import java.util.UUID;

public class UserDto {
    @Getter
    @Setter
    public static class Create{
        private String name;
        private String email;
        private String password;
        private String nickname;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read{
        private Long id;
        private String name;
        private String email;
        private String nickname;

        public Read(Member user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
        }
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Update{
        private String name;
        private String nickname;
    }
}
