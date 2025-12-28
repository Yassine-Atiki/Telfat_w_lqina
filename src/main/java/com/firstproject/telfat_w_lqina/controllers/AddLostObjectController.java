package com.firstproject.telfat_w_lqina.controllers;

import com.firstproject.telfat_w_lqina.models.*;
import com.firstproject.telfat_w_lqina.service.LostObjectService;
import com.firstproject.telfat_w_lqina.service.StadiumService;
import com.firstproject.telfat_w_lqina.util.Alerts;
import com.firstproject.telfat_w_lqina.util.LogoutUtil;
import com.firstproject.telfat_w_lqina.util.NavigationUtil;
import com.firstproject.telfat_w_lqina.util.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;   // Pour l'élément ImageView
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class AddLostObjectController {
    @FXML private ComboBox<String> typeComboBox;
    @FXML private TextArea descriptionField;
    @FXML private DatePicker lostDatePicker;
    @FXML private ChoiceBox<String> stadiumComboBox;
    @FXML private TextField ownerNameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private ImageView imageView;

    private final LostObjectService lostObjectService = new LostObjectService();
    private File selectedImageFile;


    public void initialize() {
        User currentUser = SessionManager.getInstance().getCurrentUser();
        ownerNameField.setText(currentUser.getUsername());
        phoneField.setText(currentUser.getTelephone());
        emailField.setText(currentUser.getEmail());
        loadStadiums();

    }

    private void loadStadiums() {
        List<Stadium> stadiums = StadiumService.getAllStadiums();
        stadiumComboBox.getItems().clear();
        for (Stadium stadium : stadiums) {
            stadiumComboBox.getItems().add(stadium.getStadiumName());
        }

        if (SessionManager.getInstance().getCurrentUser().getUserType().equals(UserType.AGENT)) {
            Agent currentAgent = (Agent) SessionManager.getInstance().getCurrentUser();
            String stadiumName = currentAgent.getStadium().getStadiumName();
            stadiumComboBox.getSelectionModel().select(stadiumName);
        }
    }

     @FXML
     public void chooseImage(ActionEvent event) throws MalformedURLException {
         FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Choisir une image");
         fileChooser.getExtensionFilters().addAll(
           new FileChooser.ExtensionFilter("Images","*.png","*.jpg","*.jpeg","*.gif")
         );
        File file = fileChooser.showOpenDialog(null);
        if (file!=null)
            selectedImageFile = file;

         if (selectedImageFile!=null){
             Image image = new Image(selectedImageFile.toURL().toString());
             imageView.setImage(image);
         }
    }

    @FXML
    public void handleSave() throws IOException {
        // Vérification champs vides
        if (typeComboBox.getValue() == null ||
                descriptionField.getText().isBlank() ||
                lostDatePicker.getValue() == null ||
                stadiumComboBox.getValue() == null || stadiumComboBox.getValue().isBlank() ||
                ownerNameField.getText().isBlank() ||
                phoneField.getText().isBlank() ||
                emailField.getText().isBlank() ||
                selectedImageFile == null) {

            Alerts.errorAlert(
                    "Champs manquants",
                    "Formulaire incomplet",
                    "Veuillez remplir tous les champs avant d'enregistrer."
            );
            return;
        }

        try {
            // Récupérer les données
            LostObject obj = new LostObject();
            obj.setType(typeComboBox.getValue());
            obj.setDescription(descriptionField.getText());
            obj.setLostDate(lostDatePicker.getValue());
            obj.setZone(stadiumComboBox.getValue());
            obj.setAgentName(ownerNameField.getText());
            obj.setPhone(phoneField.getText());
            obj.setEmail(emailField.getText());

            // Appeler le service
            lostObjectService.addLostObject(obj, selectedImageFile);

            // Alert de succès
            Alerts.successAlert("Succès", "", "Objet perdu enregistré avec succès.");
            NavigationUtil.navigate(new ActionEvent(), "/fxml/ViewLostObjectsAgent.fxml");

        } catch (Exception e) {
            Alerts.errorAlert("Erreur", "Erreur lors de l'enregistrement",
                    "Une erreur s'est produite : " + e.getMessage());
        }
    }

    @FXML
    public void reinitialiser(ActionEvent event) {
        typeComboBox.getSelectionModel().clearSelection();
        descriptionField.clear();
        lostDatePicker.setValue(null);
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        if (SessionManager.getInstance().getCurrentUser().getUserType().equals(UserType.ADMIN)){
            NavigationUtil.navigate(event,"/fxml/Admin.fxml");
            return;
        }
        if (SessionManager.getInstance().getCurrentUser().getUserType().equals(UserType.AGENT)){
        NavigationUtil.navigate(event,"/fxml/Agent.fxml");
        return;
        }
    }
    @FXML
    public void viewLostObjects(ActionEvent event) throws IOException {
        if (SessionManager.getInstance().getCurrentUser().getUserType().equals(UserType.ADMIN)){
            NavigationUtil.navigate(event,"/fxml/ViewLostObjectsAdmin.fxml");
            return;
        }
        if (SessionManager.getInstance().getCurrentUser().getUserType().equals(UserType.AGENT)) {
            NavigationUtil.navigate(event, "/fxml/ViewLostObjectsAgent.fxml");
            return;
        }
    }


    @FXML
    public void seDeconnecter(ActionEvent event) throws IOException {
        LogoutUtil.logout(event);
    }
}
