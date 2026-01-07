package com.firstproject.telfat_w_lqina.dao;

import com.firstproject.telfat_w_lqina.models.Proof;
import com.firstproject.telfat_w_lqina.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProofDAO {
    public Proof findById(Long proofId){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Proof proof = null;
        try{
            entityTransaction.begin();
            proof = entityManager.find(Proof.class, proofId);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            return proof;
        }
    }

    public boolean remove(Proof proof){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        boolean is_removed = false;
        try{
            entityTransaction.begin();
            entityManager.remove(entityManager.contains(proof) ? proof : entityManager.merge(proof));
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
