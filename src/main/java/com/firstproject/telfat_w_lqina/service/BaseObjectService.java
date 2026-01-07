package com.firstproject.telfat_w_lqina.service;

import com.firstproject.telfat_w_lqina.dao.BaseObjectDAO;
import com.firstproject.telfat_w_lqina.exception.NotFoundException.ObjectNotFoundException;
import com.firstproject.telfat_w_lqina.models.BaseObject;
import com.firstproject.telfat_w_lqina.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BaseObjectService {
    BaseObjectDAO baseObjectDAO = new BaseObjectDAO();
    public void remove(Long baseObjectID){
        BaseObject baseObject = baseObjectDAO.findById(baseObjectID);
        if (baseObject != null){
            baseObjectDAO.remove(baseObject);
        }else {
            throw new ObjectNotFoundException("BaseObject with ID " + baseObjectID + " not found.");
        }
    }
}
