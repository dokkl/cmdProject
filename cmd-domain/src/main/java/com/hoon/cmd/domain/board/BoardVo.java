package com.hoon.cmd.domain.board;

import lombok.Data;

import java.util.Date;

/**
 * Created by hoon on 2016-02-22.
 */
@Data
public class BoardVo {
    private Long id;

    private BoardType boardType;

    private String title;

    private String contents;

    private Date createdAt;

    private String createdBy;

    private Date modifiedAt;

    private String modifiedBy;

    private Long viewCount;
}
