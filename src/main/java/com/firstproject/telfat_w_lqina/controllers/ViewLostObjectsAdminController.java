package com.firstproject.telfat_w_lqina.controllers;

import com.firstproject.telfat_w_lqina.models.LostObject;
import com.firstproject.telfat_w_lqina.models.User;
import com.firstproject.telfat_w_lqina.models.UserType;
import com.firstproject.telfat_w_lqina.service.LostObjectService;
import com.firstproject.telfat_w_lqina.util.LogoutUtil;
import com.firstproject.telfat_w_lqina.util.NavigationUtil;
import com.firstproject.telfat_w_lqina.util.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ViewLostObjectsAdminController {

    @FXML
    private TableView<LostObject> tableViewLostObjects;

    @FXML
    private TableColumn<LostObject, String> colType;

    @FXML
    private TableColumn<LostObject, String> colDescription;

    @FXML
    private TableColumn<LostObject, LocalDate> colDate;

    @FXML
    private TableColumn<LostObject, String> colZone;

    @FXML
    private TableColumn<LostObject, String> colAgent;

    @FXML
    private TableColumn<LostObject, String> colPhone;

    @FXML
    private TableColumn<LostObject, String> colEmail;

    @FXML
    private Label labelUser;

    @FXML
    private Label totalLabel;
    @FXML
    private Label userTypeLabel;

    private final LostObjectService lostObjectService = new LostObjectService();
    private ObservableList<LostObject> lostObjectsList;

    @FXML
    public void initialize() {
        // Récupérer l'utilisateur connecté
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            labelUser.setText(currentUser.getUsername());
        }

        // Configurer les colonnes du TableView
        configureTableColumns();

        // Charger les données
        loadLostObjects();
    }

    private void configureTableColumns() {
        // Lier chaque colonne à la propriété correspondante du modèle LostObject
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("lostDate"));
        colZone.setCellValueFactory(new PropertyValueFactory<>("zone"));
        colAgent.setCellValueFactory(new PropertyValueFactory<>("agentName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Configurer la politique de redimensionnement pour éliminer la colonne vide
        tableViewLostObjects.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


    private void loadLostObjects() {
        try {
            // Récupérer tous les objets perdus depuis le service
            List<LostObject> objects = lostObjectService.getAllLostObjects();

            // Convertir en ObservableList pour JavaFX
            lostObjectsList = FXCollections.observableArrayList(objects);

            // Assigner la liste au TableView
            tableViewLostObjects.setItems(lostObjectsList);

            // Mettre à jour le label du total
            totalLabel.setText("Total: " + lostObjectsList.size() + " objet(s)");

        } catch (Exception e) {
            e.printStackTrace();
            totalLabel.setText("Erreur lors du chargement des données");
        }
    }

    @FXML
    public void refreshTable() {
        loadLostObjects();
    }

    @FXML
    public void addNewObject(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event, "/fxml/AddLostObject.fxml");
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser.getUserType() == UserType.ADMIN){
            NavigationUtil.navigate(event, "/fxml/Admin.fxml");
        }
    }

    @FXML
    public void seDeconnecter(ActionEvent event) throws IOException {
        LogoutUtil.logout(event);
    }
}

