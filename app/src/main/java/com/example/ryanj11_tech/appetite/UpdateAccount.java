package com.example.ryanj11_tech.appetite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class UpdateAccount extends AppCompatActivity {

    EditText userText, passText, emailText, fText, lText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
    }
}
