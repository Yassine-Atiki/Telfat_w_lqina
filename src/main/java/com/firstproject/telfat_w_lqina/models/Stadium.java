package com.firstproject.telfat_w_lqina.models;


import jakarta.persistence.*;

@Entity
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NomStade", nullable = false, unique = true)
    private String StadiumName;

    // Constructeurs
    public Stadium() {
    }

    public Stadium(String stadiumName) {
        this.StadiumName = stadiumName;
    }

    // Getters et Setters

    public int getId() {
        return id;
    }

    public String getStadiumName() {
        return StadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.StadiumName = stadiumName;
    }

    @Override
    public String toString() {
        return "Stadium{" +
                "id=" + id +
                ", StadiumName='" + StadiumName + '\'' +
                '}';
    }
}
