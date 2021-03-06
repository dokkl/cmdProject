package com.hoon.cmd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoon on 2016-01-30.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authorityId")
    private Long id;

    @Column(nullable = false)
    private String authority;

    @OneToMany(mappedBy = "authority")
    private List<User> userList = new ArrayList<User>();
}
