package fr.isen.lico;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrankPhoneActivity extends AppCompatActivity {

    private int nbJoueur;
    private List<String> player = new ArrayList<String>();
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 30;
    private static final int PERMISSION_REQUEST_CALL_PHONE = 10;

    private TextView tvContact, tvPlayer, tvNumber;

    private String nom, numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prank_phone);

        /***** Element de la vue *****/
        final ImageView ivBack = findViewById(R.id.ivBack);
        final ImageView ivSetting = findViewById(R.id.ivSettings);
        tvPlayer = findViewById(R.id.tvPlayer);
        tvContact = findViewById(R.id.tvContact);
        tvNumber = findViewById(R.id.tvNumber);
        final Button btCall = findViewById(R.id.btCall);
        final Button btDrink = findViewById(R.id.btDrink);

        /***** Récupération des données *****/
        Intent intent = getIntent();
        nbJoueur = intent.getIntExtra("nbJoueur", nbJoueur);
        player = getIntent().getStringArrayListExtra("player");

        /***** Redirection *****/
        ivBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PrankPhoneActivity.this.finish();
            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrankPhoneActivity.this, SettingActivity.class);
                intent.putStringArrayListExtra("player", (ArrayList<String>) player);
                intent.putExtra("nbJoueur", nbJoueur);
                startActivity(intent);
            }
        });

        tvPlayer.setText(setPlayer());
        checkPermissionContact();


        btDrink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PrankPhoneActivity.this);
                alertDialogBuilder.setTitle("Je bois");
                alertDialogBuilder.setMessage("Veux tu vraiment passer ce défi ? Dans ce cas, bois 5 gorgées.");
                alertDialogBuilder.setPositiveButton("SANTE !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        tvPlayer.setText(setPlayer());
                        checkPermissionContact();
                    }
                });
                alertDialogBuilder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        btCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PrankPhoneActivity.this);
                alertDialogBuilder.setTitle("Appeler");
                alertDialogBuilder.setMessage("Veux tu vraiment appeler cette personne ?");
                alertDialogBuilder.setPositiveButton("OUI J'APPELLE !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        checkPermissionCall();
                        tvPlayer.setText(setPlayer());
                        checkPermissionContact();
                    }
                });
                alertDialogBuilder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void getContact() {
        final ArrayList<String> list = new ArrayList<>();
        String contact;

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (phones != null && phones.getCount() > 0) {
            while (phones.moveToNext()) {
                String nom = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String numero = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                list.add(nom + "/" + numero);
            }
            phones.close();
        }

        contact = list.get((int) (Math.random() * list.size()));
        String[] parts = contact.split("/");
        nom = parts[0];
        numero = parts[1];

        tvContact.setText(nom);
        tvNumber.setText(numero);
    }

    private void phoneCall() {
        Intent call = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", "#31#" + numero, null));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(call);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermissionContact();
                } else {
                    checkPermissionContact();
                }
                return;
            }
            case PERMISSION_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermissionCall();
                } else {
                    checkPermissionCall();
                }
                return;
            }
        }
    }

    private void checkPermissionContact()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_READ_CONTACTS);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_READ_CONTACTS);
            }
        } else {
            getContact();
        }
    }

    private void checkPermissionCall()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CALL_PHONE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CALL_PHONE);
            }
        } else {
            phoneCall();
        }
    }

    private String setPlayer() {
        return player.get((int)(Math.random() * nbJoueur));
    }
}
