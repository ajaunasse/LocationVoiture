package fr.lokacar.locationvoiture.ui.vehicule;

import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Vehicule;
import fr.lokacar.locationvoiture.ui.client.AjoutClientActivity;
import fr.lokacar.locationvoiture.ui.client.ListClientsActivity;
import fr.lokacar.locationvoiture.utils.Constant;

public class DetailVehiculeActivity extends AppCompatActivity {
    private Vehicule vehicule ;

    private ImageView imageViewVehicule;
    private AppBarLayout appBarLayout;
    private TextView item_description;
    private TextView item_prix;
    private FloatingActionButton buttonRent;
    private int idAgence ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vehicule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        idAgence = getIntent().getIntExtra(Constant.ID_AGENCE, 1);
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

    public void editVehicule(View view) {
        Intent intent = new Intent(DetailVehiculeActivity.this, EditVehiculeActivity.class);

        //Envoyer un obbjet
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.INTENT_VEHICULE, vehicule);
        intent.putExtras(bundle);
        intent.putExtra(Constant.ID_AGENCE, idAgence);

        //On démarre notre activité DetailsVehiculeActivity
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createLocation(View view) {
        Intent intent = new Intent(DetailVehiculeActivity.this, LocationActivity.class);

        //Envoyer un obbjet
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.INTENT_VEHICULE, vehicule);
        intent.putExtras(bundle);

        //On démarre notre activité DetailsVehiculeActivity
        startActivity(intent);
    }
}
