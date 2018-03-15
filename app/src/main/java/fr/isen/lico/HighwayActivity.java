package fr.isen.lico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HighwayActivity extends AppCompatActivity {

    private int nbJoueur;
    private int nbManche, numCard;
    private int manche;
    private int i, id;
    private int valueCard;
    private List<String> player = new ArrayList<String>();
    private List<String> cardKey;
    private Map<String, Integer> cards;

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
        /* Initialisation */
        nbManche = 3;
        manche = 1;
        i = 0;
        numCard = 1;
        cards = new HashMap<>();
        cards.put("as_carreau", 1);
        cards.put("as_coeur", 1);
        cards.put("as_pique", 1);
        cards.put("as_trefle", 1);
        cards.put("deux_carreau", 2);
        cards.put("deux_coeur", 2);
        cards.put("deux_pique", 2);
        cards.put("deux_trefle", 2);
        cards.put("trois_carreau", 3);
        cards.put("trois_coeur", 3);
        cards.put("trois_pique", 3);
        cards.put("trois_trefle", 3);
        cards.put("quatre_carreau", 4);
        cards.put("quatre_coeur", 4);
        cards.put("quatre_pique", 4);
        cards.put("quatre_trefle", 4);
        cards.put("cinq_carreau", 5);
        cards.put("cinq_coeur", 5);
        cards.put("cinq_pique", 5);
        cards.put("cinq_trefle", 5);
        cards.put("six_carreau", 6);
        cards.put("six_coeur", 6);
        cards.put("six_pique", 6);
        cards.put("six_trefle", 6);
        cards.put("sept_carreau", 7);
        cards.put("sept_coeur", 7);
        cards.put("sept_pique", 7);
        cards.put("sept_trefle", 7);
        cards.put("huit_carreau", 8);
        cards.put("huit_coeur", 8);
        cards.put("huit_pique", 8);
        cards.put("huit_trefle", 8);
        cards.put("neuf_carreau", 9);
        cards.put("neuf_coeur", 9);
        cards.put("neuf_pique", 9);
        cards.put("neuf_trefle", 9);
        cards.put("dix_carreau", 10);
        cards.put("dix_coeur", 10);
        cards.put("dix_pique", 10);
        cards.put("dix_trefle", 10);
        cards.put("valet_carreau", 11);
        cards.put("valet_coeur", 11);
        cards.put("valet_pique", 11);
        cards.put("valet_trefle", 11);
        cards.put("dame_carreau", 12);
        cards.put("dame_coeur", 12);
        cards.put("dame_pique", 12);
        cards.put("dame_trefle", 12);
        cards.put("roi_carreau", 13);
        cards.put("roi_coeur", 13);
        cards.put("roi_pique", 13);
        cards.put("roi_trefle", 13);

        cardKey = new ArrayList<>(cards.keySet());

        tvPlayer.setText(player.get(i));
        tvManche.setText("Manche " + Integer.toString(manche));

        /* Première carte */
        ivCard1.setVisibility(View.INVISIBLE);
        id = getRandomCard();
        ivCard1.setImageResource(id);
        ivCard.setImageResource(id);

        /* Boutons */
        btPlus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                id = getRandomCard();
                ivCard.setImageResource(id);
                numCard++;
                gestionDeck(ivCard1, ivCard2, ivCard3, ivCard4, ivCard5, ivCard6);
            }
        });

        btMoins.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {ivCard.setImageResource(getRandomCard());
            }
        });
    }

    private int getRandomCard() {
        Random rand = new Random();
        String randomCard = cardKey.get(rand.nextInt(cardKey.size()));
        int imageID = getResources().getIdentifier(randomCard , "drawable", getPackageName());

        return imageID;
    }

    private int getValueCard(int id){
        int value = 0;
        String card = getResources().getResourceEntryName(id);

        value = cards.get(card);

        return value;
    }

    private void gestionDeck(ImageView iv1, ImageView iv2, ImageView iv3, ImageView iv4, ImageView iv5, ImageView iv6){
        switch (numCard) {
            case 2:
                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.INVISIBLE);
                iv2.setImageResource(id);
                break;
            case 3:
                iv2.setVisibility(View.VISIBLE);
                iv3.setVisibility(View.INVISIBLE);
                iv3.setImageResource(id);
                break;
            case 4:
                iv3.setVisibility(View.VISIBLE);
                iv4.setVisibility(View.INVISIBLE);
                iv4.setImageResource(id);
                break;
            case 5:
                iv4.setVisibility(View.VISIBLE);
                iv5.setVisibility(View.INVISIBLE);
                iv5.setImageResource(id);
                break;
            case 6:
                iv5.setVisibility(View.VISIBLE);
                iv6.setVisibility(View.INVISIBLE);
                break;
            default:
                Toast toast = Toast.makeText(getApplicationContext(), "Problème Switch", Toast.LENGTH_SHORT);
                toast.show();
        }
    }
}
