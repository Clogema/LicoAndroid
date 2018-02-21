package fr.isen.lico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {

    private int nbJoueur;
    private List<String> player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        player = new ArrayList<String>();

        Intent intent = getIntent();
        nbJoueur = intent.getIntExtra("nbJoueur", nbJoueur);
        player.clear();
        player = getIntent().getStringArrayListExtra("player");

        final TextView listejoueur = findViewById(R.id.textView2);
        listejoueur.setText(String.valueOf(nbJoueur) + player.toString());

    }
}
