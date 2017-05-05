package fr.lokacar.locationvoiture.ui.accueil;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Gerant;
import fr.lokacar.locationvoiture.ui.client.ListClientsActivity;
import fr.lokacar.locationvoiture.ui.vehicule.ListVehiculesActivity;
import fr.lokacar.locationvoiture.ui.vehicule.LocationActivity;
import fr.lokacar.locationvoiture.utils.Constant;

public class AccueilActivity extends AppCompatActivity {

    private Button btnListVehicules;
    private Button btnGestionLocation;
    private Button btnListClients;
    private Gerant gerant ;
    private TextView textViewNomAgence;
    private TextView textViewAdresseAgence;
    private TextView textViewCaAgence;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        btnListVehicules = (Button) findViewById(R.id.btn_listVehicules);
        btnGestionLocation = (Button) findViewById(R.id.btn_gestionLocation);
        btnListClients = (Button) findViewById(R.id.btn_listClients);
        textViewNomAgence = (TextView) findViewById(R.id.textViewNomAgence);
        textViewAdresseAgence = (TextView) findViewById(R.id.textViewAdresseAgence);
        textViewCaAgence = (TextView) findViewById(R.id.textViewCaAgence);
        gerant = (Gerant) getIntent().getExtras().get(Constant.INTENT_GERANT);

        textViewNomAgence.setText(String.format(getString(R.string.nomAgence), gerant.getAgence().getNom()));
        textViewAdresseAgence.setText(String.format(getString(R.string.adresseAgence), gerant.getAgence().getAdresse()));
        textViewCaAgence.setText(String.format(getString(R.string.caAgence), gerant.getAgence().getStringCa()));
        btnListVehicules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idAgence = gerant.getAgence().getId();

                Intent intent = new Intent(AccueilActivity.this, ListVehiculesActivity.class);
                intent.putExtra(getString(R.string.idAgece), idAgence);

                startActivity(intent);
            }
        });

        //Accès à l'interface de gestion des locations
        btnGestionLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccueilActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

        //Accès à la liste des clients de l'agence
        btnListClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccueilActivity.this, ListClientsActivity.class);
                startActivity(intent);
            }
        });
    }
}
