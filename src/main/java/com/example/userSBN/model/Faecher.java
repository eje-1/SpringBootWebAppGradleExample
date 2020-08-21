package com.example.userSBN.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "faecher")
public class Faecher {


    //Klassen Variable model faecher

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "ankuerzung")
    private String abkuerzung;

    @NotNull
    @Column(name = "stufe")
    private String stufe;


    //Einfache konstruktor
    public Faecher(){

    }


    public Faecher(int id, String name, String abkuerzung, String stufe) {
        this.id = id;
        this.name = name;
        this.abkuerzung = abkuerzung;
        this.stufe = stufe;
    }

    //Getter and Setter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbkuerzung() {
        return abkuerzung;
    }

    public String getStufe() {
        return stufe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbkuerzung(String abkuerzung) {
        this.abkuerzung = abkuerzung;
    }

    public void setStufe(String stufe) {
        this.stufe = stufe;
    }




}
