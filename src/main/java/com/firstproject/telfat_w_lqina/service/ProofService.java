package com.firstproject.telfat_w_lqina.service;

import com.firstproject.telfat_w_lqina.dao.ProofDAO;
import com.firstproject.telfat_w_lqina.exception.NotFoundException.ObjectNotFoundException;
import com.firstproject.telfat_w_lqina.models.Proof;

public class ProofService {
    ProofDAO proofDAO = new ProofDAO();
    public void remove(Long proofID){
        Proof proof = proofDAO.findById(proofID);
        if (proof != null){
            proofDAO.remove(proof);}
        else {
            throw new ObjectNotFoundException("Proof with ID " + proofID + " not found.");
            }
        }

}
