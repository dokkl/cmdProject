package com.hoon.cmd.service.admin;

import com.hoon.cmd.domain.admin.User;
import com.hoon.cmd.domain.admin.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by hoon on 2016-02-20.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByNick(String nick) {
        return userRepository.findByNick(nick);
    }

    @Override
    public void saveUser(User user) {
        if (StringUtils.isEmpty(user.getEncodedPassword())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setEncodedPassword(encoder.encode(user.getUsername()));
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean isUserExist(User user) {
        return userRepository.exists(user.getId());
    }
}
