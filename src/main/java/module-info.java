module com.firstproject.telfat_w_lqina {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.desktop;
    requires jbcrypt;
    requires javafx.graphics;
    requires mysql.connector.j;
    requires javafx.base;


    // Ouvrir les packages pour JavaFX
    opens com.firstproject.telfat_w_lqina to javafx.fxml;
    opens com.firstproject.telfat_w_lqina.controllers to javafx.fxml;

    // Ouvrir le package Models à Hibernate pour l'accès par réflexion
    opens com.firstproject.telfat_w_lqina.models to org.hibernate.orm.core;

    // Exports
    exports com.firstproject.telfat_w_lqina;
    exports com.firstproject.telfat_w_lqina.controllers;
    exports com.firstproject.telfat_w_lqina.models;
}