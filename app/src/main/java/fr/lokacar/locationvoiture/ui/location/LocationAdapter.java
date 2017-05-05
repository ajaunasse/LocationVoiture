package fr.lokacar.locationvoiture.ui.location;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Location;

/**
 * Created by ddrapeau2015 on 05/05/2017.
 */

public class LocationAdapter extends ArrayAdapter<Location> {

    private LayoutInflater inflater;
    private int resId; //R.layout.item_client

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public LocationAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Location> objects) {
        super(context, resource, objects);

        inflater = LayoutInflater.from(context);
        resId = resource;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LocationAdapter.ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(resId, null);

            viewHolder = new LocationAdapter.ViewHolder();
            viewHolder.textViewVehicule = (TextView)convertView.findViewById(R.id.textViewVehicule);
            viewHolder.textViewClient = (TextView) convertView.findViewById(R.id.textViewClient);
            viewHolder.textViewPrix = (TextView) convertView.findViewById(R.id.textViewPrix);
            viewHolder.textViewDateFin = (TextView) convertView.findViewById(R.id.textViewDateFin);

            convertView.setTag(viewHolder); //Enregistre les views

        }else{
            viewHolder = (LocationAdapter.ViewHolder) convertView.getTag();
        }

        //Récupération des items par leur id
        Location item = getItem(position);

        //On modifie la valeur des champs dans notre vue
        try {
            viewHolder.textViewVehicule.setText(item.getVehicule().getLibelle() + " - " + item.getVehicule().getMarque());
            viewHolder.textViewClient.setText(item.getClient().getNom() + " " + item.getClient().getPrenom());
            viewHolder.textViewPrix.setText(item.getPrixTotalString());
            viewHolder.textViewDateFin.setText(dateFormat.format(item.getDateFinLocation()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    class ViewHolder{
        TextView textViewVehicule, textViewClient, textViewPrix, textViewDateFin;
    }
}
