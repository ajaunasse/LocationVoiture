package fr.lokacar.locationvoiture.model;

import java.io.Serializable;

/**
 * Created by ddrapeau2015 on 02/05/2017.
 */

public class Client extends Personne implements Serializable {

    private String adresse;
    private String codepostal;
    private String ville;
    private String telephone;

    private String email;

    public Client(int idPersonne, String nom, String prenom, String adresse, String codepostal, String ville, String telephone, String email) {
        super(idPersonne, nom, prenom);
        this.adresse = adresse;
        this.codepostal = codepostal;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCp() {
        return codepostal;
    }

    public void setCp(String codepostal) {
        this.codepostal = codepostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTel() {
        return telephone;
    }

    public void setTel(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
