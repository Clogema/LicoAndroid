package fr.isen.lico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

        final ListView mListView = (ListView) findViewById(R.id.lvJoueur);

        player = new ArrayList<String>();

        Intent intent = getIntent();
        nbJoueur = intent.getIntExtra("nbJoueur", nbJoueur);
        player = getIntent().getStringArrayListExtra("player");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SettingActivity.this,
                android.R.layout.simple_list_item_1, player);
        mListView.setAdapter(adapter);

    }
}
