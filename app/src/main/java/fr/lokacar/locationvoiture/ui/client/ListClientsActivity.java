package fr.lokacar.locationvoiture.ui.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Client;
import fr.lokacar.locationvoiture.utils.Constant;
import fr.lokacar.locationvoiture.utils.FastDialog;
import fr.lokacar.locationvoiture.utils.Network;

public class ListClientsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listClients;

    private ArrayList<Client> clients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clients);

        generateListClients();
    }

    /**
     * Génération de la liste des clients
     * Récupération en base des clients d'une agence
     */
    private void generateListClients()
    {
        listClients = (ListView) findViewById(R.id.list_clients);


        //Vérifie si on est bien connecté à internet
        if(Network.isNetworkAvailable(ListClientsActivity.this))
        {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(ListClientsActivity.this);

            //Appel de notre api à laquelle on passe l'id de l'agence pour récupérer la liste des véhicules
            String url = Constant.URL_CLIENTS;

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Instanciation de l'objet GSON -> Conversion JSON - model
                            Gson gson = new Gson();

                            Type listType = new TypeToken<ArrayList<Client>>(){}.getType();

                            try {
                                clients = new Gson().fromJson(response, listType);
                            } catch (JsonSyntaxException jse) {
                                jse.printStackTrace();
                            }


                            //On ajoute les véhicules à la liste de notre vue
                            listClients.setAdapter(new ClientAdapter(  //ADAPTER
                                    ListClientsActivity.this,
                                    R.layout.item_client,  //LAYOUT adapté
                                    clients)
                            );

                            //Ecouteur sur les éléments de la liste
                            //listClients.setOnItemClickListener(ListClientsActivity.this);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    FastDialog.showDialog(ListClientsActivity.this,
                            FastDialog.SIMPLE_DIALOG,
                            "Un problème est survenu avec l'application! Veuillez nous en excuser.");
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        }else {
            FastDialog.showDialog(ListClientsActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté!");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
