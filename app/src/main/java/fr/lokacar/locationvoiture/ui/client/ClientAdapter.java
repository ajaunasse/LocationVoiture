package fr.lokacar.locationvoiture.ui.client;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Client;

/**
 * Created by ddrapeau2015 on 03/05/2017.
 */

public class ClientAdapter extends ArrayAdapter<Client> {

    private LayoutInflater inflater;
    private int resId; //R.layout.item_client

    public ClientAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Client> objects) {
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
            viewHolder.textViewVille = (TextView) convertView.findViewById(R.id.textViewVille);

            convertView.setTag(viewHolder); //Enregistre les views

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Récupération des items par leur id
        Client item = getItem(position);

        //On modifie la valeur des champs dans notre vue
        try {

            viewHolder.textViewTitle.setText(item.getNom() + " " + item.getPrenom());
            viewHolder.textViewVille.setText(item.getVille());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    class ViewHolder{
        TextView textViewTitle, textViewVille;
    }
}