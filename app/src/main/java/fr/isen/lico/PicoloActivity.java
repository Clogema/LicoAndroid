package fr.isen.lico;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PicoloActivity extends AppCompatActivity {

    private int nbJoueur;
    private List<String> player = new ArrayList<String>();
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picolo);

        /***** Element de la vue *****/
        final ImageView ivBack = findViewById(R.id.ivBack);
        final ImageView ivSetting = findViewById(R.id.ivSettings);

        /***** Récupération des données *****/
        Intent intent = getIntent();
        nbJoueur = intent.getIntExtra("nbJoueur", nbJoueur);
        player = getIntent().getStringArrayListExtra("player");

        /***** Redirection *****/
        ivBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PicoloActivity.this.finish();
            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PicoloActivity.this, SettingActivity.class);
                intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                intent.putExtra("nbJoueur", nbJoueur);
                startActivity(intent);
            }
        });

        /***** Firebase *****/
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    //String name = (String) messageSnapshot.child("Divers").getValue();
                    String message = (String) messageSnapshot.child("Dilemme").getValue();

                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast toast = Toast.makeText(getApplicationContext(), "Failed to read value", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}