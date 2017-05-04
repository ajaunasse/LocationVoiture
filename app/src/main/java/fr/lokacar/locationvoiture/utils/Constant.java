package fr.lokacar.locationvoiture.utils;

/**
 * Created by ddrapeau2015 on 02/05/2017.
 */

public class Constant {
    //Pointe vers l'API symfony
    public static final String URL_LOGIN = "http://10.4.140.1:8080/ApiLocationVoiture/web/api/gerant/login?email=%s&password=%s";
    public static final String URL_VEHICULES = "http://10.4.140.1:8080/ApiLocationVoiture/web/api/vehicule/agence/vehicules/%s";
    public static final String URL_CLIENTS = "http://10.4.140.1:8080/ApiLocationVoiture/web/api/client/clients";

    //Clés utilisés dans la transmission des données d'une activité à l'autre
    public static final String INTENT_GERANT =  "INTENT_GERANT";
    public static final String INTENT_VEHICULE = "INTENT_VEHICULE";

    public static final String MESSAGE_OK = "MESSAGE_OK";

    public static final String INTENT_CLIENT = "INTENT_CLIENT";
    public static final String INTENT_CLIENT_EDIT = "INTENT_CLIENT_EDIT";
}
