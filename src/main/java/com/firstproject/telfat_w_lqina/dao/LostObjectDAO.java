package com.firstproject.telfat_w_lqina.dao;

import com.firstproject.telfat_w_lqina.models.LostObject;
import com.firstproject.telfat_w_lqina.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class LostObjectDAO {
    public void save(LostObject lostObject) {
        // Persistance avec Hibernate
        EntityManager em = HibernateUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(lostObject);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<LostObject> getAllLostObjects() {
        EntityManager em = HibernateUtil.getEntityManager();
        List<LostObject> lostObjects = new ArrayList<>();
        try {
            TypedQuery<LostObject> query = em.createQuery("SELECT l FROM LostObject l ORDER BY l.lostDate DESC", LostObject.class);
            lostObjects = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return lostObjects;
    }
}
