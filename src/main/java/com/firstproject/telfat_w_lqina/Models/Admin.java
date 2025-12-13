package com.firstproject.telfat_w_lqina.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "admin_id")
public class Admin extends User{

    public Admin() {
    }

    public Admin(String username, String telephone, String password, long id, String email) {
        super(username, telephone, password, id, email);
    }


}
