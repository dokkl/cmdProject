package com.hoon.cmd.service;

import com.hoon.cmd.domain.Authority;
import com.hoon.cmd.domain.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hoon on 2016-02-10.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority findById(long id) {
        return authorityRepository.findOne(id);
    }

    @Override
    public Authority findByAuthority(String name) {
        return authorityRepository.findByAuthority(name);
    }

    @Override
    public void saveAuthority(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    public void updateAuthority(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    public void deleteAuthorityById(long id) {
        authorityRepository.delete(id);
    }

    @Override
    public List<Authority> findAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public boolean isAuthorityExist(Authority authority) {
        return authorityRepository.exists(authority.getId());
    }
}
