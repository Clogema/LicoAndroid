package fr.isen.lico;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mEditTextContainer;
    private int nbJoueur;
    private List<EditText> editTextList = new ArrayList<EditText>();
    private List<String> player = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***** Titre *****/
        TextView tvTitle = (TextView) findViewById(R.id.title);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gomarice_gogono_cocoa_mochi.ttf");
        tvTitle.setTypeface(typeface);

        /***** Element de la vue *****/
        mEditTextContainer = (LinearLayout)findViewById(R.id.linearLayoutDecisions);

        final EditText et1 = findViewById(R.id.editText1);
        editTextList.add(et1);
        final EditText et2 = findViewById(R.id.editText2);
        editTextList.add(et2);
        final EditText et3 = findViewById(R.id.editText3);
        editTextList.add(et3);

        final Button btSubmit = findViewById(R.id.submit);
        final ImageView ivSetting = findViewById(R.id.ivSettings);
        final ImageView ivAdd = findViewById(R.id.ivAdd);
        final ImageView ivSup = findViewById(R.id.ivSup);

        /***** Listeners *****/
        // Ajouter un user
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLine();
            }
        });

        // Supprimer un user
        ivSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supLine();
            }
        });

        // Valider les joueurs et passer à la sélection du jeu
        btSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getPlayer();
                if (nbJoueur >= 2 && nbJoueur <= 10) {
                    Intent intent = new Intent(MainActivity.this, MenuLicoActivity.class);
                    intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                    intent.putExtra("nbJoueur", nbJoueur);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Vous êtes pas assez", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        // Ouvrir la page de paramètre
        ivSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getPlayer();
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                intent.putExtra("nbJoueur", nbJoueur);
                startActivity(intent);
            }
        });
    }

    /***** Ajouter un editText *****/
    public void addLine() {
        nbJoueur = mEditTextContainer.getChildCount();

        if (nbJoueur < 10) {
            EditText et = new EditText(this);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(p);
            et.setHint("Joueur " + (nbJoueur + 1));
            editTextList.add(et);
            mEditTextContainer.addView(et);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "T'as trop d'amis", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /***** Supprimer un editText *****/
    public void supLine() {
        nbJoueur = mEditTextContainer.getChildCount();

        if (nbJoueur > 2) {
            editTextList.remove(editTextList.size()-1);
            mEditTextContainer.removeViewAt(nbJoueur - 1);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Trouve des amis", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /***** Récupérer la liste des joueurs *****/
    public void getPlayer() {
        nbJoueur = 0;
        player.clear();
        for (EditText editText : editTextList) {
            String text = editText.getText().toString();
            System.out.println("Text = " + text);
            if (!text.equals("")) {
                player.add(text);
                nbJoueur++;
            }
        }
    }
}