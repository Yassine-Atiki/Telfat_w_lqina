package com.firstproject.telfat_w_lqina;

public class AmineExplication {
}

// Hada fach tsali meno m7ih ok!
/*
    Awel haja blast ma tb9a tkteb had code kaml ila bghiti tbdel scene:

        FXMLLoader loader = new FXMLLoader(NavigationUtil.class.getResource(fxmlPath));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

     Rani zedt f /util : classe smitha NavigationUtil b9a t3yet liha w 3tiha ghir lien
     fxml fin bghiti tmchi exemple:

        public void viewLostObjects(ActionEvent event) throws IOException {
     bhal huka -->  NavigationUtil.navigate(event,"/fxml/ViewLostObjects.fxml");
        }

        meme chose drtha pour deconnexion f /util atl9a classe LogoutUtil
        tahia w9tach ma bghitiha 3yet liha exemple:

             public void seDeconnecter(ActionEvent event) throws IOException {
        Voila huka -->   LogoutUtil.logout(event);
             }


        Taniii f /util zedt SessionManagerUtil had classe ktfteh wahed session
        fach ktlanca l'application w mnha knb9aw f ayblasa f app deghya n9edro
        n'recuperiw l user li mConnecter n stockiwh awla ndiro bih li bghina

        Atla9aha khdam biha bzzf f wahed methode ky3awed smitha: public void initialize()
        had methode appelée automatiquement par JavaFX juste après le chargement du fichier FXML.
        -- Exemple:
            @FXML
            public void initialize() {
                // Récupérer l'utilisateur connecté depuis SessionManager
                User currentUser = SessionManager.getInstance().getCurrentUser();
            }

        Dakchi elch fi had methode Recuperina user hitach hia ktkhdem automatiquement
        m3a kydkhol. men be3d khdem b dak user ki bghiti f lcode.


       !!! Lmohim chreht lik hado hitach huma li kyt3awdo athtajhom men be3dd dakchi lakhor
        hta tchoufo rassek.
 */