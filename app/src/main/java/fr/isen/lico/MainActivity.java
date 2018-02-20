package fr.isen.lico;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvTitle = (TextView) findViewById(R.id.title);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gomarice_gogono_cocoa_mochi.ttf");
        tvTitle.setTypeface(typeface);

        final Button btSubmit = findViewById(R.id.submit);
        final ImageView ivSetting = findViewById(R.id.ivSettings);
        final EditText etPlayer1 = findViewById(R.id.player1);
        final EditText etPlayer2 = findViewById(R.id.player2);

        btSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String player1 = etPlayer1.getText().toString();
                String player2 = etPlayer2.getText().toString();
                Toast toast = Toast.makeText(getApplicationContext(), player1 + player2, Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(MainActivity.this, menuLicoActivity.class);
                intent.putExtra("player1", player1);
                intent.putExtra("player2", player2);
                startActivity(intent);
            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
