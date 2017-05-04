package fr.lokacar.locationvoiture.ui.vehicule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import java.util.List;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Agence;
import fr.lokacar.locationvoiture.model.Gerant;
import fr.lokacar.locationvoiture.model.Vehicule;
import fr.lokacar.locationvoiture.ui.connexion.ConnectionActivity;
import fr.lokacar.locationvoiture.utils.Constant;
import fr.lokacar.locationvoiture.utils.FastDialog;
import fr.lokacar.locationvoiture.utils.Network;

public class ListVehiculesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Vehicule> vehicules = new ArrayList<>();
    private ListView listVehicules;
    private int idAgence ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vehicules);
        idAgence = getIntent().getIntExtra(Constant.ID_AGENCE, 1);
        generateList();
    }

    /**
     * Fonction de genration de la liste des vehicules avec l'appel à l'API Symfony
     */
    private void generateList() {


        listVehicules = (ListView) findViewById(R.id.list_vehicules);

        //Vérifie si on est bien connecté à internet
        if(Network.isNetworkAvailable(ListVehiculesActivity.this))
        {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(ListVehiculesActivity.this);

            //Appel de notre api à laquelle on passe l'id de l'agence pour récupérer la liste des véhicules
            String url = String.format(Constant.URL_VEHICULES, idAgence);

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Instanciation de l'objet GSON -> Conversion JSON - model
                            Gson gson = new Gson();

                            Type listType = new TypeToken<ArrayList<Vehicule>>(){}.getType();

                            vehicules = new Gson().fromJson(response, listType);

                            //On ajoute les véhicules à la liste de notre vue
                            listVehicules.setAdapter(new VehiculeAdapter(  //ADAPTER
                                    ListVehiculesActivity.this,
                                    R.layout.item_vehicule,  //LAYOUT adapté
                                    vehicules)
                            );

                            //Ecouteur sur les éléments de la liste
                            listVehicules.setOnItemClickListener(ListVehiculesActivity.this);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    FastDialog.showDialog(ListVehiculesActivity.this,
                            FastDialog.SIMPLE_DIALOG,
                            "Un problème est survenu avec l'application! Veuillez nous en excuser.");
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        }else {
            FastDialog.showDialog(ListVehiculesActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté!");
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(ListVehiculesActivity.this, DetailVehiculeActivity.class);

        //Envoyer un obbjet
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.INTENT_VEHICULE, vehicules.get(position));
        intent.putExtras(bundle);
        intent.putExtra(Constant.ID_AGENCE, idAgence);

        //On démarre notre activité DetailsVehiculeActivity
        startActivity(intent);
    }
}
