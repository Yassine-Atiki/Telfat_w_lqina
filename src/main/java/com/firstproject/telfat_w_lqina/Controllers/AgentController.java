package com.firstproject.telfat_w_lqina.Controllers;

import com.firstproject.telfat_w_lqina.Models.Admin;
import com.firstproject.telfat_w_lqina.Models.Agent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AgentController {
    @FXML
    private Label labelAdmin;

    public void see(Agent agent){
        labelAdmin.setText(agent.getUsername()+" is "+agent.getUserType());
    }
}