package com.firstproject.telfat_w_lqina.controllers;

import com.firstproject.telfat_w_lqina.exception.validationexception.IncorrectPasswordException;
import com.firstproject.telfat_w_lqina.exception.validationexception.InvalidInputLoginException;
import com.firstproject.telfat_w_lqina.exception.validationexception.InvalidUsernameException;
import com.firstproject.telfat_w_lqina.models.User;
import com.firstproject.telfat_w_lqina.service.AuthService;
import com.firstproject.telfat_w_lqina.util.Alerts;
import com.firstproject.telfat_w_lqina.util.NavigationUtil;
import com.firstproject.telfat_w_lqina.util.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.firstproject.telfat_w_lqina.Enum.UserType.ADMIN;
import static com.firstproject.telfat_w_lqina.Enum.UserType.AGENT;

public class loginController {

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
            Alerts.errorAlert("Login Failed" ,"Missing Credentials","Please enter both username and password !" );
            return;
        }
        catch (InvalidUsernameException invalidUsernameException){
            Alerts.errorAlert("Login Failed" ,"Invalid Username" ,"The username you entered does not exist !" );
            return;
        }
        catch (IncorrectPasswordException incorrectPasswordException){
            Alerts.errorAlert("Login Failed", "Incorrect Password", "The password you entered is incorrect !");
            return;
        }

        User user = authService.commonUser(usernameTextField.getText());

        // Stocker l'utilisateur dans la session
        SessionManager.getInstance().setCurrentUser(user);

        ActionEvent event = new ActionEvent(usernameTextField, usernameTextField);
        if (user.getUserType() == AGENT) {
            goToAgentScene(event);
        }
        if (user.getUserType() == ADMIN){
            goToAdminScene(event);
        }

    }

    public void goToAdminScene(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event,"/fxml/Admin.fxml");
    }

    public void goToAgentScene(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event,"/fxml/Agent.fxml");
    }
}
