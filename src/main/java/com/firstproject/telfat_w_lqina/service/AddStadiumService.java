package com.firstproject.telfat_w_lqina.service;

import com.firstproject.telfat_w_lqina.dao.AddStadiumDAO;
import com.firstproject.telfat_w_lqina.models.Stadium;

public class AddStadiumService {

    private static final AddStadiumDAO stadiumDAO = new AddStadiumDAO();

    public static void addStadium(String name) {
        if (name.length() < 3) {
            throw new IllegalArgumentException(
                    "Le nom du stade doit contenir au moins 3 caractères!");
        }

        if (stadiumDAO.existsByName(name)) {
            throw new IllegalArgumentException("Ce stade existe déjà!");
        }

        Stadium stadium = new Stadium();
        stadium.setStadiumName(name);

        stadiumDAO.saveStadium(stadium);
    }

}