package fr.lokacar.locationvoiture.model;

import java.io.Serializable;

/**
 * Created by ddrapeau2015 on 02/05/2017.
 */

public class Client extends Personne implements Serializable {

    private String adresse;
    private int cp;
    private String ville;
    private String tel;

    public Client(int idPersonne, String nom, String prenom, String adresse, int cp, String ville, String tel) {
        super(idPersonne, nom, prenom);
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Client{" +
                "adresse='" + adresse + '\'' +
                ", cp=" + cp +
                ", ville='" + ville + '\'' +
                ", tel='" + tel +
                '}';
    }
}
