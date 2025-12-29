package com.firstproject.telfat_w_lqina.service;

import com.firstproject.telfat_w_lqina.dao.LostObjectDAO;
import com.firstproject.telfat_w_lqina.models.LostObject;
import com.firstproject.telfat_w_lqina.util.Alerts;
import com.firstproject.telfat_w_lqina.util.CheckSyntaxe;
import com.firstproject.telfat_w_lqina.util.ImageConverterUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LostObjectService {
    private final LostObjectDAO lostObjectDAO = new LostObjectDAO();
    private final LocalDate today = LocalDate.now();

    public void addLostObject(LostObject lostObject , File selectedImageFile) throws IOException {
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
        } else{
            byte[]imageData = ImageConverterUtil.convertImageToByte(selectedImageFile);
            lostObject.setImage(imageData);
            lostObjectDAO.save(lostObject);
        }
    }

    public List<LostObject> getAllLostObjects() {
        return lostObjectDAO.getAllLostObjects();
    }

    public List<LostObject> getLostObjectsByOwnerName(String ownerName , List<LostObject> lostObjects ) {
        List<LostObject> lostObjectsByOwnerName = new ArrayList<>();
        for (LostObject lostObject : lostObjects){
            if (lostObject.getAgentName().equals(ownerName)){
                lostObjectsByOwnerName.add(lostObject);
            }
        }
        return lostObjectsByOwnerName;
    }

    public List<LostObject> getLostObjectsByDate(LocalDate date , List<LostObject> lostObjects) {
        List<LostObject> lostObjectsByDate = new ArrayList<>();
        for (LostObject lostObject : lostObjects){
           if (lostObject.getLostDate().equals(date)){
               lostObjectsByDate.add(lostObject);
           }
        }
        return lostObjectsByDate;
    }

    public List<LostObject> getLostObjectsByStadium(String stadiumName , List<LostObject> lostObjects){
        List<LostObject> lostObjectListByStadium = new ArrayList<>();
        for (LostObject lostObject : lostObjects){
            if (lostObject.getZone().equals(stadiumName)){
                lostObjectListByStadium.add(lostObject);
            }
        }
        return lostObjectListByStadium;
    }


    public void removeLostObject(LostObject lostObject){
        boolean success = lostObjectDAO.remove(lostObject);
        if (success) {
            Alerts.successAlert("Suppression réussie", "L'objet perdu a été supprimé avec succès.", null);
        } else {
            Alerts.errorAlert("Erreur de suppression", "Impossible de supprimer l'objet perdu.", "L'objet n'a pas été trouvé dans la base de données.");
        }
    }

    public void updateLostObject(LostObject lostObject) {
        lostObjectDAO.update(lostObject);
        Alerts.successAlert(
                "Modification réussie",
                "Les informations ont été modifiées avec succès.",
                null
        );
    }
}
