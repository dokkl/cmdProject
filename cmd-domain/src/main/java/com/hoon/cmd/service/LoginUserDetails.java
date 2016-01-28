package com.hoon.cmd.service;

import com.hoon.cmd.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * Created by hoon on 2016-01-18.
 */
@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
    private final User user;

    public LoginUserDetails(User user) {
        super(user.getUsername(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }
}
