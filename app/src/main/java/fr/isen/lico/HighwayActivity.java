package fr.isen.lico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HighwayActivity extends AppCompatActivity {

    private int nbJoueur, nbManche, manche, i;
    private List<String> player = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highway);

        /***** Element de la vue *****/
        final ImageView ivBack = findViewById(R.id.ivBack);
        final ImageView ivSetting = findViewById(R.id.ivSettings);
        final ImageView ivCard = findViewById(R.id.ivCard);
        final ImageView ivCard1 = findViewById(R.id.ivCard1);
        final ImageView ivCard2 = findViewById(R.id.ivCard2);
        final ImageView ivCard3 = findViewById(R.id.ivCard3);
        final ImageView ivCard4 = findViewById(R.id.ivCard4);
        final ImageView ivCard5 = findViewById(R.id.ivCard5);
        final ImageView ivCard6 = findViewById(R.id.ivCard6);
        final Button btMoins = findViewById(R.id.btMoins);
        final Button btPlus = findViewById(R.id.btPlus);
        final TextView tvPlayer = findViewById(R.id.tvPlayer);
        final TextView tvManche = findViewById(R.id.tvManche);

        /***** Récupération des données *****/
        Intent intent = getIntent();
        nbJoueur = intent.getIntExtra("nbJoueur", nbJoueur);
        player = getIntent().getStringArrayListExtra("player");

        /***** Redirection *****/
        ivBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HighwayActivity.this.finish();
            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighwayActivity.this, SettingActivity.class);
                intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                intent.putExtra("nbJoueur", nbJoueur);
                startActivity(intent);
            }
        });

        /***** Algo du jeu *****/
        /* Définition des variables */
        nbManche = 3;
        manche = 0;
        i = 0;
        tvPlayer.setText(player.get(i));
        tvManche.setText("Manche " + Integer.toString(manche));




    }
}
