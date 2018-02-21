package fr.isen.lico;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {

    private int nbJoueur;
    private List<String> player = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        /***** Element de la vue *****/
        final ListView mListView = (ListView) findViewById(R.id.lvJoueur);

        final ImageView ivBack = findViewById(R.id.ivBack);

        final TextView tvNbjoueur = findViewById(R.id.tvNbjoueur);

        final Switch sUseless = (Switch) findViewById(R.id.sUseless);

        /***** Récupération des données *****/
        Intent intent = getIntent();
        nbJoueur = intent.getIntExtra("nbJoueur", nbJoueur);
        tvNbjoueur.setText(String.valueOf(nbJoueur));
        player = getIntent().getStringArrayListExtra("player");

        /***** Affichage des données dans la listView *****/
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SettingActivity.this, R.layout.item_list_view, player);
        mListView.setAdapter(adapter);

        /***** Retour *****/
        ivBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });

        /***** Test Bouton Inutile *****/
        sUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sUseless.isChecked()) {
                    android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(SettingActivity.this);
                    alertDialogBuilder.setTitle("Félicitation !");
                    alertDialogBuilder.setMessage("Bravo pour ta découverte, tu peux boire 2 gorgées !");
                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int arg1) {
                            dialog.cancel();
                            sUseless.setChecked(false);
                        }
                    });

                    android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });
    }
}
