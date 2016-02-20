package com.hoon.cmd.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(nullable = false)
    private String username;

    private String nick;

    @JsonIgnore
    @Column(nullable = false)
    private String encodedPassword;

    @Column(nullable = true)
    private String email;

    //@JsonIgnore
    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "authorityId")
    private Authority authority;

    public void setAuthority(Authority authority) {
        this.authority = authority;
        if (!authority.getUserList().contains(this)) {
            authority.getUserList().add(this);
        }
    }

}
