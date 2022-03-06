package com.saumlaki.timer.entities;
import javax.persistence.*;

/**
 * Вид времени. Вид временеи описывает возможные виды времени. Вид времени включает в себя более общий типп времени.
 */

@Entity(name = "KindOfTime")
public class KindOfTime {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timeTypeId")
    private TimeType timeType;
}
