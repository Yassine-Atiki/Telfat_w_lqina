package com.firstproject.telfat_w_lqina.Controllers;

import com.firstproject.telfat_w_lqina.Models.Admin;
import com.firstproject.telfat_w_lqina.Models.User;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.event.ActionEvent;

public class AdminController {
    @FXML
    private Label labelAdmin;

    private Stage stage;
    private Scene scene;

    public void see(User admin){
        labelAdmin.setText(admin.getUsername()+" is "+admin.getUserType());
    }

    @FXML
    public void createUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddUsers.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
