package fr.lokacar.locationvoiture.ui.client;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Client;
import fr.lokacar.locationvoiture.utils.Constant;

public class DetailsClientActivity extends AppCompatActivity {

    private TextView adresseClient;
    private TextView cpVilleClient;
    private TextView telClient;
    private TextView emailClient;

    private FloatingActionButton btnEdit;
    private FloatingActionButton btnDelete;

    private Client client = new Client();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_client);

        //Récupération de l'objet Client envoyé depuis l'activité Liste Clients
        client = (Client) getIntent().getExtras().get(Constant.INTENT_CLIENT);

        //Chargement des données du client dans les champs
        loadingData();

        //Listener sur les boutons EDIT et DELETE
        btnEdit = (FloatingActionButton) findViewById(R.id.icon_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editClient(client);
            }
        });
    }

    private void loadingData()
    {
        //Récupération des champs
        adresseClient = (TextView) findViewById(R.id.adresse_client);
        cpVilleClient = (TextView) findViewById(R.id.cp_ville_client);
        telClient = (TextView) findViewById(R.id.tel_client);
        emailClient = (TextView) findViewById(R.id.email_client);

        //Modification des valeurs des champs
        setTitle(client.getNom() + " " + client.getPrenom());
        adresseClient.setText(client.getAdresse());
        cpVilleClient.setText(client.getCp() + " " + client.getVille());
        telClient.setText(client.getTel());
        emailClient.setText(client.getEmail());
    }

    /**
     * Modification infos client -> redirection vers l'activité d'édition
     */
    private void editClient(Client client)
    {
        Intent intent = new Intent(DetailsClientActivity.this, EditClientActivity.class);

        //Envoyer un obbjet
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.INTENT_CLIENT, client);
        intent.putExtras(bundle);

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String messageOK = data.getStringExtra(Constant.MESSAGE_OK);

        Toast.makeText(this, messageOK, Toast.LENGTH_SHORT).show();

        client = (Client) data.getExtras().get(Constant.INTENT_CLIENT_EDIT);
        loadingData();
    }
}
