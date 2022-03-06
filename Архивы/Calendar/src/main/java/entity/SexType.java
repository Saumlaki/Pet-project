package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**Класс описывает тип отношений*/
@Entity
@Table(name = "sextype")
public class SexType {

    @Column
    @Id
    int id;

    @Column
    String description;
}
