package com.firstproject.telfat_w_lqina.controllers;

import com.firstproject.telfat_w_lqina.models.Agent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AgentController {
    @FXML
    private Label labelAdmin;

    public void see(Agent agent){
        labelAdmin.setText(agent.getUsername()+" is "+agent.getUserType());
    }
}