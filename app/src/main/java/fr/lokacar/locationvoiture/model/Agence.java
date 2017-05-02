package fr.lokacar.locationvoiture.model;

/**
 * Created by ddrapeau2015 on 02/05/2017.
 */

public class Agence {

    private int id;
    private String nom;
    private int ca;

    public Agence(int idAgence, String nom, int ca) {
        this.id = idAgence;
        this.nom = nom;
        this.ca = ca;
    }

    public int getId() {
        return id;
    }

    public void setId(int idAgence) {
        this.id = idAgence;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCa() {
        return ca;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }
}
