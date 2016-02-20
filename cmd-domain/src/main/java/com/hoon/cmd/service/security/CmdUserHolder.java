package com.hoon.cmd.service.security;

import com.hoon.cmd.domain.User;
import com.hoon.cmd.service.LoginUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by hoon on 2016-02-20.
 */
@Component
public class CmdUserHolder {
    public User getCmdUser() {
        User user = null;
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            user = new User();
            user.setUsername("익명");
            return user;
        }
        Object principal = authentication.getPrincipal();

        if (principal instanceof LoginUserDetails) {
            user = ((LoginUserDetails)principal).getUser();
        } else {
            //String username = principal.toString();
            user = new User();
            user.setUsername("익명");
        }
        return user;
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
