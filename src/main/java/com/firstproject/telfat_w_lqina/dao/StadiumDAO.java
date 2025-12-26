package com.firstproject.telfat_w_lqina.dao;

import com.firstproject.telfat_w_lqina.models.Stadium;
import jakarta.persistence.EntityManager;

import static com.firstproject.telfat_w_lqina.util.HibernateUtil.getEntityManager;

public class StadiumDAO {
    public void saveStadium(Stadium stadium) {
        //Sauvgarder le stade dans la bd avec Hibernatte presistance
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(stadium);
            em.getTransaction().commit();
        }catch(Exception e){
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public boolean existsByName(String name) {
        EntityManager em = getEntityManager();
        try {
            Long count = em.createQuery(
                            "SELECT COUNT(s) FROM Stadium s WHERE s.StadiumName = :name",
                            Long.class
                    ).setParameter("name", name)
                    .getSingleResult();

            return count > 0;
        } finally {
            em.close();
        }
    }

    public java.util.List<Stadium> getAllStadiums() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Stadium s", Stadium.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
