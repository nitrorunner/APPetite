package com.example.ryanj11_tech.appetite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {


    EditText userText, passText, passText2, fText, lText, emailText, phoneText, streetText, cityText, stateText, zipText;
    String regUsername, regPassword, regFName, regLName, regEmail, regPhone, regStreet, regCity, regState, regZip, type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        userText = (EditText) findViewById(R.id.reg_Username);
        passText = (EditText) findViewById(R.id.reg_Password);
        passText2 = (EditText) findViewById(R.id.reg_Password2);
        fText = (EditText) findViewById(R.id.reg_FName);
        lText = (EditText) findViewById(R.id.reg_LName);
        emailText = (EditText) findViewById(R.id.reg_Email);
        phoneText = (EditText) findViewById(R.id.reg_Phone);
        streetText = (EditText) findViewById(R.id.reg_Street);
        cityText = (EditText) findViewById(R.id.reg_City);
        stateText = (EditText) findViewById(R.id.reg_State);
        zipText = (EditText) findViewById(R.id.reg_Zip);

    }

    public void OnRegister(View view) {

        if (userText.getText().toString().equals("")||
                passText.getText().toString().equals("")||
                fText.getText().toString().equals("")||
                lText.getText().toString().equals("")||
                emailText.getText().toString().equals("")||
                phoneText.getText().toString().equals("")||
                streetText.getText().toString().equals("")||
                cityText.getText().toString().equals("")||
                stateText.getText().toString().equals("")||
                zipText.getText().toString().equals(""))
        {
            Toast.makeText(CreateAccountActivity.this, "Please fill in all fields.", Toast.LENGTH_LONG).show();
        }
        else if (!passText.getText().toString().equals(passText2.getText().toString()))
        {
            Toast.makeText(CreateAccountActivity.this, "Passwords do not match.", Toast.LENGTH_LONG).show();
        }
        else
        {

            regUsername = userText.getText().toString();
            regPassword = passText.getText().toString();
            regFName = fText.getText().toString();
            regLName = lText.getText().toString();
            regEmail = emailText.getText().toString();
            regPhone = phoneText.getText().toString();
            regStreet = streetText.getText().toString();
            regCity = cityText.getText().toString();
            regState = stateText.getText().toString();
            regZip = zipText.getText().toString();
            type = "register";

            DatabaseConnection dbConnection = new DatabaseConnection(this);
            dbConnection.execute(type, regUsername, regPassword, regFName, regLName, regEmail, regPhone, regStreet, regCity, regState, regZip);
        }
    }
}
