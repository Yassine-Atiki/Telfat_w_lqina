package com.firstproject.telfat_w_lqina.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "agent")
@PrimaryKeyJoinColumn(name = "agent_id")
public class Agent extends User{

    public Agent() {
    }

    public Agent(String username, String telephone, String password, long id, String email) {
        super(username, telephone, password, id, email);
    }
}
