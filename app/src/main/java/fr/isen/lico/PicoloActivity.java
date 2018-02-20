package fr.isen.lico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class PicoloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picolo);

        final ImageView ivBack = findViewById(R.id.ivBack);
        final ImageView ivAdd = findViewById(R.id.ivAdd);
        final EditText etPlayer4 = findViewById(R.id.player4);

        final ListView lv = findViewById(R.id.listview);
        String[] array = {"Player 1", "Player 2", "Player 3"};
        final ArrayList<String> lat = new ArrayList<String>(Arrays.asList(array));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lat);

        lv.setAdapter(adapter);

        ivBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PicoloActivity.this, menuLicoActivity.class);
                startActivity(intent);
            }
        });

        ivAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nomJoueur = etPlayer4.getText().toString();
                lat.add(nomJoueur);
                lv.setAdapter(adapter);
            }
        });
    }
}
