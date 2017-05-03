package fr.lokacar.locationvoiture.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by ddrapeau2015 on 02/05/2017.
 */

public class Gerant extends Personne implements Serializable {

    private String email;
    private String password;
    private Agence agence;

    public Gerant(int id, String nom, String prenom, String email, String password, Agence agence) {
        super(id, nom, prenom);
        this.email = email;
        this.password = password;
        this.agence = agence;
    }

    protected Gerant(Parcel in) {
        email = in.readString();
        password = in.readString();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    @Override
    public String toString() {
        return "Gerant{" +
                "mail='" + email + '\'' +
                ", password='" + password + '\'' +
                ", agence=" + agence +
                '}';
    }
}
