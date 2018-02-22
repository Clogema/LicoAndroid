package fr.isen.lico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BetSumActivity extends AppCompatActivity {

    private int nbJoueur;
    private List<String> player = new ArrayList<String>();
    private int number, somme, i;
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    private TextView tvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_sum);

        /***** Element de la vue *****/
        final ImageView ivBack = findViewById(R.id.ivBack);
        final ImageView ivSetting = findViewById(R.id.ivSettings);

        final TextView tvPlayer = findViewById(R.id.tvPlayer);
        tvNumber = findViewById(R.id.tvNumber);

        /***** Récupération des données *****/
        Intent intent = getIntent();
        nbJoueur = intent.getIntExtra("nbJoueur", nbJoueur);
        player = getIntent().getStringArrayListExtra("player");

        /***** Algo du jeu *****/
        somme = 0;
        i = 0;
        number = (int)(Math.random() * 10);
        tvNumber.setText(Integer.toString(number));
        tvPlayer.setText(player.get(i));

        tvNumber.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BetSumActivity.this);
                alertDialogBuilder.setTitle("Numéro choisi");
                alertDialogBuilder.setMessage("Voulez vous vraiment choisir le numéro " + Integer.toString(number) + " ?");
                alertDialogBuilder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        i = i + 1;
                        somme = somme + number;
                        if (i >= nbJoueur) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BetSumActivity.this);
                            alertDialogBuilder.setTitle("FIN DU JEU");
                            alertDialogBuilder.setMessage("Faites vos paris !");
                            alertDialogBuilder.setPositiveButton("Afficher Somme", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int arg1) {
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BetSumActivity.this);
                                    alertDialogBuilder.setTitle("Résultats");
                                    alertDialogBuilder.setMessage("Somme : " + Integer.toString(somme));
                                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int arg1) {
                                            BetSumActivity.this.finish();
                                        }
                                    });

                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                }
                            });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        } else {
                            number = (int)(Math.random() * 10);
                            tvNumber.setText(Integer.toString(number));
                            tvPlayer.setText(player.get(i));
                        }
                    }
                });
                alertDialogBuilder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        /***** Redirection *****/
        ivBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                BetSumActivity.this.finish();
            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BetSumActivity.this, SettingActivity.class);
                intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                intent.putExtra("nbJoueur", nbJoueur);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        number = (int)((Math.random() * 11) - 1);
                        tvNumber.setText(Integer.toString(number));
                    }

                    // Right to left swipe action
                    else
                    {
                        number = (int)((Math.random() * 11) - 1);
                        tvNumber.setText(Integer.toString(number));
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
