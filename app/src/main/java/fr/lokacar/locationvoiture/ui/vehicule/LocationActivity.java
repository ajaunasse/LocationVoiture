package fr.lokacar.locationvoiture.ui.vehicule;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.model.Client;
import fr.lokacar.locationvoiture.model.Location;
import fr.lokacar.locationvoiture.model.Vehicule;
import fr.lokacar.locationvoiture.ui.client.ClientAdapter;
import fr.lokacar.locationvoiture.ui.client.ListClientsActivity;
import fr.lokacar.locationvoiture.utils.Constant;
import fr.lokacar.locationvoiture.utils.FastDialog;
import fr.lokacar.locationvoiture.utils.Network;

public class LocationActivity extends AppCompatActivity {

    private String[] tabClients ;
    private EditText editTextVoiture;
    private AutoCompleteTextView editTextClient;
    private EditText editTextDateDebut;
    private EditText editTextDateFin;
    private Button btnAddClient;
    private ArrayList<Client> clients = new ArrayList<>() ;
    private Vehicule vehicule;
    private ArrayAdapter<String> adapter ;
    private AutoCompleteTextView autoComplete ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        editTextVoiture = (EditText) findViewById(R.id.editTextVoiture);
        editTextClient = (AutoCompleteTextView) findViewById(R.id.editTextClient);
        editTextDateDebut = (EditText) findViewById(R.id.editTextDateDebut);
        editTextDateFin = (EditText) findViewById(R.id.editTextDateFin);
        btnAddClient = (Button) findViewById(R.id.btn_addClient);

        vehicule = (Vehicule) getIntent().getExtras().get(Constant.INTENT_VEHICULE);

        editTextVoiture.setText(vehicule.getLibelle());

        autoComplete = (AutoCompleteTextView) findViewById(R.id.editTextClient);


        generateListClients() ;

    }

    private void generateListClients()
    {
        //Vérifie si on est bien connecté à internet
        if(Network.isNetworkAvailable(LocationActivity.this))
        {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(LocationActivity.this);

            //Appel de notre api à laquelle on passe l'id de l'agence pour récupérer la liste des véhicules
            String url = Constant.URL_CLIENTS;

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Instanciation de l'objet GSON -> Conversion JSON - model
                            Gson gson = new Gson();

                            Type listType = new TypeToken<ArrayList<Client>>(){}.getType();

                            try {
                                clients.clear();
                                clients.addAll((ArrayList<Client>)gson.fromJson(response, listType));
                                generateTabClient() ;
                                adapter  = new ArrayAdapter<>(
                                        getBaseContext(),
                                        android.R.layout.simple_dropdown_item_1line,
                                        tabClients);

                                autoComplete.setAdapter(adapter);
                            } catch (JsonSyntaxException jse) {
                                jse.printStackTrace();
                            }

                            //Ecouteur sur les éléments de la liste
                            //listClients.setOnItemClickListener(ListClientsActivity.this);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    FastDialog.showDialog(LocationActivity.this,
                            FastDialog.SIMPLE_DIALOG,
                            "Un problème est survenu avec l'application! Veuillez nous en excuser.");
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        }else {
            FastDialog.showDialog(LocationActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté!");
        }
    }

    public void generateTabClient() {
        ArrayList<String> tempList = new ArrayList<>() ;
        for(Client client: clients) {
            tempList.add(client.getId() +" - "+ client.getNom() + " "+client.getPrenom());
        }
        tabClients =  tempList.toArray(new String[tempList.size()]);
    }
    public void pickadateDebut(View view) {
        // TODO Auto-generated method stub
        //To show current date in the datepicker
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear=mcurrentDate.get(Calendar.YEAR);
        int mMonth=mcurrentDate.get(Calendar.MONTH);
        int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(LocationActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                String dateSelect = String.valueOf(selectedyear) + "/" + String.valueOf(selectedmonth) + "/"+String.valueOf(selectedday) ;
                editTextDateDebut.setText(dateSelect);
            }
        },mYear, mMonth, mDay);

        mDatePicker.setTitle(getString(R.string.selectDate));
        mDatePicker.show();
    }


    public void pickadateFin(View view) {
        // TODO Auto-generated method stub
        //To show current date in the datepicker
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear=mcurrentDate.get(Calendar.YEAR);
        int mMonth=mcurrentDate.get(Calendar.MONTH);
        int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(LocationActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                String dateSelect = String.valueOf(selectedyear) + "/" + String.valueOf(selectedmonth) + "/"+String.valueOf(selectedday) ;
                editTextDateFin.setText(dateSelect);
            }
        },mYear, mMonth, mDay);

        mDatePicker.setTitle(getString(R.string.selectDate));
        mDatePicker.show();
    }

    public void addLocation(View view) {
        if(Network.isNetworkAvailable(LocationActivity.this))
        {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(LocationActivity.this);

            String url = Constant.ULR_ADD_LOCATION ;

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject json = new JSONObject(response) ;
                                if(json.getBoolean("ok")) {
                                    Intent intent = new Intent(LocationActivity.this, ListVehiculesActivity.class);
                                    intent.putExtra("ok", json.getString("message"));
                                    setResult(Activity.RESULT_OK, intent);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("Error.Response", error.toString());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    String client = editTextClient.getText().toString() ;
                    String idClient = client.split("-")[0] ;
                    params.put("vehicule", String.valueOf(vehicule.getId()));
                    params.put("client", idClient);
                    params.put("debutLocation", editTextDateDebut.getText().toString());
                    params.put("finLocation", editTextDateFin.getText().toString());

                    return params;
                }
            };
            // Add the request to the RequestQueue.
            queue.add(postRequest);

        }else {
            FastDialog.showDialog(LocationActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Vous devez être connecté!");
        }
    }
}
