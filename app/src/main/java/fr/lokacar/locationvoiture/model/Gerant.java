package fr.lokacar.locationvoiture.model;

/**
 * Created by ddrapeau2015 on 02/05/2017.
 */

public class Gerant extends Personne {

    private String email;
    private String password;

    private Agence agence;

    public Gerant(int idPersonne, String nom, String prenom, String email, String password, Agence agence) {
        super(idPersonne, nom, prenom);
        this.email = email;
        this.password = password;
        this.agence = agence;
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
