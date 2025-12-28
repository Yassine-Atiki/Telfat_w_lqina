package com.firstproject.telfat_w_lqina.controllers;

import com.firstproject.telfat_w_lqina.models.LostObject;
import com.firstproject.telfat_w_lqina.models.Stadium;
import com.firstproject.telfat_w_lqina.service.StadiumService;
import com.firstproject.telfat_w_lqina.util.ImageConverterUtil;
import com.firstproject.telfat_w_lqina.util.NavigationUtil;
import com.firstproject.telfat_w_lqina.util.SessionLostObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class UpdateLostObjectController {
    @FXML
    private ImageView imageView;
    @FXML
    private TextArea descriptionField;
    @FXML
    private ComboBox<String> stadiumComboBox;
    @FXML
    private DatePicker lostDatePicker;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TextField ownerNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;

    private List<Stadium> stadiums ;
    private LostObject curentLostObject = SessionLostObject.getInstance().getCurentLostObject();
    private File selectedImageFile;


    @FXML
    public void initialize() {
        Image image=ImageConverterUtil.convertByteToImage(curentLostObject.getImage());
        imageView.setImage(image);
        descriptionField.setText(curentLostObject.getDescription());
        lostDatePicker.setValue(curentLostObject.getLostDate());
        ownerNameField.setText(curentLostObject.getAgentName());
        phoneField.setText(curentLostObject.getPhone());
        emailField.setText(curentLostObject.getEmail());
        typeComboBox.getSelectionModel().select(curentLostObject.getType());
        stadiums = StadiumService.getAllStadiums();
        stadiumComboBox.getItems().clear();
        for (Stadium stadium :stadiums){
            stadiumComboBox.getItems().add(stadium.getStadiumName());
        }
        stadiumComboBox.getSelectionModel().select(curentLostObject.getZone());


    }

    @FXML
    public void goBack() throws IOException {
        NavigationUtil.navigate(new ActionEvent(),"/fxml/Agent.fxml");
    }
    @FXML
    public void viewLostObjects() throws IOException {
        NavigationUtil.navigate(new ActionEvent(),"/fxml/ViewLostObjectsAgent.fxml");
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
}
