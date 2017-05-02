package fr.lokacar.locationvoiture.ui.accueil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import fr.lokacar.locationvoiture.R;
import fr.lokacar.locationvoiture.ui.connexion.ConnectionActivity;

public class MainActivity extends AppCompatActivity {

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mise en place d'un timer pour afficher notre écran pendant un cours laps de temps
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                //TODO: lancer activity Home
                Intent intent = new Intent(MainActivity.this, ConnectionActivity.class); //.class -> compilation du .java
                startActivity(intent);
                //finish(); //On termine notre activité -> OnDestroy() de notre activité
            }
        }, 2000);
    }
}
