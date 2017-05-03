package fr.lokacar.locationvoiture.model;

import java.io.Serializable;

/**
 * Created by ddrapeau2015 on 02/05/2017.
 */

public class Vehicule implements Serializable{

    private int id;
    private String immatriculation;
    private String marque;
    private String libelle;
    private String caracteristiques;
    private float prix;
    private boolean estLoue;

    private Agence agence;

    private String image;

    public Vehicule(int id, String immatriculation, String marque, String libelle, String caracteristiques, float prix, boolean estLoue, Agence agence, String image) {
        this.id = id;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.libelle = libelle;
        this.caracteristiques = caracteristiques;
        this.prix = prix;
        this.estLoue = estLoue;
        this.agence = agence;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isEstLoue() {
        return estLoue;
    }

    public void setEstLoue(boolean estLoue) {
        this.estLoue = estLoue;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", immatriculation='" + immatriculation + '\'' +
                ", marque='" + marque + '\'' +
                ", libelle='" + libelle + '\'' +
                ", caracteristiques='" + caracteristiques + '\'' +
                ", prix=" + prix +
                ", estLoue=" + estLoue +
                ", agence=" + agence +
                ", image='" + image + '\'' +
                '}';
    }
}
