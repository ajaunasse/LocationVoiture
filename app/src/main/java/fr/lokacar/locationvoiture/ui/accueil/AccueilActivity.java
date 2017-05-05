package fr.lokacar.locationvoiture.ui.accueil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.lokacar.locationvoiture.ui.location.ListLocationsActivity;
import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Gerant;
import fr.lokacar.locationvoiture.ui.client.ListClientsActivity;
import fr.lokacar.locationvoiture.ui.vehicule.ListVehiculesActivity;
import fr.lokacar.locationvoiture.utils.Constant;

public class AccueilActivity extends AppCompatActivity {

    private TextView titleAgenceAccueil;
    private Button btnListVehicules;
    private Button btnGestionLocation;
    private Button btnListClients;
    private Gerant gerant ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        titleAgenceAccueil = (TextView) findViewById(R.id.title_agence_accueil);
        btnListVehicules = (Button) findViewById(R.id.btn_listVehicules);
        btnGestionLocation = (Button) findViewById(R.id.btn_gestionLocation);
        btnListClients = (Button) findViewById(R.id.btn_listClients);
        gerant = (Gerant) getIntent().getExtras().get(Constant.INTENT_GERANT);
        titleAgenceAccueil.setText(gerant.getAgence().toString2());

        //Accès à la liste des véhicules de l'agence
        btnListVehicules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idAgence = gerant.getAgence().getId();
                Intent intent = new Intent(AccueilActivity.this, ListVehiculesActivity.class);
                intent.putExtra("idAgence", idAgence);

                startActivity(intent);
            }
        });

        //Accès à l'interface de gestion des locations
        btnGestionLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idAgence = gerant.getAgence().getId();
                Intent intent = new Intent(AccueilActivity.this, ListLocationsActivity.class);
                intent.putExtra("idAgence", idAgence);

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
