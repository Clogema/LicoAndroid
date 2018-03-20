package fr.isen.lico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MenuLicoActivity extends AppCompatActivity {

    private int nbJoueur;
    private List<String> player = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lico);

        /***** Element de la vue *****/
        final ImageView ivPicolo = findViewById(R.id.ivPicolo);
        final ImageView ivHighway = findViewById(R.id.ivHighway);
        final ImageView ivBetsum = findViewById(R.id.ivBetsum);
        final ImageView ivPhone = findViewById(R.id.ivPhone);
        final ImageView ivBack = findViewById(R.id.ivBack);
        final ImageView ivSetting = findViewById(R.id.ivSettings);

        /***** Récupération des données *****/
        Intent intent = getIntent();
        nbJoueur = intent.getIntExtra("nbJoueur", nbJoueur);
        player = getIntent().getStringArrayListExtra("player");

        /***** Redirection *****/
        /** PICOLO */
        ivPicolo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MenuLicoActivity.this);
                alertDialogBuilder.setTitle(R.string.picolo);
                alertDialogBuilder.setMessage(R.string.descPicolo);
                alertDialogBuilder.setPositiveButton("Jouer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        Intent intent = new Intent(MenuLicoActivity.this, PicoloActivity.class);
                        intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                        intent.putExtra("nbJoueur", nbJoueur);
                        startActivity(intent);
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        /** HIGHWAY */
        ivHighway.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MenuLicoActivity.this);
                alertDialogBuilder.setTitle(R.string.highway);
                alertDialogBuilder.setMessage(R.string.descHighway);
                alertDialogBuilder.setPositiveButton("Jouer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        Intent intent = new Intent(MenuLicoActivity.this, HighwayActivity.class);
                        intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                        intent.putExtra("nbJoueur", nbJoueur);
                        startActivity(intent);
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        /** BETSUM */
        ivBetsum.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MenuLicoActivity.this);
                alertDialogBuilder.setTitle(R.string.sum);
                alertDialogBuilder.setMessage(R.string.descBetSum);
                alertDialogBuilder.setPositiveButton("Jouer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        Intent intent = new Intent(MenuLicoActivity.this, BetSumActivity.class);
                        intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                        intent.putExtra("nbJoueur", nbJoueur);
                        startActivity(intent);
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        /** SELFDRAW */
        ivPhone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MenuLicoActivity.this);
                alertDialogBuilder.setTitle(R.string.phone);
                alertDialogBuilder.setMessage(R.string.descDrawSelf);
                alertDialogBuilder.setPositiveButton("Jouer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        Intent intent = new Intent(MenuLicoActivity.this, PrankPhoneActivity.class);
                        intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                        intent.putExtra("nbJoueur", nbJoueur);
                        startActivity(intent);
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MenuLicoActivity.this.finish();
            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuLicoActivity.this, SettingActivity.class);
                intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                intent.putExtra("nbJoueur", nbJoueur);
                startActivity(intent);
            }
        });
    }
}
