package com.hoon.cmd.service.board;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.hoon.cmd.domain.board.Board;
import com.hoon.cmd.domain.board.BoardRepository;
import com.hoon.cmd.domain.board.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by hoon on 2016-02-22.
 */
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Page<BoardVo> getBoards(Pageable pageable) {
        Page<Board> page = boardRepository.findAll(pageable);
        return new PageImpl<BoardVo>(convert(page.getContent()), pageable, page.getTotalElements());
    }

    private List<BoardVo> convert(List<Board> list) {
        return Lists.transform(list, new Function<Board, BoardVo>() {
            @Nullable
            @Override
            public BoardVo apply(Board input) {
                return toBoardVo(input);
            }
        });
    }

    private BoardVo toBoardVo(Board board) {
        BoardVo boardVo = new BoardVo();
        boardVo.setId(board.getId());
        boardVo.setBoardType(board.getBoardType());
        boardVo.setTitle(board.getTitle());
        boardVo.setContents(board.getContents());
        boardVo.setCreatedAt(board.getCreatedAt());
        boardVo.setCreatedBy(board.getCreatedBy());
        boardVo.setModifiedAt(board.getModifiedAt());
        boardVo.setModifiedBy(board.getModifiedBy());
        boardVo.setViewCount(board.getViewCount());

        return boardVo;
    }
}
