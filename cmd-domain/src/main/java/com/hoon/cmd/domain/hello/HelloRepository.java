package com.hoon.cmd.domain.hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 서비스 설명
 *
 * @author : baby bong
 * @since : 2016-01-13
 */
@Repository
public interface HelloRepository extends JpaRepository<Hello, Long>, QueryDslPredicateExecutor<Hello>, HelloRepositoryCustom {
}
