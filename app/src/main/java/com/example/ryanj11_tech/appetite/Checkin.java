package com.example.ryanj11_tech.appetite;

import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Checkin extends AppCompatActivity {

    NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        Button FB_btn=(Button)findViewById(R.id.btnFB);
        final String pageId = "86552939404";//changes based on company
        final String twitterId="gcfb";

        Button TW_btn=(Button)findViewById(R.id.btnTwit);


        FB_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("fb://page/" + pageId));
                    startActivity(intent);
                } catch (Exception e) {
                    Intent intent =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + pageId));
                    startActivity(intent);
                }

            }
        });

        TW_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitterId)));
                }catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + twitterId)));
                }
            }

        });



    }

    public void checkClick(View view) {
        if((nfcAdapter !=null)&&(nfcAdapter.isEnabled()))
        {
            Toast.makeText(Checkin.this,"NFC enabled :)",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Checkin.this,"NFC needs to be enabled",Toast.LENGTH_SHORT).show();
        }

    }
}
