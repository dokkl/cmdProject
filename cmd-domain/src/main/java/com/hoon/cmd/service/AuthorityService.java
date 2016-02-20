package com.hoon.cmd.service;

import com.hoon.cmd.domain.Authority;

import java.util.List;

/**
 * Created by hoon on 2016-02-10.
 */
public interface AuthorityService {
    Authority findById(long id);
    Authority findByAuthority(String authority);
    void saveAuthority(Authority authority);
    void updateAuthority(Authority authority);
    void deleteAuthorityById(long id);
    List<Authority> findAllAuthorities();
    public boolean isAuthorityExist(Authority authority);
}
