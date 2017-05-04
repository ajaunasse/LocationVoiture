package fr.lokacar.locationvoiture.ui.vehicule;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
import fr.lokacar.locationvoiture.model.Vehicule;
import fr.lokacar.locationvoiture.utils.Constant;
import fr.lokacar.locationvoiture.utils.FastDialog;
import fr.lokacar.locationvoiture.utils.Network;

public class EditVehiculeActivity extends AppCompatActivity {

    private Vehicule vehicule ;
    private EditText editTextMarque;
    private EditText editTextLibelle;
    private EditText editTextPrix ;
    private EditText editTextCaracteristique;
    private EditText editTextImat;
    private EditText editTextImage ;
    private ToggleButton toggleButtonLoue;
    private int idAgence ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vehicule);
        idAgence = getIntent().getIntExtra(Constant.ID_AGENCE, 1);

        vehicule = (Vehicule) getIntent().getExtras().get(Constant.INTENT_VEHICULE);

        editTextMarque = (EditText) findViewById(R.id.editTextMarque);
        editTextLibelle = (EditText) findViewById(R.id.editTextLibelle);
        editTextPrix = (EditText) findViewById(R.id.editTextPrice);
        editTextImat = (EditText) findViewById(R.id.editTextImmat);
        editTextImage = (EditText) findViewById(R.id.editTextImage);
        editTextCaracteristique =(EditText) findViewById(R.id.editTextDescription) ;
        toggleButtonLoue = (ToggleButton) findViewById(R.id.toggleButtonLocation);

        refreshView();
    }

    private void refreshView() {
        editTextMarque.setText(vehicule.getMarque());
        editTextLibelle.setText(vehicule.getLibelle());
        editTextImat.setText(vehicule.getImmatriculation());
        editTextImage.setText(vehicule.getImage());
        editTextCaracteristique.setText(vehicule.getCaracteristiques());
        editTextPrix.setText(String.valueOf(vehicule.getPrix()));
        toggleButtonLoue.setChecked(vehicule.isEstLoue());
    }

    public void editVehicule(View view) {
        //Vérifie si on est bien connecté à internet
        if(Network.isNetworkAvailable(EditVehiculeActivity.this))
        {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(EditVehiculeActivity.this);

            String url = String.format(Constant.URL_CRUD_VEHICULE, vehicule.getId());

            StringRequest postRequest = new StringRequest(Request.Method.PUT, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject json = new JSONObject(response) ;
                                if(json.getBoolean("ok")) {
                                    Intent intent = new Intent(EditVehiculeActivity.this, ListVehiculesActivity.class);
                                    intent.putExtra("ok", json.getString("message"));
                                    setResult(Activity.RESULT_OK, intent);
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
                    params.put("immatriculation", editTextImat.getText().toString());
                    params.put("marque", editTextMarque.getText().toString());
                    params.put("libelle", editTextLibelle.getText().toString());
                    params.put("caracteristiques", editTextCaracteristique.getText().toString());
                    params.put("prix", editTextPrix.getText().toString());
                    params.put("agence", String.valueOf(idAgence));
                    params.put("image", editTextImage.getText().toString());
                    params.put("estloue", toggleButtonLoue.isChecked() ? "true" : "false") ;

                    return params;
                }
            };
            // Add the request to the RequestQueue.
            queue.add(postRequest);

        }else {
            FastDialog.showDialog(EditVehiculeActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté!");
        }
    }
}
