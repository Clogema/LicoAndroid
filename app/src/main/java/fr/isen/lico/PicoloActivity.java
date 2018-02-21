package fr.isen.lico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PicoloActivity extends AppCompatActivity {
    public int numberOfLines = 3;

    private LinearLayout mEditTextContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picolo);
        mEditTextContainer = (LinearLayout)findViewById(R.id.linearLayoutDecisions);

        final ImageView ivBack = findViewById(R.id.ivBack);
        final ImageView ivAdd = findViewById(R.id.ivAdd);

        ivBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PicoloActivity.this, menuLicoActivity.class);
                startActivity(intent);
            }
        });


        final Button Add_button = (Button) findViewById(R.id.add_button);
        Add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Line();
            }
        });

        final Button remove_button = (Button) findViewById(R.id.remove_button);
        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLine();
            }
        });
    }

    public void Add_Line() {
        EditText et = new EditText(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(p);
        et.setHint("Enter Answer #" + (mEditTextContainer.getChildCount()+1));
        mEditTextContainer.addView(et);
    }

    public void removeLine() {
        mEditTextContainer.removeViewAt(mEditTextContainer.getChildCount()-1);
    }
}