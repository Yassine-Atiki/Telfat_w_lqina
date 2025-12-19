package com.firstproject.telfat_w_lqina.controllers;

import com.firstproject.telfat_w_lqina.exception.creationexception.DuplicateEmailException;
import com.firstproject.telfat_w_lqina.exception.creationexception.DuplicateUsernameException;
import com.firstproject.telfat_w_lqina.exception.validationexception.InvalidEmailException;
import com.firstproject.telfat_w_lqina.exception.validationexception.InvalidPasswordException;
import com.firstproject.telfat_w_lqina.exception.validationexception.InvalidPhoneException;
import com.firstproject.telfat_w_lqina.models.Admin;
import com.firstproject.telfat_w_lqina.models.Agent;
import com.firstproject.telfat_w_lqina.models.User;
import com.firstproject.telfat_w_lqina.service.UserService;
import com.firstproject.telfat_w_lqina.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class AddUsersController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField telephoneTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> userTypeComboBox;

    UserService userService = new UserService();

    private Stage stage;
    private Scene scene;
    private Alerts alerts = new Alerts();


    public void initialize() {
        userTypeComboBox.getItems().addAll("ADMIN", "AGENT");
    }

    @FXML
    public void createUser(ActionEvent event) throws IOException {
        if (userTypeComboBox.getValue() == null) {
            alerts.errorAlert("Validation Error", "Role not selected","Please select a user role.");
            return;
        }
        if (userTypeComboBox.getValue().equals("ADMIN")){
            Admin admin = new Admin(usernameTextField.getText(),telephoneTextField.getText(),passwordField.getText(),emailTextField.getText());
            try {
                userService.createUser((User) admin);
            }catch (InvalidEmailException invalidEmailException){
                alerts.errorAlert("Validation Error","Error syntaxe email","Exemple Email : user@gmail.com");
            }
            catch (InvalidPhoneException invalidPhoneException){
                alerts.errorAlert("Validation Error","Error syntaxe Telephone","Exemple Telephone : 0xxxxxxxxx or +212 xxxxxxxxx ");
            }
            catch (InvalidPasswordException invalidPasswordException){
                alerts.errorAlert("Validation Error","Error syntaxe Password","Pleasse choose another Password");
            }
            catch (DuplicateUsernameException duplicateUsernameException){
                alerts.errorAlert("Creation Error","Username already exists","Please choose another username.");
            }
            catch (DuplicateEmailException duplicateEmailException){
                alerts.errorAlert("Creation Error","Email already exists","Please choose another email.");
            };
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddUsers.fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return;
        }
        if (userTypeComboBox.getValue().equals("AGENT")){
            Agent agent = new Agent(usernameTextField.getText(),telephoneTextField.getText(),passwordField.getText(),emailTextField.getText());
            userService.createUser((User) agent);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddUsers.fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return;
        }
    }
}
