package com.hoon.cmd.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hoon on 2016-01-18.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
