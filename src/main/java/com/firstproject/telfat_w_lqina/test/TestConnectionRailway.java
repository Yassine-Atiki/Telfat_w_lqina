package com.firstproject.telfat_w_lqina.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnectionRailway {
    public static void main(String[] args) {
        // Load connection info from environment variables or database.properties
        String url = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/railway?useSSL=false&serverTimezone=UTC");
        String user = System.getenv().getOrDefault("DB_USERNAME", "root");
        String password = System.getenv().getOrDefault("DB_PASSWORD", "");

        try {
            // Charger le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("✅ Connexion réussie à la base Railway !");
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver MySQL introuvable !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Erreur de connexion :");
            e.printStackTrace();
        }
    }
}
