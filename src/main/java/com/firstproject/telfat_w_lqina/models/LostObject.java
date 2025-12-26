package com.firstproject.telfat_w_lqina.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lost_objects")
@PrimaryKeyJoinColumn(name = "lostObject_id")
public class LostObject extends Object {

    // @Lob = Large Object (pour les données volumineuses)
    // @Column avec columnDefinition spécifie le type SQL exact
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column(name = "status", nullable = false)
    private TypeState typeState = TypeState.IN_STORAGE ;

    public LostObject(String agentName, String description, String email, Long id, LocalDate lostDate, String phone, String type, String zone ,byte[] image) {
        super(agentName, description, email, id, lostDate, phone, type, zone);
        this.image = image;
    }
    public LostObject(String agentName, String description, String email, LocalDate lostDate, String phone, String type, String zone ,byte[] image) {
        super(agentName, description, email, lostDate, phone, type, zone);
        this.image = image;
    }



    public LostObject() {

    }

    //Getters
    public byte[] getImage() {
        return image;
    }

    public TypeState getTypeState() {
        return typeState;
    }

    //Setters
    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setTypeState(TypeState typeState) {
        this.typeState = typeState;
    }
}
