package fr.lokacar.locationvoiture.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ddrapeau2015 on 02/05/2017.
 */

public class Gerant extends Personne implements Parcelable {

    private String email;
    private String password;

    private Agence agence;

    public Gerant(int idPersonne, String nom, String prenom, String email, String password, Agence agence) {
        super(idPersonne, nom, prenom);
        this.email = email;
        this.password = password;
        this.agence = agence;
    }

    protected Gerant(Parcel in) {
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<Gerant> CREATOR = new Creator<Gerant>() {
        @Override
        public Gerant createFromParcel(Parcel in) {
            return new Gerant(in);
        }

        @Override
        public Gerant[] newArray(int size) {
            return new Gerant[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(password);
    }
}
