package com.saumlaki.timer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "TimeType")
public class TimeType {

    @Id
    private int id;

    @Column(name = "name")
    private String name;
}
