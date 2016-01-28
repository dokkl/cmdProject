package com.hoon.cmd.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hoon on 2016-01-18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @JsonIgnore
    private String encodedPassword;

    public User(String username, String encodedPassword) {
        this.username = username;
        this.encodedPassword = encodedPassword;
    }
}
