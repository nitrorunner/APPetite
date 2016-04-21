package com.example.ryanj11_tech.appetite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    modPrefs loginPref;
    EditText userText, passText;
    Button btnLgn, btnSgnUp;
    String username, password, type, username1;
    Intent intent = getIntent();
    String extraStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginPref = new modPrefs(LogInActivity.this, "LoginPrefs");

        userText = (EditText) findViewById(R.id.user_name);
        passText = (EditText) findViewById(R.id.user_pass);
        btnLgn = (Button) findViewById(R.id.btnLogIn);
        btnSgnUp = (Button) findViewById(R.id.btnSignUp);

//        try{
//            extraStr=getIntent().getExtras().getString("Username");
//        }
//        catch (NullPointerException e){
//            extraStr="Not Found";
//            Toast.makeText(LogInActivity.this,extraStr,Toast.LENGTH_SHORT).show();
//        }

//        String loggedUsername = loginPref.getNameFromPref("Username");
//
//        if (loggedUsername.equals(null)) {
//            //do nothing
//        } else {
//            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
//            startActivity(intent);

//        if(intent.hasExtra("Username")){
//            bd = getIntent().getExtras();
//            if(!bd.getString("")).equals(null){
//                username2 = bd.getString("Username");
//                loginPref.putData("Username",username2);
//            }
//        }

            btnSgnUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent loginIntent = new Intent(LogInActivity.this, CreateAccountActivity.class);
                    loginIntent.setAction(Intent.ACTION_VIEW);
                    startActivity(loginIntent);


                }
            });
        }

    public void OnLogin (View view)
    {
        username = userText.getText().toString();
        password = passText.getText().toString();
        type = "login";

        DatabaseConnection dbConnection = new DatabaseConnection(this);
        dbConnection.execute(type, username, password);
    }

    /**
     * onStart() method will check whether or not the user is logged in after closing the app. If the user
     * logged in, a SharedPreference key based on their username is created. If it exists, the use is automatically redirected to
     * the main activity. If not, nothing is done.
     */
    @Override
    protected void onStart() {
        super.onStart();

        modPrefs checkPref = new modPrefs(LogInActivity.this,"LoginPrefs");
        username1 = checkPref.getNameFromPref("Username");

//For testing purposes. Displays what value is stored in the SharedPreferences folder.
//Toast.makeText(LogInActivity.this,"Username is " + username1,Toast.LENGTH_LONG).show();


        if(username1.equals("Not Found"))
        {
            //do nothing
        }
        else{
            Intent intent = new Intent (LogInActivity.this, MainActivity.class);
            intent.putExtra("Username",username1);
            startActivity(intent);

        }
    }
}
