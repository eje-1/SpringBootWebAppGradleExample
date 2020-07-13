package com.example.userSBN.model;

import com.sun.istack.NotNull;
import org.apache.catalina.Store;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    // Klassen variable

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @NotNull
    @Column(name = "name")
    private String name;


    @NotNull
    @Column(name = "vorname")
    private String vorname;


    @NotNull
    @Column(name = "email")
    private String email;


    @NotNull
    @Column(name = "telefon")
    private String telefon;


    @NotNull
    @Column(name = "strasse")
    private String strasse;


    @NotNull
    @Column(name = "ort")
    private String ort;


    @NotNull
    @Column(name = "plz")
    private String plz;


    @NotNull
    @Column(name = "sex")
    private String sex;


    @NotNull
    @Column(name = "spitzname")
    private String spitzname;

    @NotNull
    @Column(name = "birthday")
    private String birthday;

    // Einfache konstruktor

    public User(){

    }

    // Konstruktor

    public User(String name, String vorname, String email, String telefon, String strasse, String ort, String plz, String sex, String spitzname, String birthday) {
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.telefon = telefon;
        this.strasse = strasse;
        this.ort = ort;
        this.plz = plz;
        this.sex = sex;
        this.spitzname = spitzname;
        this.birthday = birthday;
    }


    // Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSpitzname() {
        return spitzname;
    }

    public void setSpitzname(String spitzname) {
        this.spitzname = spitzname;
    }

    public String getBirthday(){
        return birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
}
