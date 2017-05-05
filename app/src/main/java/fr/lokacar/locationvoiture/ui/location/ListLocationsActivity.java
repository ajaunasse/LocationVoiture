package fr.lokacar.locationvoiture.ui.location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Location;
import fr.lokacar.locationvoiture.ui.client.ClientAdapter;
import fr.lokacar.locationvoiture.ui.client.ListClientsActivity;
import fr.lokacar.locationvoiture.utils.Constant;
import fr.lokacar.locationvoiture.utils.FastDialog;
import fr.lokacar.locationvoiture.utils.Network;

public class ListLocationsActivity extends AppCompatActivity {

    private ListView listLocations;
    private ArrayList<Location> locations = new ArrayList<>();
    private LocationAdapter adapter;
    private int idAgence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_locations);

        //Récupération de l'élément ListView de la vue activity_list_location
        listLocations = (ListView) findViewById(R.id.list_locations);
        //Récupération de l'id de l'agence
        idAgence = getIntent().getIntExtra(Constant.ID_AGENCE, 1);

        //Appel de la fonction de génération de la liste des locations
        generateListLocations();

        //Initialisation de notre Adapter
        adapter = new LocationAdapter(  //ADAPTER
                ListLocationsActivity.this,
                R.layout.item_location,  //LAYOUT adapté
                locations);

        //On ajoute les clients à la liste de notre vue
        listLocations.setAdapter(adapter);
    }

    /**
     * Fonction de génération de la liste des locations
     * Appel de l'api symfony
     */
    private void generateListLocations()
    {
        //Vérifie si on est bien connecté à internet
        if(Network.isNetworkAvailable(ListLocationsActivity.this))
        {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(ListLocationsActivity.this);

            //Appel de notre api à laquelle on passe l'id de l'agence pour récupérer la liste des locations
            String url = String.format(Constant.URL_LOCATIONS, idAgence);

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Instanciation de l'objet GSON -> Conversion JSON - model
                            Gson gson = new Gson();

                            Type listType = new TypeToken<ArrayList<Location>>(){}.getType();
                            locations.clear();
                            locations.addAll((ArrayList<Location>)gson.fromJson(response, listType));
                            adapter.notifyDataSetChanged();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    FastDialog.showDialog(ListLocationsActivity.this,
                            FastDialog.SIMPLE_DIALOG,
                            "Un problème est survenu avec l'application! Veuillez nous en excuser.");
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        }else {
            FastDialog.showDialog(ListLocationsActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté!");
        }
    }
}
