package com.hoon.cmd.sample;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hoon on 2015-07-21.
 */
@Entity
@Table(name = "sosi")
@NoArgsConstructor
@Data
public class Sosi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name="sosi_id", referencedColumnName="id")
    private List<Schedule> scheduleList;

    public Sosi(String name) {
        this.name = name;
    }

}
