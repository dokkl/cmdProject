package com.hoon.cmd.controller;

import com.hoon.cmd.domain.board.BoardType;
import com.hoon.cmd.domain.board.BoardVo;
import com.hoon.cmd.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hoon on 2016-02-22.
 */
@RestController
public class BoardRestController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/rest/board", method = RequestMethod.GET)
    public Page<BoardVo> getBoards(@RequestParam("page") int page,
                                  @RequestParam("size") int size) {
        return boardService.getBoards(createPageRequest(page, size), BoardType.BBS_NORMAL);
    }

    @RequestMapping(value = "/rest/notice", method = RequestMethod.GET)
    public Page<BoardVo> getNoticeBbs(@RequestParam("page") int page,
                                   @RequestParam("size") int size) {
        return boardService.getBoards(createPageRequest(page, size), BoardType.BBS_NOTICE);
    }

    private Pageable createPageRequest(int page, int size) {
        //return new PageRequest(page, size);
        return new PageRequest(page - 1, size, Sort.Direction.DESC, "createdAt");
    }
}
