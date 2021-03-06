package fr.lokacar.locationvoiture.model;

import java.io.Serializable;

/**
 * Created by ddrapeau2015 on 02/05/2017.
 */

public class Agence implements Serializable {

    private int id;
    private String nom;
    private String adresse;
    private String codepostal;
    private String ville;
    private float cA;

    public Agence(int id, String nom, String adresse, String codepostal, String ville, int cA) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.codepostal = codepostal;
        this.ville = ville;
        this.cA = cA;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public float getcA() {
        return cA;
    }
    public String getStringCa() {
        return cA + "€" ;
    }

    public void setcA(int cA) {
        this.cA = cA;
    }

    @Override
    public String toString() {
        return "Agence{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", codepostal='" + codepostal + '\'' +
                ", ville='" + ville + '\'' +
                ", cA=" + cA +
                '}';
    }

    public String toString2() {
        return "Agence : "+nom + "\n Adresse : " + adresse +"\n CA :" + cA ;
    }
}
