package com.firstproject.telfat_w_lqina.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "object")
@Inheritance(strategy = InheritanceType.JOINED)
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "typeObjet", nullable = false)
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "lostDate", nullable = false)
    private LocalDate lostDate;

    @Column(name = "zone", nullable = false)
    private String zone;

    @Column(name = "agentName", nullable = false)
    private String agentName;

    @Column(name = "Telephone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    public Object(String agentName, String description, String email, Long id, LocalDate lostDate, String phone, String type, String zone ) {
        this.agentName = agentName;
        this.description = description;
        this.email = email;
        this.lostDate = lostDate;
        this.phone = phone;
        this.type = type;
        this.zone = zone;
        this.id = id;
    }
    public Object(String agentName, String description, String email, LocalDate lostDate, String phone, String type, String zone) {
        this.agentName = agentName;
        this.description = description;
        this.email = email;
        this.lostDate = lostDate;
        this.phone = phone;
        this.type = type;
        this.zone = zone;
    }
    public Object() {}

    // Getters
    public String getAgentName() {
        return agentName;
    }


    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getLostDate() {
        return lostDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    public String getZone() {
        return zone;
    }
    public Long getId() {
        return id;
    }
    //Setters


    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setLostDate(LocalDate lostDate) {
        this.lostDate = lostDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

}
