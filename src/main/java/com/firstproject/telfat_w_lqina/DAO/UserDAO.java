package com.firstproject.telfat_w_lqina.DAO;

import com.firstproject.telfat_w_lqina.Models.User;
import com.firstproject.telfat_w_lqina.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.w3c.dom.Entity;

public class UserDAO {

    //Insert user
    public boolean save(User user){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        boolean transaction = false;
        try {
            entityTransaction.begin();
            entityManager.persist(user);
            entityTransaction.commit();
            transaction = true;
        }
        catch (Exception exception){
            if (entityTransaction.isActive())
                entityTransaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return transaction;
    }

    //SELECT User By UserName
    public User findByUsername(String username){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        User user = null;
        try {
            entityTransaction.begin();
            user = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :u",User.class)
                    .setParameter("u", username).getSingleResult();
            entityTransaction.commit();
        }
        catch (Exception exception){
            if (entityTransaction.isActive())
                entityTransaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return user;
    }

    //SELECT User By ID
    public User findById(long id){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        User user = null;
        try {
            entityTransaction.begin();
            user = entityManager.find(User.class,id);
            entityTransaction.commit();
        }
        catch (Exception exception){
            if (entityTransaction.isActive())
                entityTransaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return user;
    }

    //REMOVE User
    public boolean remove (User user){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        boolean transaction = false;
        try {
            entityTransaction.begin();
            entityManager.remove(user);
            entityTransaction.commit();
            transaction = true;
        }
        catch (Exception exception){
            if (entityTransaction.isActive())
                entityTransaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return transaction;
    }


}
