package fr.lokacar.locationvoiture.ui.client;

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
import fr.lokacar.locationvoiture.model.Client;
import fr.lokacar.locationvoiture.utils.Constant;
import fr.lokacar.locationvoiture.utils.FastDialog;
import fr.lokacar.locationvoiture.utils.Network;

public class EditClientActivity extends AppCompatActivity {

    private Client client = new Client();

    private EditText formNom;
    private EditText formPrenom;
    private EditText formAdresse;
    private EditText formCp;
    private EditText formVille;
    private EditText formTel;
    private EditText formEmail;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);

        //Edition des champs avec les données du client que l'on souhaite modifier
        editChamps();

        btnSave = (Button) findViewById(R.id.btn_saveModifsClient);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveModifs();
            }
        });
    }

    private void editChamps()
    {
        client = (Client) getIntent().getExtras().get(Constant.INTENT_CLIENT);

        //Récupération des éléments du formulaire
        formNom = (EditText) findViewById(R.id.form_nom);
        formPrenom = (EditText) findViewById(R.id.form_prenom);
        formAdresse = (EditText) findViewById(R.id.form_adresse);
        formCp = (EditText) findViewById(R.id.form_cp);
        formVille = (EditText) findViewById(R.id.form_ville);
        formTel = (EditText) findViewById(R.id.form_tel);
        formEmail = (EditText) findViewById(R.id.form_email);

        //Modification des valeurs des champs
        formNom.setText(client.getNom());
        formPrenom.setText(client.getPrenom());
        formAdresse.setText(client.getAdresse());
        formCp.setText(client.getCp());
        formVille.setText(client.getVille());
        formTel.setText(client.getTel());
        formEmail.setText(client.getEmail());
    }

    private void saveModifs()
    {
        //Vérifie si on est bien connecté à internet
        if(Network.isNetworkAvailable(EditClientActivity.this))
        {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(EditClientActivity.this);

            String url = String.format(Constant.URL_CLIENTS + "/" + client.getId());

            StringRequest postRequest = new StringRequest(Request.Method.PUT, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            try {
                                JSONObject json = new JSONObject(response) ;
                                if(json.getBoolean("ok")) {

                                    client = editObjectClient();

                                    Intent intent = new Intent(EditClientActivity.this, DetailsClientActivity.class);
                                    intent.putExtra(Constant.MESSAGE_OK, json.getString("message"));
                                    intent.putExtra(Constant.INTENT_CLIENT_EDIT, client);

                                    //Retour Vue d'avant
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
            FastDialog.showDialog(EditClientActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté!");
        }
    }

    /**
     * Modification des attributs du Client lors de la sauvegarde
     * @return
     */
    private Client editObjectClient()
    {
        client.setNom(formNom.getText().toString());
        client.setPrenom(formPrenom.getText().toString());
        client.setAdresse(formAdresse.getText().toString());
        client.setCp(formCp.getText().toString());
        client.setVille(formVille.getText().toString());
        client.setEmail(formEmail.getText().toString());
        client.setTel(formTel.getText().toString());

        return client;
    }
}
