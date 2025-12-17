package com.firstproject.telfat_w_lqina.Service;

import com.firstproject.telfat_w_lqina.DAO.UserDAO;
import com.firstproject.telfat_w_lqina.Models.Admin;
import com.firstproject.telfat_w_lqina.Models.User;
import com.firstproject.telfat_w_lqina.util.Alerts;
import com.firstproject.telfat_w_lqina.util.CheckSyntaxe;
import com.firstproject.telfat_w_lqina.util.Security;

public class UserService {
    UserDAO userDAO = new UserDAO();
    Alerts alerts = new Alerts();
    CheckSyntaxe checkSyntaxe = new CheckSyntaxe();
    Security security = new Security();

    public void createUser(User user){
        if (!checkSyntaxe.checkSyntaxeEmail(user.getEmail())){
            alerts.errorAlert("Validation Error","Error syntaxe email","Exemple Email : user@gmail.com");
            return;
        }
        if (!checkSyntaxe.checkSyntaxeTelephone(user.getTelephone())){
            alerts.errorAlert("Validation Error","Error syntaxe Telephone","Exemple Telephone : 0xxxxxxxxx or +212 xxxxxxxxx ");
            return;
        }
        if (!checkSyntaxe.checkSyntaxePassword(user.getPassword())){
            alerts.errorAlert("Validation Error","Error syntaxe Password","Pleasse choose another Password");
            return;
        }
        if(userDAO.findByUsername(user.getUsername())!=null){
            alerts.errorAlert("Creation Error","Username already exists","Please choose another username.");
            return;
        }
        String passwd = user.getPassword();
        user.setPassword(security.hashPassword(passwd));
        if (userDAO.save(user)){
            alerts.successAlert("Success","User created","The user has been created successfully.");
            return;
        }
    }

    public void firstUserAdmin(){
        if (userDAO.nbUsers()==0){
            String passwd = security.hashPassword("Admin");
            Admin admin = new Admin("Admin","0000000000",passwd,"Admin@Admin.com");
            userDAO.save((User) admin);
            System.out.println("first User Admin add");
        }
    }

}
