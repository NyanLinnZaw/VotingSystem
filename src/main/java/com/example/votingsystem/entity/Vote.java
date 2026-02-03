package com.example.votingsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`option`", nullable = false)
    private String option;

    public Vote() {}

    public Vote(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
