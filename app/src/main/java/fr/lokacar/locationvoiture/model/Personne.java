package fr.lokacar.locationvoiture.model;

import java.io.Serializable;

/**
 * Created by ddrapeau2015 on 02/05/2017.
 */

public class Personne implements Serializable{

    private int id;
    private String nom;
    private String prenom;

    //Constructeur vide -> bean
    public Personne(){

    }

    public Personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
