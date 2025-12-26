package com.firstproject.telfat_w_lqina.service;

import com.firstproject.telfat_w_lqina.dao.StadiumDAO;
import com.firstproject.telfat_w_lqina.models.Stadium;

import java.util.List;

public class StadiumService {

    private static final StadiumDAO stadiumDAO = new StadiumDAO();

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

    public static List<Stadium> getAllStadiums(){
        return stadiumDAO.getAllStadiums();
    }

}