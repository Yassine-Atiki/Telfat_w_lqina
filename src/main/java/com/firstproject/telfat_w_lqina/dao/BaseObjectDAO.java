package com.firstproject.telfat_w_lqina.dao;

import com.firstproject.telfat_w_lqina.models.BaseObject;
import com.firstproject.telfat_w_lqina.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BaseObjectDAO {

    public BaseObject findById(long id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        BaseObject baseObject = null;
        try {
            entityTransaction.begin();
            baseObject = entityManager.find(BaseObject.class, id);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            return baseObject;
        }
    }

    public boolean remove(BaseObject baseObject) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        boolean is_removed = false;
        try {
            entityTransaction.begin();
            entityManager.remove(entityManager.contains(baseObject) ? baseObject : entityManager.merge(baseObject));
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
