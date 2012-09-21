package org.isma.web.versusfighting.model;

import org.isma.utils.Identifiable;

import javax.persistence.*;

@Entity
@Table(name = "PLAYER"/*, catalog = "VG"*/)
public class Player implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    public Player() {
        //Hibernate requirement
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    //TODO trouver plus propre pour afficher ça dans la combo dune jsp...
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (id != player.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
