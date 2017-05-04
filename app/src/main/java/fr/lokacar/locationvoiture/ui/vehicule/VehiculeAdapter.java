package fr.lokacar.locationvoiture.ui.vehicule;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Vehicule;
import fr.lokacar.locationvoiture.utils.CircleTransform;

/**
 * Created by ddrapeau2015 on 03/05/2017.
 */

public class VehiculeAdapter extends ArrayAdapter<Vehicule> {

    private LayoutInflater inflater;
    private int resId; //R.layout.item_vehicule

    public VehiculeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Vehicule> objects) {
        super(context, resource, objects);

        inflater = LayoutInflater.from(context);
        resId = resource;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(resId, null);

            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = (TextView)convertView.findViewById(R.id.textViewTitle);
            viewHolder.textViewLibelle = (TextView)convertView.findViewById(R.id.textViewLibelle);
            viewHolder.textViewPrix = (TextView)convertView.findViewById(R.id.textViewPrix);
            viewHolder.textViewLoue = (TextView)convertView.findViewById(R.id.textViewLoue);
            viewHolder.imageViewVehicule = (ImageView)convertView.findViewById(R.id.image_vehicule);

            convertView.setTag(viewHolder); //Enregistre les views

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Récupération des items par leur id
        Vehicule item = getItem(position);

        //On modifie la valeur des champs dans notre vue
        viewHolder.textViewTitle.setText(item.getMarque());
        viewHolder.textViewLibelle.setText(item.getLibelle());
        viewHolder.textViewPrix.setText(String.valueOf(item.getPrix()));

        if(item.isEstLoue()){
            viewHolder.textViewLoue.setText("LOUE");
        }else{
            viewHolder.textViewLoue.setText("A LOUER");
        }
        Picasso.with(getContext()).load(item.getImage()).transform(new CircleTransform()).into(viewHolder.imageViewVehicule);

        return convertView;
    }

    class ViewHolder{
        TextView textViewTitle, textViewLibelle, textViewPrix, textViewLoue;
        ImageView imageViewVehicule;
    }
}
