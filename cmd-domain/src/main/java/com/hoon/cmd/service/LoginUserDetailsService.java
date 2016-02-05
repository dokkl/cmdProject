package com.hoon.cmd.service;

import com.hoon.cmd.domain.User;
import com.hoon.cmd.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by hoon on 2016-01-18.
 */
@Service
@Slf4j
public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("요청된 사용자를 찾을수 없습니다.");
        }
        //log.debug(">> user : " + user.toString());
        return new LoginUserDetails(user);
    }
}
