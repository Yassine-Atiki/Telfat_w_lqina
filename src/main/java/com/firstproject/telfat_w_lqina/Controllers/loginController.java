package com.firstproject.telfat_w_lqina.Controllers;

import com.firstproject.telfat_w_lqina.Models.Admin;
import com.firstproject.telfat_w_lqina.Models.Agent;
import com.firstproject.telfat_w_lqina.Models.User;
import com.firstproject.telfat_w_lqina.Service.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.firstproject.telfat_w_lqina.Models.User.UserType.ADMIN;
import static com.firstproject.telfat_w_lqina.Models.User.UserType.AGENT;

public class loginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordField;

    AuthService authService = new AuthService();


    public void login() throws IOException {
        if (authService.login(usernameTextField.getText(),passwordField.getText())){
            User user = authService.commonUser(usernameTextField.getText());
            ActionEvent event = new ActionEvent(usernameTextField, usernameTextField);
            if (user.getUserType() == AGENT) {
            goToAgentScene((Agent) user , event);
            }
            if (user.getUserType() == ADMIN){
                goToAdminScene(user , event);
            }
        }

    }

    public void goToAdminScene(User user ,ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin.fxml"));
        Parent root = loader.load();
        AdminController nextScene = loader.getController();
        nextScene.see(user);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToAgentScene(Agent agent ,ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Agent.fxml"));
        Parent root = loader.load();
        AgentController nextScene = loader.getController();
        nextScene.see(agent);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
