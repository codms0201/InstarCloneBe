//package com.example.InstarCloneBe.Board.entity;
//
//import com.example.InstarCloneBe.member.entity.UserEntity;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@EntityListeners(AuditingEntityListener.class)
//@Table(name = "board")
//public class Board {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, columnDefinition = "TEXT")
//    private String content;
//
//    private String imgUrl;
//
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private UserEntity user;
//
//    //@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
//    //private List<Comment> comments;
//}
