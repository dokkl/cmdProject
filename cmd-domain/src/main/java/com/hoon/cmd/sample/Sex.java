package com.hoon.cmd.sample;

import lombok.Getter;

/**
 * Created by hoon on 2015-08-12.
 */
public enum Sex {
    M("남자"),
    F("여자");

    @Getter
    private String sexType;

    Sex(String sexType) {
        this.sexType = sexType;
    }


}
