package com.firstproject.telfat_w_lqina;

import com.firstproject.telfat_w_lqina.service.UserService;
import com.firstproject.telfat_w_lqina.util.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    UserService userService = new UserService();

    @Override
    public void init() throws Exception {
        super.init();

        // Initialiser Hibernate au démarrage de l'application
        // Cela va déclencher la création des tables si elles n'existent pas
        System.out.println("🚀 Initialisation de l'application...");
        System.out.println("📊 Vérification de la base de données...");

        try {
            // Force l'initialisation d'Hibernate (création des tables)
            HibernateUtil.getEntityManager().close();
            System.out.println("✅ Base de données initialisée avec succès !");
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'initialisation de la base de données");
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("ATIKI & NIHMATOUALLAH Project");
        userService.firstUserAdmin();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        // Fermer proprement Hibernate à la fermeture de l'application
        System.out.println("🔒 Fermeture de la connexion à la base de données...");
        HibernateUtil.shutdown();
    }
}