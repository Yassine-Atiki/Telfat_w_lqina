package com.firstproject.telfat_w_lqina.dao;

import com.firstproject.telfat_w_lqina.models.Person;
import com.firstproject.telfat_w_lqina.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PersonDAO {
    public Person findById(long id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Person person = null;
        try {
            entityTransaction.begin();
            person = entityManager.find(Person.class, id);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            return person;
        }
    }

    public boolean remove(Person person) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        boolean is_removed = false;
        try {
            entityTransaction.begin();
            entityManager.remove(entityManager.contains(person) ? person : entityManager.merge(person));
            entityTransaction.commit();
            is_removed = true;
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            return is_removed;
        }
    }
}
