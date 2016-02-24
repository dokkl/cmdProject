package com.hoon.cmd.controller.admin;

import com.hoon.cmd.domain.admin.Authority;
import com.hoon.cmd.service.admin.AuthorityService;
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
public class AuthorityRestController {
    @Autowired
    AuthorityService authorityService;

    @RequestMapping(value = "authority/", method = RequestMethod.GET)
    public ResponseEntity<List<Authority>> listAllAuthorities() {
        log.debug(">> listAllAuthorities");
        List<Authority> authorities = authorityService.findAllAuthorities();
        if (authorities.isEmpty()) {
            return new ResponseEntity<List<Authority>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Authority>>(authorities, HttpStatus.OK);
    }

    @RequestMapping(value = "authority/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Authority> getAuthority(@PathVariable("id") long id) {
        Authority authority = authorityService.findById(id);
        if (authority == null) {
            return new ResponseEntity<Authority>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Authority>(authority, HttpStatus.OK);
    }

    @RequestMapping(value = "authority/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAuthority(@RequestBody Authority authority, UriComponentsBuilder ucBuilder) {
        log.debug(">> createAuthority");
       /* if (authorityService.isAuthorityExist(authority)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
        authorityService.saveAuthority(authority);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/admin/authority/{id}").buildAndExpand(authority.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "authority/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Authority> updateAuthority(@PathVariable("id") long id, @RequestBody Authority authority) {
        Authority currentAuthority = authorityService.findById(id);
        if (currentAuthority == null) {
            return new ResponseEntity<Authority>(HttpStatus.NOT_FOUND);
        }
        currentAuthority.setAuthority(authority.getAuthority());
        authorityService.updateAuthority(currentAuthority);
        return new ResponseEntity<Authority>(currentAuthority, HttpStatus.OK);
    }

    @RequestMapping(value = "authority/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Authority> deleteAuthority(@PathVariable("id") Long id) {
        Authority authority = authorityService.findById(id);
        if (authority == null) {
            return new ResponseEntity<Authority>(HttpStatus.NOT_FOUND);
        }
        authorityService.deleteAuthorityById(id);
        return new ResponseEntity<Authority>(HttpStatus.NO_CONTENT);
    }
}
