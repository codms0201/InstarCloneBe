//package com.example.InstarCloneBe.member.dto;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.example.InstarCloneBe.member.entity.UserEntity;
//import lombok.*;
//import java.util.UUID;
//
//public class UserDto {
//    @Getter
//    @Setter
//    public static class Create{
//        private String name;
//        private String email;
//        private String password;
//        private String nickname;
//    }
//
//    @Getter
//    @Setter
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public static class Read{
//        private UUID id;
//        private String name;
//        private String email;
//        private String nickname;
//
//        public Read(UserEntity user) {
//            this.id = user.getId();
//            this.name = user.getName();
//            this.email = user.getEmail();
//            this.nickname = user.getNickname();
//        }
//    }
//
//    @Getter
//    @Setter
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public static class Update{
//        private String name;
//        private String nickname;
//    }
//}
