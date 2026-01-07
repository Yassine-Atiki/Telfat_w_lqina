package com.firstproject.telfat_w_lqina.service;

import com.firstproject.telfat_w_lqina.dao.PersonDAO;
import com.firstproject.telfat_w_lqina.exception.NotFoundException.ObjectNotFoundException;
import com.firstproject.telfat_w_lqina.models.Person;

public class PersonService {
    PersonDAO personDAO = new PersonDAO();
    public void remove(long personID) {
        Person person = personDAO.findById(personID);
        if (person != null){
            personDAO.remove(person);
        }else {
            throw new ObjectNotFoundException("Person with ID " + personID + " not found.");
        }
    }
}
