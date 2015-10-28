package com.hoon.cmd.sample;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hoon on 2015-07-21.
 */
@Entity
@Table(name = "schedule")
@NoArgsConstructor
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Sosi sosi;

    @Column
    private String program;

    public Schedule(Sosi sosi, String program) {
        this.sosi = sosi;
        this.program = program;
    }

}
