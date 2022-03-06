package com.saumlaki.timer.entities;

import javax.persistence.*;

/**
 * Тип времени. Тип временеи описывает возможные типы времени. На основании типов времени задаются виды времени
 */
@Entity(name = "TimeData")
public class TimeData {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kindOfTimeId")
    private KindOfTime kindOfTime;

}
