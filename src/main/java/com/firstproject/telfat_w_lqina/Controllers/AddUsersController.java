package com.firstproject.telfat_w_lqina.Controllers;

import com.firstproject.telfat_w_lqina.Models.Admin;
import com.firstproject.telfat_w_lqina.Models.Agent;
import com.firstproject.telfat_w_lqina.Models.User;
import com.firstproject.telfat_w_lqina.Service.UserService;
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
import java.util.ResourceBundle;
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
            userService.createUser((User) admin);
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
