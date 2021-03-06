package fr.lokacar.locationvoiture.ui.vehicule;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.lang.reflect.Array;
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
    private VehiculeAdapter adapter;
    private FloatingActionButton addVehicule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vehicules);
        idAgence = getIntent().getIntExtra(Constant.ID_AGENCE, 1);
        listVehicules = (ListView) findViewById(R.id.list_vehicules);
        //On ajoute les véhicules à la liste de notre vue
        adapter = new VehiculeAdapter(ListVehiculesActivity.this,
                R.layout.item_vehicule,
                vehicules);
        listVehicules.setAdapter(adapter);
        listVehicules.setOnItemClickListener(ListVehiculesActivity.this);

        generateList();

        addVehicule = (FloatingActionButton) findViewById(R.id.add_vehicule);
        addVehicule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajoutVehicule();
            }
        });
    }

    /**
     * Fonction de genration de la liste des vehicules avec l'appel à l'API Symfony
     */
    private void generateList() {

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
                            vehicules.clear();
                            vehicules.addAll((ArrayList<Vehicule>)gson.fromJson(response, listType));
                            adapter.notifyDataSetChanged();
                            //Ecouteur sur les éléments de la liste

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

        //Envoyer un objet
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.INTENT_VEHICULE, vehicules.get(position));
        intent.putExtras(bundle);
        intent.putExtra(Constant.ID_AGENCE, idAgence);

        //On démarre notre activité DetailsVehiculeActivity
        startActivityForResult(intent,1);
    }

    private void ajoutVehicule()
    {
        Intent intent = new Intent(ListVehiculesActivity.this, AjoutVehiculeActivity.class);
        intent.putExtra(Constant.ID_AGENCE, idAgence);

        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null) {
            String message =  data.getExtras().getString(Constant.MESSAGE_OK) ;
            Toast.makeText(ListVehiculesActivity.this, message, Toast.LENGTH_SHORT).show();
        }
        generateList();
    }
}
