package com.hoon.cmd.controller.admin;

import com.hoon.cmd.domain.admin.User;
import com.hoon.cmd.service.admin.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by hoon on 2016-02-10.
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin/rest/")
public class UserRestController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        log.debug(">> listAllUsers");
        List<User> authorities = userService.findAllUsers();
        if (authorities.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(authorities, HttpStatus.OK);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "user/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        log.debug(">> createUser");
       /* if (authorityService.isAuthorityExist(authority)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
        userService.saveUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/admin/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        //log.debug("updateUser getAuthority :" + user.getAuthority().toString());
        User currentUser = userService.findById(id);
        if (currentUser == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        currentUser.setUsername(user.getUsername());
        currentUser.setNick(user.getNick());
        currentUser.setEmail(user.getEmail());
        currentUser.setAuthority(user.getAuthority());
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
