package com.firstproject.telfat_w_lqina.util;

import jakarta.persistence.EntityManager;

public class TestHibernate {

    public static void main(String[] args) {
        System.out.println("🔌 Test de connexion à la base de données...");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        EntityManager em = null;
        try {
            // Tenter de créer un EntityManager
            em = HibernateUtil.getEntityManager();

            // Vérifier la connexion avec une transaction
            em.getTransaction().begin();
            em.getTransaction().commit();

            System.out.println("✅ Connexion MySQL + Hibernate réussie !");
            System.out.println("📊 Base de données : telfat_w_lqina");
            System.out.println("🎯 EntityManager créé avec succès");

        } catch (Exception e) {
            System.out.println("❌ Échec de connexion à la base de données");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            e.printStackTrace();

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
                System.out.println("🔒 EntityManager fermé");
            }
            HibernateUtil.shutdown();
            System.out.println("🛑 Hibernate arrêté proprement");
        }
    }
}
