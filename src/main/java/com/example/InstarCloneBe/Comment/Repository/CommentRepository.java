package com.example.InstarCloneBe.Comment.Repository;

import com.example.InstarCloneBe.Comment.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
