package com.example.userSBN.controller;

import com.example.userSBN.model.Faecher;
import com.example.userSBN.repository.FaecherRepository;
import com.example.userSBN.services.FaecherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@EnableAutoConfiguration
@Component
@Controller
public class FaecherController {


    @Autowired
    private FaecherRepository faecherRepository;

    @Autowired
    private FaecherService faecherService;

    private String appmode;

    @Autowired
    public FaecherController(Environment environment){

        appmode = environment.getProperty("app-mode");

    }

    //Fächer index
    @RequestMapping(value = "/faecherIndex", method = RequestMethod.GET)
    public String faecherIndex(Model model){

        List<Faecher> allFeacher = faecherRepository.findAll();//get all entries from Entry table into a list
        model.addAttribute("faecher", allFeacher);//get the contents of list into the Thymeleaf template

        model.addAttribute("mode", appmode);

        return "faecherIndex";
    }

    // showpage create Fach
    @RequestMapping(value = "/addFaecher", method = RequestMethod.GET)
    public String createFaecherPage(Model model){

        Faecher newFach = new Faecher();
        model.addAttribute("newFach", newFach);

        return "addFaecher";

    }

    //Add fach to table
    @RequestMapping(value = "/addFaecher", method = RequestMethod.POST)
    public String saveNewFach(Model model, @ModelAttribute("newFach") Faecher faecher){

        String name = faecher.getName();
        String abkuerzung = faecher.getAbkuerzung();
        String stufe = faecher.getStufe();

        if(name != null && name.length() > 0 && abkuerzung != null && abkuerzung.length() > 0
                && stufe != null && stufe.length() > 0){

            Faecher newFaecher = new Faecher(name, abkuerzung, stufe);
            faecherRepository.save(newFaecher);
        }
        return "redirect:/faecherIndex";
    }

    //Edit fach get id
    @RequestMapping(value = {"/editFaecher/update/{id}"}, method = RequestMethod.GET)
    public String showEditFachPage(@PathVariable(name = "id") int id, Model model){

        Optional<Faecher> result = faecherRepository.findById(id);
        model.addAttribute("editFach", result);

        return "editFaecher";
    }

    //Edit fach save method
    @RequestMapping(value = {"/editFaecher/update/{id}"}, method = RequestMethod.POST)
    public String saveEditFach(Model model, int id, @ModelAttribute("editFach") Faecher faecher){

        Optional<Faecher> result = faecherRepository.findById(id);
        Faecher editFach = result.orElse(null);
        if (faecher != null){
            faecherRepository.save(faecher);
        }
        return "redirect:/faecherIndex";
    }

    //Delete method
    @RequestMapping(value = {"/deleteFach/{id}"}, method = RequestMethod.GET)
    public String deleteFach(@PathVariable(value = "id") int fachID){

        faecherRepository.deleteById(fachID); // repository delete by id

        return "redirect:/faecherIndex";

    }






}
