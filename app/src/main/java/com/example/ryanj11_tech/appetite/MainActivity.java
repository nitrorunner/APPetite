package com.example.ryanj11_tech.appetite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String type;
    DatabaseConnection databaseConnection;
    modPrefs loginPref, signoutPref, ptsPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String extraStr;
        Bundle extras = getIntent().getExtras();
        modPrefs loginPref;
        findViewById(R.id.btnTakeMe).setOnClickListener(MainActivity.this);
        findViewById(R.id.btnCheckIn).setOnClickListener(MainActivity.this);
        findViewById(R.id.btnSignIn).setOnClickListener(MainActivity.this);
        findViewById(R.id.btnTipCalc).setOnClickListener(MainActivity.this);
        findViewById(R.id.btnPromo).setOnClickListener(MainActivity.this);
        findViewById(R.id.btnReviews).setOnClickListener(MainActivity.this);
        findViewById(R.id.btnMenu).setOnClickListener(MainActivity.this);

        type = "getUserPoints";
        modPrefs ptsPref = new modPrefs(MainActivity.this,"LoginPrefs");
        String userPts = ptsPref.getNameFromPref("Username");
        //Toast.makeText(MainActivity.this, "Username is " + userPts,Toast.LENGTH_LONG).show();
        databaseConnection = new DatabaseConnection(this);
        databaseConnection.execute(type, userPts);


        loginPref = new modPrefs(MainActivity.this,"LoginPrefs");

//        String test = loginPref.getNameFromPref("Username");
//        Toast.makeText(MainActivity.this, "Username is " + test,Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                signoutPref = new modPrefs(MainActivity.this,"LoginPrefs");
                String userData = signoutPref.getNameFromPref("Username");
                ptsPref = new modPrefs(MainActivity.this, "PointsPref");
                String userPoints = ptsPref.getNameFromPref("Points");
                ptsPref.delData("Points", userPoints);
                signoutPref.delData("LoginPrefs", userData);
                Intent sgnoIntent = new Intent (MainActivity.this, LogInActivity.class);
                startActivity(sgnoIntent);
                break;
            case R.id.btnTakeMe:
                Intent navIntent = new Intent(MainActivity.this, MapActivity.class);
                navIntent.setAction(Intent.ACTION_VIEW);
                startActivity(navIntent);
                break;
            case R.id.btnCheckIn:
                Intent checkinIntent = new Intent(MainActivity.this, Checkin.class);
                checkinIntent.setAction(Intent.ACTION_VIEW);
                startActivity(checkinIntent);
                break;
            case R.id.btnTipCalc:
                Intent tipcalIntent = new Intent(MainActivity.this, TipActivity.class);
                tipcalIntent.setAction(Intent.ACTION_VIEW);
                startActivity(tipcalIntent);
                break;
            case R.id.btnPromo:
                Intent promoIntent = new Intent(MainActivity.this, PromotionsActivity.class);
                promoIntent.setAction(Intent.ACTION_VIEW);
                startActivity(promoIntent);
                break;
            case R.id.btnReviews:
                Intent reviewIntent = new Intent(MainActivity.this, ReviewActivity.class);
                reviewIntent.setAction(Intent.ACTION_VIEW);
                startActivity(reviewIntent);
                break;
            case R.id.btnMenu:
                Intent menuIntent = new Intent (MainActivity.this, MenuActivity.class);
                menuIntent.setAction(Intent.ACTION_VIEW);
                startActivity(menuIntent);
                break;
        }
    }
}
