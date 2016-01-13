package com.hoon.cmd.domain.hello;

import java.util.List;

/**
 * 서비스 설명
 *
 * @author : baby bong
 * @since : 2016-01-13
 */
public interface HelloRepositoryCustom {
	List<Hello> findByIdLimit();
}
