package com.hoon.cmd.domain.board;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hoon on 2016-02-21.
 */
@Data
@Entity
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    private String title;

    private String contents;

    private Date createdAt;

    private String createdBy;

    private Date modifiedAt;

    private String modifiedBy;

    private Long viewCount;

}
