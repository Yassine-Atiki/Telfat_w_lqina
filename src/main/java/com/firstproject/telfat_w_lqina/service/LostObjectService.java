package com.firstproject.telfat_w_lqina.service;

import com.firstproject.telfat_w_lqina.dao.LostObjectDAO;
import com.firstproject.telfat_w_lqina.models.LostObject;
import com.firstproject.telfat_w_lqina.util.Alerts;
import com.firstproject.telfat_w_lqina.util.CheckSyntaxe;
import java.time.LocalDate;
import java.util.List;


public class LostObjectService {
    private final LostObjectDAO lostObjectDAO = new LostObjectDAO();
    private final LocalDate today = LocalDate.now();

    public void addLostObject(LostObject lostObject) {
        // Validation métier ici
        if(lostObject.getLostDate().isAfter(today)){
            Alerts.errorAlert("Date invalide","Impossible d'enregistrer l'objet perdu","La date ne peut pas être dans le futur.");
        }
        else if(lostObject.getAgentName() == null || lostObject.getAgentName().isBlank()){
            Alerts.errorAlert("Nom manquant","Agent non renseigné","Veuillez saisir le nom de l'agent.");
        }
        else if(!CheckSyntaxe.checkSyntaxeEmail(lostObject.getEmail())){
            Alerts.errorAlert("Email invalide","Impossible d'enregistrer l'objet perdu","Veuillez saisir un email valide.");
        }
        else if(!CheckSyntaxe.checkSyntaxeTelephone(lostObject.getPhone())){
            Alerts.errorAlert("Téléphone invalide","Impossible d'enregistrer l'objet perdu","Veuillez saisir un numéro de téléphone valide.");
        }else{
            lostObjectDAO.save(lostObject);
        }
    }

    public List<LostObject> getAllLostObjects() {
        return lostObjectDAO.getAllLostObjects();
    }
}
