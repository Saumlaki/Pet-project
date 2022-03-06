package entity;

import javax.persistence.*;

/**
 * Клас описывает женьщин с которыми мы встремяемся
 */
@Entity
@Table(name = "womans")
public class Woman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String name;

    @Column
    String telephone;

    @Column
    String email;

    private Woman() {
    }

    public Woman(String name, String telephone, String email) {

        this.name = name;
        this.telephone = telephone;
        this.email = email;
    }
}
