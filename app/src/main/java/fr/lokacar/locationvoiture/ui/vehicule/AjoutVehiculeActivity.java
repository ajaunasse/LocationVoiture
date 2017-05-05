package fr.lokacar.locationvoiture.ui.vehicule;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.ui.client.AjoutClientActivity;
import fr.lokacar.locationvoiture.ui.client.ListClientsActivity;
import fr.lokacar.locationvoiture.utils.Constant;
import fr.lokacar.locationvoiture.utils.FastDialog;
import fr.lokacar.locationvoiture.utils.Network;

public class AjoutVehiculeActivity extends AppCompatActivity {

    private EditText formImmatriculation;
    private EditText formMarque;
    private EditText formLibelle;
    private EditText formCaracteristiques;
    private EditText formPrix;
    private EditText formImg;
    private Button btnAddVehicule;

    private int idAgence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_vehicule);

        formImmatriculation = (EditText) findViewById(R.id.form_immatriculation);
        formMarque = (EditText) findViewById(R.id.form_marque);
        formLibelle = (EditText) findViewById(R.id.form_libelle);
        formCaracteristiques = (EditText) findViewById(R.id.form_caracteristiques);
        formPrix = (EditText) findViewById(R.id.form_prix);
        formImg = (EditText) findViewById(R.id.form_img);
        btnAddVehicule = (Button) findViewById(R.id.btn_addVehicule);

        idAgence = getIntent().getIntExtra(Constant.ID_AGENCE, 1);

        btnAddVehicule.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                ajouterClient();
            }
        });
    }

    /**
     * Fonction d'ajout d'un client -> appel de l'api symfony
     */
    private void ajouterClient()
    {
        //Vérifie si on est bien connecté à internet
        if(Network.isNetworkAvailable(AjoutVehiculeActivity.this))
        {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(AjoutVehiculeActivity.this);

            String url = Constant.URL_ADD_VEHICULE;

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            try {
                                JSONObject json = new JSONObject(response) ;
                                if(json.getBoolean("ok")) {
                                    Intent intent = new Intent(AjoutVehiculeActivity.this, ListVehiculesActivity.class);
                                    intent.putExtra(Constant.MESSAGE_OK, json.getString("message"));

                                    //Retour Vue d'avant -> Liste Clients
                                    setResult(Activity.RESULT_OK, intent);
                                    //On termine l'activité en cours
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", error.toString());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("immatriculation", formImmatriculation.getText().toString());
                    params.put("marque", formMarque.getText().toString());
                    params.put("libelle", formLibelle.getText().toString());
                    params.put("caracteristiques", formCaracteristiques.getText().toString());
                    params.put("prix", formPrix.getText().toString());
                    params.put("agence", String.valueOf(idAgence));
                    params.put("image", formImg.getText().toString());

                    return params;
                }
            };
            // Add the request to the RequestQueue.
            queue.add(postRequest);

        }else {
            FastDialog.showDialog(AjoutVehiculeActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté!");
        }
    }
}
