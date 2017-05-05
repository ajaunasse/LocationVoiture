package fr.lokacar.locationvoiture.model;

import java.util.Date;

/**
 * Created by ddrapeau2015 on 02/05/2017.
 */

public class Location {

    private int id;
    private Date dateDebutLocation;
    private Date dateFinLocation;
    private Client client;
    private Vehicule vehicule;
    private float prixTotal;

    public Location(){

    }
    public Location(int id, Date dateDebutLocation, Date dateFinLocation, Client client, Vehicule vehicule, float prixTotal) {
        this.id = id;
        this.dateDebutLocation = dateDebutLocation;
        this.dateFinLocation = dateFinLocation;
        this.client = client;
        this.vehicule = vehicule;
        this.prixTotal = prixTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebutLocation() {
        return dateDebutLocation;
    }

    public void setDateDebutLocation(Date dateDebutLocation) {
        this.dateDebutLocation = dateDebutLocation;
    }

    public Date getDateFinLocation() {
        return dateFinLocation;
    }

    public void setDateFinLocation(Date dateFinLocation) {
        this.dateFinLocation = dateFinLocation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public float getPrixTotal() {
        return prixTotal;
    }
    public String getPrixTotalString() {
        return prixTotal + " €" ;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }
}
