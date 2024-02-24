package edu.hogwarts.studentadmin.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class House {
    @Id
    private String name;
    private String founder;
    @OneToMany(cascade = CascadeType.ALL)
    private List<HouseColor> houseColors;

    public House() {
    }

    public House(String name, String founder, List<HouseColor> houseColors) {
        this.name = name;
        this.founder = founder;
        this.houseColors = houseColors;
    }

    public House(House otherHouse) {
        this.founder = otherHouse.getFounder();
        this.houseColors = otherHouse.getHouseColors();
    }

    public void copyFrom(House otherHouse) {
        this.setFounder(otherHouse.getFounder());
        this.setHouseColors(otherHouse.getHouseColors());
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public List<HouseColor> getHouseColors() {
        return houseColors;
    }

    public void setHouseColors(List<HouseColor> houseColors) {
        this.houseColors = houseColors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
