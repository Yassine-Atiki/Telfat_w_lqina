package com.firstproject.telfat_w_lqina.controllers;

import com.firstproject.telfat_w_lqina.models.LostObject;
import com.firstproject.telfat_w_lqina.models.User;
import com.firstproject.telfat_w_lqina.service.LostObjectService;
import com.firstproject.telfat_w_lqina.util.Alerts;
import com.firstproject.telfat_w_lqina.util.LogoutUtil;
import com.firstproject.telfat_w_lqina.util.NavigationUtil;
import com.firstproject.telfat_w_lqina.util.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddLostObjectController {
    @FXML private ComboBox<String> typeComboBox;
    @FXML private TextArea descriptionField;
    @FXML private DatePicker lostDatePicker;
    @FXML private TextField zoneField;
    @FXML private TextField ownerNameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;

    private final LostObjectService lostObjectService = new LostObjectService();
    private Stage stage;
    private Scene scene;


    public void initialize(){
        User currentUser = SessionManager.getInstance().getCurrentUser();
        ownerNameField.setText(currentUser.getUsername());
        phoneField.setText(currentUser.getTelephone());
        emailField.setText(currentUser.getEmail());
            }

    @FXML
    public void handleSave() {
        // Vérification champs vides
        if (typeComboBox.getValue() == null ||
                descriptionField.getText().isBlank() ||
                lostDatePicker.getValue() == null ||
                zoneField.getText().isBlank() ||
                ownerNameField.getText().isBlank() ||
                phoneField.getText().isBlank() ||
                emailField.getText().isBlank()) {

            Alerts.errorAlert(
                    "Champs manquants",
                    "Formulaire incomplet",
                    "Veuillez remplir tous les champs avant d'enregistrer."
            );
            return; //w9ef hna 3end hedek
        }

        // Récupérer les données
        LostObject obj = new LostObject();
        obj.setType(typeComboBox.getValue());
        obj.setDescription(descriptionField.getText());
        obj.setLostDate(lostDatePicker.getValue());
        obj.setZone(zoneField.getText());
        obj.setAgentName(ownerNameField.getText());
        obj.setPhone(phoneField.getText());
        obj.setEmail(emailField.getText());

        // Appeler le service
        lostObjectService.addLostObject(obj);
        //Alert
        Alerts.successAlert("Succès","","Objet perdu enregistré avec succès.");
    }

    @FXML
    public void reinitialiser(ActionEvent event) {
        typeComboBox.getSelectionModel().clearSelection();
        descriptionField.clear();
        lostDatePicker.setValue(null);
        zoneField.clear();
        ownerNameField.clear();
        phoneField.clear();
        emailField.clear();
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event,"/fxml/Agent.fxml");
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        LogoutUtil.logout(event);
    }
}
