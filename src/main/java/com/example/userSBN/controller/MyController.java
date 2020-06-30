package com.example.userSBN.controller;

import com.example.userSBN.model.User;
import com.example.userSBN.repository.UserRepository;
import com.example.userSBN.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@EnableAutoConfiguration
@Component
@org.springframework.stereotype.Controller
public class MyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    //Go to person List
    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
    public String personList(Model model) {

        List<User> allUsers = userRepository.findAll(); //get all entries from Entry table into a list
        model.addAttribute("users", allUsers);//get the contents of list into the Thymeleaf template

        return "personList";
    }

/*
    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
    public String showAddPersonPage(@ModelAttribute("name") String name, @ModelAttribute("vorname") String vorname, @ModelAttribute("email") String email,
                                    @ModelAttribute("telefon") String telefon, @ModelAttribute("strasse") String strasse, @ModelAttribute("ort") String ort ,
                                    @ModelAttribute("plz") String plz, @ModelAttribute("sex") String sex, @ModelAttribute("geburtstag") String geburtstag,
                                    @ModelAttribute("spitzname") String spitzname) {

        User person = new User(name, vorname, email, telefon, strasse, ort, plz, sex, geburtstag, spitzname);
        userRepository.save(person);

        return "addPerson";
    }
*/

    //addPerson show page
    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String showAddPerson(Model model) {

        User newUser = new User();
        model.addAttribute("newUser", newUser);

        return "addPerson";
    }

    //add user to database mvc
    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public String saveAddPerson(Model model, @ModelAttribute("newUser") User user) {

        String firstName = user.getName();
        String lastName = user.getVorname();
        String mail = user.getEmail();
        String telefon = user.getTelefon();
        String street = user.getStrasse();
        String place = user.getOrt();
        String plz = user.getPlz();
        String sex = user.getSex();
        String birthday = user.getGeburtstag();
        String nick = user.getSpitzname();

        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            User newPerson = new User(firstName, lastName, mail, telefon, street, place, plz, sex, birthday, nick);
            System.out.println("Add");

            userRepository.save(newPerson);

        }
        return "redirect:/personList";
    }

    //delete an user
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteMethod(@PathVariable(value = "id") int userID){

        userRepository.deleteById(userID);
        return "redirect:/personList";
    }

    // editUser get Id
    @RequestMapping(value = { "/editPerson/update/{id}" }, method = RequestMethod.GET)
    public String showEditPersonPage(@PathVariable(name = "id") int id, Model model)  {

        Optional<User> result = userRepository.findById(id);
        model.addAttribute("editUser", result);


        return "editPerson";
    }

    @RequestMapping(value = {"/editPerson/update/{id}"}, method = RequestMethod.POST)
    public String saveEditPerson(Model model, int id, @ModelAttribute("editUser") User user, @ModelAttribute("name") String name, @ModelAttribute("vorname") String vorname, @ModelAttribute("email") String email,
                                 @ModelAttribute("telefon") String telefon, @ModelAttribute("strasse") String strasse, @ModelAttribute("ort") String ort ,
                                 @ModelAttribute("plz") String plz, @ModelAttribute("sex") String sex, @ModelAttribute("geburtstag") String geburtstag,
                                 @ModelAttribute("spitzname") String spitzname) {

        Optional<User> result = userRepository.findById(id);
        User editPerson = result.orElse(null);
        if(editPerson != null){
            editPerson.setName(name);
            editPerson.setVorname(vorname);
            editPerson.setEmail(email);
            editPerson.setTelefon(telefon);
            editPerson.setStrasse(strasse);
            editPerson.setOrt(ort);
            editPerson.setPlz(plz);
            editPerson.setSex(sex);
            editPerson.setGeburstag(geburtstag);
            editPerson.setSpitzname(spitzname);
            userRepository.save(editPerson);
        }

        return "redirect:/personList";

    }

    @RequestMapping(value = "/personList/suche/{name}", method = RequestMethod.GET)
    public String viewHomePage(Model model, @Param("keyword") String keyword) {

        model.addAttribute("userSuche", userRepository.findByTitle(keyword));

        return "redirect:/personList";
    }



}
