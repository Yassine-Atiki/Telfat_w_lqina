package com.firstproject.telfat_w_lqina.models;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "agent")
@PrimaryKeyJoinColumn(name = "agent_id")
public class Agent extends User{

    public Agent() {
        super.setUserType(UserType.AGENT);
    }

    public Agent(long id, String username, String telephone, String password, String email) {
        super( id,username, telephone, password, email);
        super.setUserType(UserType.AGENT);
    }
    public Agent( String username, String telephone, String password, String email) {
        super(username, telephone, password, email);
        super.setUserType(UserType.AGENT);
    }
}
