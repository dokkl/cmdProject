package com.hoon.cmd.service;

import com.hoon.cmd.domain.Authority;
import com.hoon.cmd.domain.User;

import java.util.List;

/**
 * Created by hoon on 2016-02-10.
 */
public interface UserService {
    User findById(long id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByNick(String nick);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserById(long id);
    List<User> findAllUsers();
    public boolean isUserExist(User user);
}
