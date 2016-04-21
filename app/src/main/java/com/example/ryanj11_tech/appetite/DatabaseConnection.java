package com.example.ryanj11_tech.appetite;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Jace on 4/13/2016.
 */
//first parameter is input, second parameter is progress, third is return value
public class DatabaseConnection extends AsyncTask<String, Void, String> {


    Context context;
    AlertDialog alertDialog;
    String login_URL = "http://menesesj2.leto.feralhosting.com/APPetite/logIn.php";
    String register_URL = "http://menesesj2.leto.feralhosting.com/APPetite/register.php";
    DatabaseConnection (Context ctx){
        context = ctx;
    }

    String result="";
    String line="";
    String username = "test";

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        if (type.equals("login")) {
            username = params[1];
            String password = params[2];
            try {
                URL url = new URL(login_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                ;
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("register"))
        {
            String regUsername = params[1];
            String regPassword = params[2];
            String regFName = params[3];
            String regLName = params[4];
            String regEmail = params[5];
            try {
                URL url = new URL(register_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(regUsername, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(regPassword, "UTF-8")+"&"+
                        URLEncoder.encode("fName", "UTF-8")+"="+URLEncoder.encode(regFName, "UTF-8")+"&"+
                        URLEncoder.encode("lName", "UTF-8")+"="+URLEncoder.encode(regLName, "UTF-8")+"&"+
                        URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(regEmail, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                while ((line = bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();;
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    /**
     * onPostExecute determines the action to be performed dependending on the string retrieved from the php file
     * @param result returns the string a selected php file displays. Depending on the value, it will perform specified actions
     */
    @Override
    protected void onPostExecute(String result) {

        if (result.equals("Login Success"))
        {
            alertDialog.setMessage(result);
            alertDialog.show();
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Intent successIntent = new Intent(context, MainActivity.class);
                    successIntent.putExtra("Username",username);
                    context.startActivity(successIntent);
                }
            });
        }
        else if (result.equals("Account Created."))
        {
            alertDialog.setMessage(result);
            alertDialog.show();
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Intent regIntent = new Intent(context, LogInActivity.class);
                    context.startActivity(regIntent);
                }
            });
        }
        else
        {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        alertDialog.setMessage("Performing...");
        alertDialog.show();
    }
}
