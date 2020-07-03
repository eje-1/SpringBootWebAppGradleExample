package com.example.userSBN.controller;

import com.example.userSBN.model.User;
import com.example.userSBN.repository.UserRepository;
import com.example.userSBN.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Optional;


/**Controller ist eine Klasse  Controller, die die Anforderung vom Benutzer behandelt und den Strom (flow) der Applikation kontrolliert.
 * Das Kommentar  @SpringBootApplication ist so ähnlich wie die Benutzung von @Configuration, @EnableAutoConfiguration und  @ComponentScan mit ihren standardmäßigen Attribute
 * Deshalb hilft  @SpringBootApplication Ihnen bei der automatischen Konfigurierung vom Spring, und ganz den Projekt automatisch überprüfen (Scan) um die Elemente von Spring  ( Controller, Bean, Service,...) zu finden*/

@EnableAutoConfiguration
@Component
@Controller
public class MyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;


    /**Diese funktion sollte aufgerufen werden wenn es ein get request rein kommt "
    * path = "" <--- der path der methodt
    * produces = MediaType.Application_JSON_VALUE    <--- wandle das ganze in ein json format*/
    /**Die Annotation @GetMapping("/")legt fest, dass GET-Requests auf Root von der darauf folgenden Methode (hier also home()) behandelt werden.*/
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET) //path wo wir das meppen mochten
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    private String appMode;

    @Autowired
    public MyController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/index2")
    public String index2(Model model){

        List<User> allUsers = userRepository.findAll(); //get all entries from Entry table into a list
        model.addAttribute("users", allUsers);//get the contents of list into the Thymeleaf template

        model.addAttribute("mode", appMode);

        return "index2";
    }

    //Go to person List
    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
    public String personList(Model model) {

        List<User> allUsers = userRepository.findAll(); //get all entries from Entry table into a list
        model.addAttribute("users", allUsers);//get the contents of list into the Thymeleaf template

        return "personList";
    }

    @RequestMapping(value = "/personList/suche", method = RequestMethod.GET)
    public String viewHomePage(Model model, @Param("keyword") String keyword) {

        List<User> listUser = service.listAll(keyword);
        model.addAttribute("user", listUser);
        model.addAttribute("keyword", keyword);
        System.out.println(keyword);

        return "redirect:/personList";
    }

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
    public String saveEditPerson(Model model, int id, @ModelAttribute("editUser") User user) {

        Optional<User> result = userRepository.findById(id);
        User editPerson = result.orElse(null);
        if(user != null){
           userRepository.save(user);
        }

        return "redirect:/personList";

    }

}
