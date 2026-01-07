package com.firstproject.telfat_w_lqina.service;

import com.firstproject.telfat_w_lqina.dao.ComplaintDAO;
import com.firstproject.telfat_w_lqina.exception.NotFoundException.ObjectNotFoundException;
import com.firstproject.telfat_w_lqina.exception.validationexception.InvalidEmailException;
import com.firstproject.telfat_w_lqina.exception.validationexception.InvalidPhoneException;
import com.firstproject.telfat_w_lqina.models.Complaint;
import com.firstproject.telfat_w_lqina.util.Alerts;
import com.firstproject.telfat_w_lqina.util.CheckSyntaxe;

import java.util.List;

public class ComplaintService {
    private CheckSyntaxe checkSyntaxe = new CheckSyntaxe();
    private ComplaintDAO complaintDAO = new ComplaintDAO();
    private BaseObjectService baseObjectService = new BaseObjectService();
    private PersonService personService = new PersonService();
    private ProofService proofService = new ProofService();

    public Boolean createComplaint(Complaint complaint) {
        if (checkSyntaxe.checkSyntaxeEmail(complaint.getPerson().getEmail())==false){
            throw new InvalidEmailException("Invalid email format: " + complaint.getPerson().getEmail());
        }
        if (checkSyntaxe.checkSyntaxeTelephone(complaint.getPerson().getTelephone())==false){
            throw new InvalidPhoneException("Invalid telephone format: " + complaint.getPerson().getTelephone());
        }
        return complaintDAO.create(complaint);}

    public List<Complaint> getAllComplaints(){
        return complaintDAO.getAllComplaints();
    }

    public void removeComplaint(long complaintID){
        Complaint complaint = complaintDAO.getComplaintById(complaintID);
        if(complaint != null){
            try {
                baseObjectService.remove(complaint.getObject().getId());
                personService.remove(complaint.getPerson().getId());
                proofService.remove(complaint.getProof().getId());
            } catch (ObjectNotFoundException e) {
                throw new ObjectNotFoundException("Related entity not found while deleting complaint with ID " + complaintID + ": " + e.getMessage());
            }

             complaintDAO.removeComplaint(complaint);
        }else {
            throw new ObjectNotFoundException ("Complaint with ID " + complaintID + " not found.");
        }
    }
}
