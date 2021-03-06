package com.example.ryanj11_tech.appetite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Checkin extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    PendingIntent mPendingIntent;
    modPrefs loginPref;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        final String pageId = "86552939404";//changes based on company
        final String twitterId = "gcfb?lang=en";


        Window window = Checkin.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        nfcAdapter = NfcAdapter.getDefaultAdapter(Checkin.this);
        if (nfcAdapter == null) {
            Toast.makeText(Checkin.this, "Check-in will not work on this device", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (!nfcAdapter.isEnabled()) {
            nfcDialog();
        } else {
            Toast.makeText(Checkin.this, "Wave over tag to check in", Toast.LENGTH_SHORT).show();

        }

        mPendingIntent = PendingIntent.getActivity(Checkin.this, 0, new Intent(Checkin.this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);


        findViewById(R.id.btnFB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + pageId));
                    startActivity(intent);
                } catch (Exception e) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + pageId));
                    startActivity(intent);
                }

            }
        });

        findViewById(R.id.btnTwit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitterId)));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + twitterId)));
                }
            }

        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcAdapter.enableForegroundDispatch(Checkin.this, mPendingIntent, null, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }


    public void handleIntent(Intent intent) {
        Tag mTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String str;
        int size;
        try {
            byte byteId[] = mTag.getId();
            size = byteId.length;
            // Convert the byte array to integer
            str = "";
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    byte myByte = byteId[i];
                    int myInt = (int) (myByte & 0xFF);
                    str += myInt;
                }
            } else {
                Toast.makeText(Checkin.this, "Check-in failed tag cannot be read", Toast.LENGTH_SHORT).show();
            }
            if (str.equals("13409726")) {
                Toast.makeText(Checkin.this, "Check-in Complete", Toast.LENGTH_SHORT).show();

                String type = "checkin";
                loginPref = new modPrefs(Checkin.this, "LoginPrefs");
                String username = loginPref.getNameFromPref("Username");
                DatabaseConnection databaseConnection = new DatabaseConnection(this);
                databaseConnection.execute(type, username);
            } else {
                Toast.makeText(Checkin.this, "Wrong tag detected", Toast.LENGTH_SHORT).show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Toast.makeText(Checkin.this,str,Toast.LENGTH_SHORT).show();  //get tag number
        //https://stackoverflow.com/questions/25610266/android-how-to-read-nfc-tag-with-current-app
    }

    public void nfcDialog() {
        alertDialog = new AlertDialog.Builder(Checkin.this).create();
        alertDialog.setMessage("NFC must be enabled in order to check in!");
        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Build.VERSION.SDK_INT >= 16) {
                    startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
                } else {
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                }
            }
        });
        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }


}
