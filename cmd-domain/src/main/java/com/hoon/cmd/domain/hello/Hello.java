package com.hoon.cmd.domain.hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hoon on 2015-10-21.
 */
@Entity
@Table(name = "Hello")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hello {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Hello(String name) {
        this.name = name;
    }
}
