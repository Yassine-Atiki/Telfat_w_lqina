package com.firstproject.telfat_w_lqina.models;

import com.firstproject.telfat_w_lqina.Enum.TypeState;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lost_objects")
@PrimaryKeyJoinColumn(name = "lostObject_id")
public class LostObject extends BaseObject {

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column(name = "status", nullable = false)
    private TypeState typeState = TypeState.IN_STORAGE;

    @Column(name = "agentName", nullable = false)
    private String agentName;

    @Column(name = "Telephone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    // Constructeur avec ID
    public LostObject(String agentName, String description, String email, Long id,
                      LocalDate lostDate, String phone, String type, String zone, byte[] image) {
        super(description, lostDate, type, zone);
        this.agentName = agentName;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    // Constructeur sans ID
    public LostObject(String agentName, String description, String email,
                      LocalDate lostDate, String phone, String type, String zone, byte[] image) {
        super(description, lostDate, type, zone);
        this.agentName = agentName;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public LostObject() {}

    // Getters
    public byte[] getImage() {
        return image;
    }

    public TypeState getTypeState() {
        return typeState;
    }

    public String getAgentName() {
        return agentName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    // Setters
    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setTypeState(TypeState typeState) {
        this.typeState = typeState;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}