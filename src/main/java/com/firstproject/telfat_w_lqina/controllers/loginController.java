package com.firstproject.telfat_w_lqina.controllers;

import com.firstproject.telfat_w_lqina.exception.validationexception.IncorrectPasswordException;
import com.firstproject.telfat_w_lqina.exception.validationexception.InvalidInputLoginException;
import com.firstproject.telfat_w_lqina.exception.validationexception.InvalidUsernameException;
import com.firstproject.telfat_w_lqina.models.Agent;
import com.firstproject.telfat_w_lqina.models.User;
import com.firstproject.telfat_w_lqina.service.AuthService;
import com.firstproject.telfat_w_lqina.util.Alerts;
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

import static com.firstproject.telfat_w_lqina.models.UserType.ADMIN;
import static com.firstproject.telfat_w_lqina.models.UserType.AGENT;

public class loginController {
    Alerts alerts = new Alerts();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordField;

    AuthService authService = new AuthService();


    public void login() throws IOException {
        try {
            authService.login(usernameTextField.getText(),passwordField.getText());
        }
        catch (InvalidInputLoginException invalidInputLoginException){
            alerts.errorAlert("Login Failed" ,"Missing Credentials","Please enter both username and password !" );
            return;
        }
        catch (InvalidUsernameException invalidUsernameException){
            alerts.errorAlert("Login Failed" ,"Invalid Username" ,"The username you entered does not exist !" );
            return;
        }
        catch (IncorrectPasswordException incorrectPasswordException){
            alerts.errorAlert("Login Failed", "Incorrect Password", "The password you entered is incorrect !");
            return;
        }

        User user = authService.commonUser(usernameTextField.getText());
        ActionEvent event = new ActionEvent(usernameTextField, usernameTextField);
        if (user.getUserType() == AGENT) {
        goToAgentScene((Agent) user , event);
        }
        if (user.getUserType() == ADMIN){
            goToAdminScene(user , event);
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
