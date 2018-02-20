package fr.isen.lico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class menuLicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lico);

        final ImageView ivPicolo = findViewById(R.id.ivPicolo);
        final ImageView ivBack = findViewById(R.id.ivBack);
        final ImageView ivSetting = findViewById(R.id.ivSettings);

        Intent intent = getIntent();
        final String player1 = intent.getStringExtra("player1");
        final String player2 = intent.getStringExtra("player2");

        ivPicolo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuLicoActivity.this, PicoloActivity.class);
                startActivity(intent);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuLicoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuLicoActivity.this, SettingActivity.class);
                intent.putExtra("player1", player1);
                intent.putExtra("player2", player2);
                startActivity(intent);
            }
        });
    }
}
