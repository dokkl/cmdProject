package com.hoon.cmd.domain.board;

/**
 * Created by hoon on 2016-02-21.
 */
public enum BoardType {
    BBS_NORMAL("일반게시판"),
    BBS_NOTICE("공지사항");

    private String name;
    BoardType(String name) {
        this.name = name;
    }
}
