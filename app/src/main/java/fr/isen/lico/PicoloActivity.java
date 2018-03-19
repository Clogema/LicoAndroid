package fr.isen.lico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class PicoloActivity extends AppCompatActivity {

    private int nbJoueur;
    private List<String> player = new ArrayList<String>();

    private Picolo picolo;
    private String defi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picolo);

        /***** Element de la vue *****/
        final ImageView ivBack = findViewById(R.id.ivBack);
        final ImageView ivSetting = findViewById(R.id.ivSettings);
        final TextView tvTheme = findViewById(R.id.tvTheme);
        final TextView tvDefi = findViewById(R.id.tvDefi);

        /***** Récupération des données *****/
        Intent intent = getIntent();
        nbJoueur = intent.getIntExtra("nbJoueur", nbJoueur);
        player = getIntent().getStringArrayListExtra("player");

        /***** Redirection *****/
        ivBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PicoloActivity.this.finish();
            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PicoloActivity.this, SettingActivity.class);
                intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                intent.putExtra("nbJoueur", nbJoueur);
                startActivity(intent);
            }
        });

        /***** Récupérer BDD *****/
        new HttpHandler(new CallBackInterface() {
            @Override
            public void success(String json) {
                Gson gson = new GsonBuilder().create();
                picolo = gson.fromJson(json, Picolo.class);
            }

            @Override
            public void error() {
                tvDefi.setText("error");
            }
        }).execute("");

        /***** Afficher Défi *****/
        tvDefi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                defi = getDefi();
                tvDefi.setText(defi);
            }
        });

    }

    private String getDefi() {
        return picolo.getRandomDivers();
    }
}