package com.hoon.cmd.service;

import com.hoon.cmd.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hoon on 2016-01-18.
 */
@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
    private final User user;

    public LoginUserDetails(User user) {
        super(user.getUsername(), user.getEncodedPassword()
                //, AuthorityUtils.createAuthorityList("ROLE_USER")
                , authorities(user));
        this.user = user;
    }

    private static Collection<? extends GrantedAuthority> authorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getAuthority().getAuthority()));
        return authorities;
    }
}
