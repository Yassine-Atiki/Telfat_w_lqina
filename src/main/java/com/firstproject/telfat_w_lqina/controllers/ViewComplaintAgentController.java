package com.firstproject.telfat_w_lqina.controllers;

import com.firstproject.telfat_w_lqina.exception.NotFoundException.ObjectNotFoundException;
import com.firstproject.telfat_w_lqina.models.Complaint;
import com.firstproject.telfat_w_lqina.service.ComplaintService;
import com.firstproject.telfat_w_lqina.util.Alerts;
import com.firstproject.telfat_w_lqina.util.ImageConverterUtil;
import com.firstproject.telfat_w_lqina.util.LogoutUtil;
import com.firstproject.telfat_w_lqina.util.NavigationUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;

public class ViewComplaintAgentController {
    @FXML
    private TableView<Complaint> complaintTableView;
    @FXML
    private TableColumn<Complaint, String> nameComplaintTableColumn;
    @FXML
    private TableColumn<Complaint, String> stadeLosteObjectTableColumn;
    @FXML
    private TableColumn<Complaint, String> dateLosteObjectTableColumn;
    @FXML
    private TableColumn<Complaint, String> typeProofTableColumn;
    @FXML
    private TableColumn<Complaint, String> stateComplaintTableColumn;
    @FXML
    private TableColumn<Complaint, String> typeLosteObjectTableColumn;

    // Info Complaint selected
    @FXML private Label lastNameLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label emailLabel;
    @FXML private Label teleLabel;
    @FXML private Label documentTypeLabel;
    @FXML private Label documentNumberLabel;
    @FXML private Label typeObjetLabel;
    @FXML private Label lostDateLabel;
    @FXML private Label zoneLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label proofTypeLabel;
    @FXML private Label StatusComplaintLabel;
    @FXML private ImageView ImageProofImageView;

    private ComplaintService complaintService = new ComplaintService();
    private ObservableList<Complaint> complaintObservableList;
    private List<Complaint> complaintList;
    private Complaint selectedComplaint;

    @FXML
    public void initialize() {
        // Charger les données
        complaintList = complaintService.getAllComplaints();
        complaintObservableList = FXCollections.observableArrayList(complaintList);

        // Configuration des colonnes
        nameComplaintTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getPerson().getFirstName() + " " +
                                cellData.getValue().getPerson().getLastName()
                )
        );

        stadeLosteObjectTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getObject().getZone())
        );

        dateLosteObjectTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getObject().getLostDate() != null ?
                                cellData.getValue().getObject().getLostDate().toString() : ""
                )
        );

        typeProofTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getProof() != null &&
                                cellData.getValue().getProof().getPresenceProofType() != null ?
                                cellData.getValue().getProof().getPresenceProofType().toString() : ""
                )
        );

        stateComplaintTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getStatus() != null ?
                                cellData.getValue().getStatus().toString() : ""
                )
        );

        typeLosteObjectTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getObject() != null &&
                                cellData.getValue().getObject().getType() != null ?
                                cellData.getValue().getObject().getType().toString() : ""
                )
        );

        // Charger les données dans le TableView
        complaintTableView.setItems(complaintObservableList);

        complaintTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        displayComplaintDetails(newValue);
                    }
                }
        );
    }


    private void displayComplaintDetails(Complaint complaint) {
        try {
            selectedComplaint = complaint;
            // Informations personnelles
            lastNameLabel.setText(complaint.getPerson().getLastName());
            firstNameLabel.setText(complaint.getPerson().getFirstName());
            emailLabel.setText(complaint.getPerson().getEmail());
            teleLabel.setText(complaint.getPerson().getTelephone());

            // Document d'identité
            documentTypeLabel.setText(complaint.getPerson().getIdentityDocument().getType().toString());
            documentNumberLabel.setText(complaint.getPerson().getIdentityDocument().getDocumentNumber());

            // Objet perdu
            typeObjetLabel.setText(complaint.getObject().getType());
            lostDateLabel.setText(complaint.getObject().getLostDate().toString());
            zoneLabel.setText(complaint.getObject().getZone());
            descriptionLabel.setText(complaint.getObject().getDescription());

            // Preuve et statut
            proofTypeLabel.setText(complaint.getProof().getPresenceProofType().toString());
            StatusComplaintLabel.setText(complaint.getStatus().toString());

            // Image de preuve
            if (complaint.getProof().getProofImage() != null) {
                ImageProofImageView.setImage(
                        ImageConverterUtil.convertByteToImage(complaint.getProof().getProofImage())
                );
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des détails: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goToAgent(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event, "/fxml/Agent.fxml");
    }

    @FXML
    public void addLostObject(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event, "/fxml/AddLostObject.fxml");
    }

    @FXML
    public void viewLostObjects(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event, "/fxml/ViewLostObjectsAgent.fxml");
    }

    @FXML
    public void goToAddComplaint(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event, "/fxml/AddComplaint.fxml");
    }

    @FXML
    public void seDeconnecter(ActionEvent event) throws IOException {
        LogoutUtil.logout(event);
    }

    public void removeComplaint(ActionEvent event) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        if (selectedComplaint != null) {
            confirmationAlert.setTitle("Confirmation de suppression");
        confirmationAlert.setHeaderText("Voulez-vous vraiment supprimer cette réclamation ?");
        confirmationAlert.setContentText("Cette action est irréversible.");
        confirmationAlert.getButtonTypes().setAll(ButtonType.YES,ButtonType.CANCEL);
            confirmationAlert.showAndWait();
            if (confirmationAlert.getButtonTypes().equals(ButtonType.YES)){
                try {
                complaintService.removeComplaint(selectedComplaint.getId());
                complaintObservableList.remove(selectedComplaint);
                Alerts.successAlert("Succès", null, "La réclamation a été supprimée avec succès.");
            } catch (ObjectNotFoundException objectNotFoundException) {
                    Alerts.errorAlert("Erreur", null, "La réclamation n'a pas été trouvée.");
                }
            }
        }
    }
}