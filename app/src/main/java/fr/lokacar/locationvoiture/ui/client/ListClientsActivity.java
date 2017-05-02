package fr.lokacar.locationvoiture.ui.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Client;

public class ListClientsActivity extends AppCompatActivity {

    private ListView listClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clients);

        listClients = (ListView) findViewById(R.id.list_clients);

        generateListClients();
    }

    /**
     * Génération de la liste des clients
     * Récupération en base des clients d'une agence
     */
    private void generateListClients()
    {
        //On lie notre liste à notre vue en passant par l'adapter
        /*listClients.setAdapter(new ArrayAdapter<Client>(
                this,
                android.R.layout.simple_list_item_1,
                clients)
        );*/
    }
}
