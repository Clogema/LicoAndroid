package fr.isen.lico;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private int nbManche, numCard, numManche;
    private int i, id;
    private int oldValueCard, newValueCard;
    private List<String> player = new ArrayList<String>();
    private List<String> usedCard = new ArrayList<String>();
    private List<String> cardKey;
    private Map<String, Integer> cards;
    private ImageView ivBack, ivSetting, ivCard, ivCard1, ivCard2, ivCard3, ivCard4, ivCard5, ivCard6;
    private Button btMoins, btPlus;
    private TextView tvPlayer, tvManche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highway);

        /***** Element de la vue *****/
        ivBack = findViewById(R.id.ivBack);
        ivSetting = findViewById(R.id.ivSettings);
        ivCard = findViewById(R.id.ivCard);
        ivCard1 = findViewById(R.id.ivCard1);
        ivCard2 = findViewById(R.id.ivCard2);
        ivCard3 = findViewById(R.id.ivCard3);
        ivCard4 = findViewById(R.id.ivCard4);
        ivCard5 = findViewById(R.id.ivCard5);
        ivCard6 = findViewById(R.id.ivCard6);
        btMoins = findViewById(R.id.btMoins);
        btPlus = findViewById(R.id.btPlus);
        tvPlayer = findViewById(R.id.tvPlayer);
        tvManche = findViewById(R.id.tvManche);

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
        numManche = 0;
        i = 0;

        initCards();
        cardKey = new ArrayList<>(cards.keySet());
        nextCard();
        newManche();
        oldValueCard = getValueCard(id);

        tvPlayer.setText(player.get(i));
        tvManche.setText("Manche " + Integer.toString(numManche));

        btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newValueCard = nextCard();
                printCard();

                if (newValueCard > oldValueCard){
                    if(numCard == 6) {
                        i++;
                        alerteGagne(player.get(i-1));
                        if (i >= nbJoueur) {
                            alerteFinJeu();
                        }
                    }
                    oldValueCard = newValueCard;
                }
                else {
                    if(numManche >= nbManche)  {
                        i++;
                        alertePerduPlayer();
                    }
                    else {
                        alertePerdu(7-numCard);
                    }
                }
            }
        });

        btMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newValueCard = nextCard();
                printCard();

                if (newValueCard < oldValueCard){
                    if(numCard == 6) {
                        i++;
                        alerteGagne(player.get(i-1));
                        if (i >= nbJoueur) {
                            alerteFinJeu();
                        }
                    }
                    oldValueCard = newValueCard;
                }
                else {
                    if(numManche >= nbManche)  {
                        i++;
                        alertePerduPlayer();
                    }
                    else {
                        alertePerdu(7-numCard);
                    }
                }

            }
        });
    }

    private void newManche()
    {
        usedCard.clear();
        numCard = 1;
        numManche++;
        printCard();
        tvManche.setText("Manche " + Integer.toString(numManche));
    }

    private int nextCard()
    {
        int value;

        numCard++;
        this.id = getRandomCard();
        value = getValueCard(this.id);
        ivCard.setImageResource(this.id);

        return value;
    }

    private void nextPlayer() {
        numManche = 0;
        alerteNextPlayer(player.get(i));
        tvPlayer.setText(player.get(i));
    }

    private int getRandomCard() {
        int imageID;
        Random rand;
        String randomCard;

        do {
            rand = new Random();
            randomCard = cardKey.get(rand.nextInt(cardKey.size()));
            imageID = getResources().getIdentifier(randomCard, "drawable", getPackageName());
        } while (testID(usedCard, randomCard) == 1);

        usedCard.add(randomCard);

        return imageID;
    }

    private int getValueCard(int id){
        int value = 0;
        String card = getResources().getResourceEntryName(id);

        value = getValueCard(card);

        return value;
    }

    private int getValueCard(String id){
        int value = 0;

        value = this.cards.get(id);

        return value;
    }

    private void printCard(){
        switch(this.numCard) {
            case 1:
                this.ivCard1.setVisibility(View.INVISIBLE);
                this.id = getRandomCard();
                oldValueCard = getValueCard(this.id);
                this.ivCard1.setImageResource(this.id);
                this.ivCard.setImageResource(this.id);
                this.ivCard2.setImageResource(R.drawable.back);
                this.ivCard3.setImageResource(R.drawable.back);
                this.ivCard4.setImageResource(R.drawable.back);
                this.ivCard5.setImageResource(R.drawable.back);
                this.ivCard6.setImageResource(R.drawable.back);
                this.ivCard2.setVisibility(View.VISIBLE);
                this.ivCard3.setVisibility(View.VISIBLE);
                this.ivCard4.setVisibility(View.VISIBLE);
                this.ivCard5.setVisibility(View.VISIBLE);
                this.ivCard6.setVisibility(View.VISIBLE);
                break;
            case 2:
                this.ivCard1.setVisibility(View.VISIBLE);
                this.ivCard2.setVisibility(View.INVISIBLE);
                this.ivCard2.setImageResource(this.id);
                break;
            case 3:
                this.ivCard2.setVisibility(View.VISIBLE);
                this.ivCard3.setVisibility(View.INVISIBLE);
                this.ivCard3.setImageResource(this.id);
                break;
            case 4:
                this.ivCard3.setVisibility(View.VISIBLE);
                this.ivCard4.setVisibility(View.INVISIBLE);
                this.ivCard4.setImageResource(this.id);
                break;
            case 5:
                this.ivCard4.setVisibility(View.VISIBLE);
                this.ivCard5.setVisibility(View.INVISIBLE);
                this.ivCard5.setImageResource(this.id);
                break;
            case 6:
                this.ivCard5.setVisibility(View.VISIBLE);
                this.ivCard6.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private int testID(List<String> list, String id)
    {
        int test = 0;
        for(int i = 0 ; i < list.size() ; i++)
        {
            if(list.get(i).equals(id))
            {
                test = 1;
            }
        }
        return test;
    }

    private void alertePerdu(int gorgees)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HighwayActivity.this);
        alertDialogBuilder.setTitle("PERDU");
        alertDialogBuilder.setMessage("Perdu ! Bois " + gorgees + " gorgées.");
        alertDialogBuilder.setPositiveButton("OK..", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newManche();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void alerteGagne(String player)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HighwayActivity.this);
        alertDialogBuilder.setTitle("Félicitations " + player + " !!");
        alertDialogBuilder.setMessage("Tu peux distribuer 6 gorgées.");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nextPlayer();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void alerteNextPlayer(String player)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HighwayActivity.this);
        alertDialogBuilder.setTitle("Changement de joueur");
        alertDialogBuilder.setMessage(player + " à toi de jouer");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newManche();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void alertePerduPlayer()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HighwayActivity.this);
        alertDialogBuilder.setTitle("GAME OVER");
        alertDialogBuilder.setMessage("Perdu ! Tu es nul. Cul sec.");
        alertDialogBuilder.setPositiveButton("OK..", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (i >= nbJoueur)
                    alerteFinJeu();
                else
                    nextPlayer();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void alerteFinJeu() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HighwayActivity.this);
        alertDialogBuilder.setTitle("FIN DU JEU");
        alertDialogBuilder.setMessage("Le jeu est terminé ! Pour fêter ça tout le monde boit.");
        alertDialogBuilder.setPositiveButton("OUAAAAIS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HighwayActivity.this.finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void initCards()
    {
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
    }
}
