package com.example.ryanj11_tech.appetite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTakeMe = (Button)findViewById(R.id.btnTakeMe);
        btnTakeMe.setOnClickListener(this);

        /**
        Button btnCheckin = (Button)findViewById(R.id.btnCheckIn);
        btnCheckin.setOnClickListener(this);
         **/

        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTakeMe:
                Intent navIntent = new Intent(this, NavActivity.class);
                navIntent.setAction(Intent.ACTION_VIEW);
                startActivity(navIntent);
            break;
            case R.id.btnSignIn:
                Intent signinIntent = new Intent (this, LogInActivity.class);
                signinIntent.setAction(Intent.ACTION_VIEW);
                startActivity(signinIntent);
            break;

        }
    }

}
