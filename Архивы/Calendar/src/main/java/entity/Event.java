package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**Основной класс описыающий кое какое событие*/
@Entity
@Table(name = "event")
public class Event {

    @Column
    @Id
    int id;

    @Column
    Date date;

    @Column
    Woman woman;
    @Column
    SexType sexType;
    @Column
    boolean manOrgasm;
    @Column
    boolean womanOrgasm;


}
