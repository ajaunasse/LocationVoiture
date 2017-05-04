package fr.lokacar.locationvoiture.ui.vehicule;

import android.app.ActionBar;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Vehicule;
import fr.lokacar.locationvoiture.utils.Constant;

public class DetailVehiculeActivity extends AppCompatActivity {
    private Vehicule vehicule ;

    private ImageView imageViewVehicule;
    private AppBarLayout appBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vehicule);
        vehicule = (Vehicule) getIntent().getExtras().get(Constant.INTENT_VEHICULE);
        imageViewVehicule = (ImageView) findViewById(R.id.imageViewVehicule);
        Picasso.with(DetailVehiculeActivity.this).load(vehicule.getImage()).into(imageViewVehicule);
//        ActionBar ab = getActionBar();
//        ab.setTitle(vehicule.getMarque() + vehicule.getLibelle());

    }
}
