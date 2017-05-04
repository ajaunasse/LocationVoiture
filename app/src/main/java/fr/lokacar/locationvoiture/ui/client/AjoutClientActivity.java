package fr.lokacar.locationvoiture.ui.client;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.utils.Constant;
import fr.lokacar.locationvoiture.utils.FastDialog;
import fr.lokacar.locationvoiture.utils.Network;

public class AjoutClientActivity extends AppCompatActivity {

    private EditText formNom;
    private EditText formPrenom;
    private EditText formAdresse;
    private EditText formCp;
    private EditText formVille;
    private EditText formTel;
    private EditText formEmail;
    private Button btnAddClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_client);

        //Récupération des éléments du formulaire
        formNom = (EditText) findViewById(R.id.form_nom);
        formPrenom = (EditText) findViewById(R.id.form_prenom);
        formAdresse = (EditText) findViewById(R.id.form_adresse);
        formCp = (EditText) findViewById(R.id.form_cp);
        formVille = (EditText) findViewById(R.id.form_ville);
        formTel = (EditText) findViewById(R.id.form_tel);
        formEmail = (EditText) findViewById(R.id.form_email);
        btnAddClient = (Button) findViewById(R.id.btn_addClient);

        //Ajout d'un écouteur sur le bouton
        btnAddClient.setOnClickListener(new View.OnClickListener() {
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
        if(Network.isNetworkAvailable(AjoutClientActivity.this))
        {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(AjoutClientActivity.this);

            String url = Constant.URL_CLIENTS;

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            try {
                                JSONObject json = new JSONObject(response) ;
                                if(json.getBoolean("ok")) {
                                    Intent intent = new Intent(AjoutClientActivity.this, ListClientsActivity.class);
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
                    params.put("nom", formNom.getText().toString());
                    params.put("prenom", formPrenom.getText().toString());
                    params.put("adresse", formAdresse.getText().toString());
                    params.put("codepostal", formCp.getText().toString());
                    params.put("ville", formVille.getText().toString());
                    params.put("email", formEmail.getText().toString());
                    params.put("telephone", formTel.getText().toString());

                    return params;
                }
            };
            // Add the request to the RequestQueue.
            queue.add(postRequest);

        }else {
            FastDialog.showDialog(AjoutClientActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté!");
        }
    }
}
