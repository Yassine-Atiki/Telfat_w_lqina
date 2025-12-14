package com.firstproject.telfat_w_lqina.Controllers;

import com.firstproject.telfat_w_lqina.Models.Admin;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class AdminController {
    @FXML
    private Label labelAdmin;

    public void see(Admin admin){
        labelAdmin.setText(admin.getUsername()+" is "+admin.getUserType());
    }
}
