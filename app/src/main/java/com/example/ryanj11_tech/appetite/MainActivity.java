package com.example.ryanj11_tech.appetite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    modPrefs loginPref, signoutPref;
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

//        try {
            loginPref = new modPrefs(MainActivity.this,"LoginPrefs");
            extraStr = extras.getString("Username");
            loginPref.putData("Username", extraStr);
            Toast.makeText(MainActivity.this,"The username currently logged in is " + loginPref.getNameFromPref("Username"),Toast.LENGTH_LONG).show();
//        }
//       catch (NullPointerException e)
//       {
//           Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
//           Intent cancel = new Intent(MainActivity.this, LogInActivity.class);
//           startActivity(cancel);
//       }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                signoutPref = new modPrefs(MainActivity.this,"LoginPrefs");
                String userData = signoutPref.getNameFromPref("Username");
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
//            case R.id.btnSignIn:
//                Intent signinIntent = new Intent(MainActivity.this, LogInActivity.class);
//                signinIntent.setAction(Intent.ACTION_VIEW);
//                startActivity(signinIntent);
//                break;
            case R.id.btnTipCalc:
                Intent tipcalIntent = new Intent(MainActivity.this, TipActivity.class);
                tipcalIntent.setAction(Intent.ACTION_VIEW);
                startActivity(tipcalIntent);
                break;
        }
    }

}
