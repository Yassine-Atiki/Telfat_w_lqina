package com.firstproject.telfat_w_lqina.Service;

import com.firstproject.telfat_w_lqina.DAO.UserDAO;
import com.firstproject.telfat_w_lqina.Models.User;
import com.firstproject.telfat_w_lqina.util.Alerts;

public class UserService {
    UserDAO userDAO = new UserDAO();
    Alerts alerts = new Alerts();

    public boolean login (String userName , String passewd){
        userName = userName.trim();
        boolean isVlideLogin = false;
        if (userName.isEmpty() || passewd.isEmpty()){
            alerts.errorAlert("Login Failed" ,"Missing Credentials","Please enter both username and password !" );
        }
        User user = userDAO.findByUsername(userName);
        if (user != null && user.getPassword().equals(passewd)){
            isVlideLogin = true;
        }
        if (user == null){
            alerts.errorAlert("Login Failed" ,"Invalid Username" ,"The username you entered does not exist !" );
        }
        if (user != null && !(user.getPassword().equals(passewd))) {
            alerts.errorAlert("Login Failed", "Incorrect Password", "The password you entered is incorrect !");
        }
        return isVlideLogin;
    }



    public User commonUser(String userName){
        userName = userName.trim();
        User user = userDAO.findByUsername(userName);
        return user;
    }

}
