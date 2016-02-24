package com.hoon.cmd.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hoon on 2016-02-22.
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
