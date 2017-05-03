package fr.lokacar.locationvoiture.ui.vehicule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Vehicule;
import fr.lokacar.locationvoiture.utils.Constant;

public class DetailsVehiculeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Récupération du véhicule envoyé par le bundle
        Vehicule vehicule = (Vehicule) getIntent().getExtras().get(Constant.INTENT_VEHICULE);
    }

}
