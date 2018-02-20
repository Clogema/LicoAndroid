package fr.isen.lico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Intent intent = getIntent();
        final String player1 = intent.getStringExtra("player1");
        final String player2 = intent.getStringExtra("player2");

        final TextView listejoueur = findViewById(R.id.textView2);
        listejoueur.setText(player1 + " et " + player2);

    }
}
