package fr.lokacar.locationvoiture.ui.vehicule;

import android.app.ActionBar;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Vehicule;
import fr.lokacar.locationvoiture.utils.Constant;

public class DetailVehiculeActivity extends AppCompatActivity {
    private Vehicule vehicule ;

    private ImageView imageViewVehicule;
    private AppBarLayout appBarLayout;
    private TextView item_description;
    private TextView item_prix;
    private FloatingActionButton buttonRent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vehicule);
        vehicule = (Vehicule) getIntent().getExtras().get(Constant.INTENT_VEHICULE);
        imageViewVehicule = (ImageView) findViewById(R.id.imageViewVehicule);
        item_description = (TextView) findViewById(R.id.item_description);
        item_prix = (TextView) findViewById(R.id.item_price);
        buttonRent =(FloatingActionButton) findViewById(R.id.buttonRent) ;
        if(vehicule.isEstLoue()) {
            buttonRent.setEnabled(false);
        }

        Picasso.with(DetailVehiculeActivity.this).load(vehicule.getImage()).into(imageViewVehicule);
        setTitle(vehicule.getLibelle());
        item_description.setText(vehicule.getCaracteristiques());
        item_prix.setText(vehicule.getPrixString());


    }
}
