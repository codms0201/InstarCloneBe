package com.example.InstarCloneBe.Board.repository;

import com.example.InstarCloneBe.Board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByMemberId(Long memberId);
}
