package com.firstproject.telfat_w_lqina.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LogoutUtil {
    private static Stage stage;
    private static Scene scene;

    @FXML
    public static void logout(ActionEvent event) throws IOException {
        SessionManager.getInstance().clearSession();
        FXMLLoader loader = new FXMLLoader(LogoutUtil.class.getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
