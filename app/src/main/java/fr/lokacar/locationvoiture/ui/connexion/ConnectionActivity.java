package fr.lokacar.locationvoiture.ui.connexion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Gerant;
import fr.lokacar.locationvoiture.ui.accueil.AccueilActivity;
import fr.lokacar.locationvoiture.utils.Constant;
import fr.lokacar.locationvoiture.utils.FastDialog;
import fr.lokacar.locationvoiture.utils.Network;

public class ConnectionActivity extends AppCompatActivity {

    private Button btnConnexion;
    private EditText editTextLogin;
    private EditText editTextMdp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        btnConnexion = (Button) findViewById(R.id.btn_connexion);

        //Récupération des valeurs saisies dans les champs
        editTextLogin = (EditText) findViewById(R.id.editText_login);
        editTextMdp = (EditText) findViewById(R.id.editText_mdp);

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connect();
            }
        });
    }

    /**
     * Fonction de connexion -> redirection vers la page d'accueil si OK
     */
    private void connect() {

        String login = editTextLogin.getText().toString();
        String mdp = editTextMdp.getText().toString();

        if(!login.isEmpty() && !mdp.isEmpty()){
            //Vérifie si on est bien connecté à internet
            if(Network.isNetworkAvailable(ConnectionActivity.this))
            {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(ConnectionActivity.this);

                //Appel de notre api à laquelle on passe le login et le mdp saisis
                String url = String.format(Constant.URL_LOGIN, login.trim(), mdp.trim());

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Instanciation de l'objet GSON -> Conversion JSON - model
                                try {
                                    //Constructions objets Json à partir de response
                                    JSONObject reader = new JSONObject(response);
                                    boolean ok = reader.getBoolean("ok") ;

                                    JSONObject main  = reader.getJSONObject("object");

                                    Gson gson = new Gson();
                                    //Construction de notre objet gerant
                                    Gerant gerant = gson.fromJson(String.valueOf(main), Gerant.class);

                                    if(null != gerant){
                                        if(true == ok){
                                            //Si le mot de passe est correcte alors on est redirigé vers la page d'accueil
                                            connectionValide(gerant);
                                        }else{
                                            FastDialog.showDialog(ConnectionActivity.this,
                                                    FastDialog.SIMPLE_DIALOG,
                                                    "Mot de passe incorrect!");
                                            editTextMdp.getText().clear();
                                        }
                                    }else{
                                        FastDialog.showDialog(ConnectionActivity.this,
                                                FastDialog.SIMPLE_DIALOG,
                                                "Login et/ou mot de passe incorrects!");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        FastDialog.showDialog(ConnectionActivity.this,
                                FastDialog.SIMPLE_DIALOG,
                                "Un problème est survenu avec l'application! Veuillez nous en excuser.");
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }else {
                FastDialog.showDialog(ConnectionActivity.this,
                        FastDialog.SIMPLE_DIALOG,
                        "Vous devez être connecté!");
            }
        }else{
            FastDialog.showDialog(ConnectionActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Les champs Login et Mot de passe doivent être saisis!");
        }
    }

    /**
     * Connection valide -> on est redirigé vers l'accueil
     */
    private void connectionValide(Gerant gerant){
        Intent intent = new Intent(ConnectionActivity.this, AccueilActivity.class);

        intent.putExtra("personneConnectee", gerant);

        //On démarre notre activité ViewArticle
        startActivity(intent);
        finish();
    }
}
