package fr.isen.lico;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
    }
}
