package com.example.ryanj11_tech.appetite;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NavActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);



        Button btnNav =(Button) findViewById(R.id.btnNavTo);
        btnNav.setOnClickListener(this);

    }

    public void onClick(View v){
        Intent geoIntent=new Intent();
        geoIntent.setAction(Intent.ACTION_VIEW);
        geoIntent.setData(Uri.parse("geo:0,0?q=Granite+City+Brewery+Lyndhurst+Ohio"));

        startActivity(geoIntent);
    }


}
