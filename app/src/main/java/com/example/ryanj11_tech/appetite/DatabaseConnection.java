package com.example.ryanj11_tech.appetite;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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
    String getpromotion_URL = "http://menesesj2.leto.feralhosting.com/APPetite/getPromotions.php";
    String points_URL = "http://menesesj2.leto.feralhosting.com/APPetite/getPoints.php";
    String redeem_URL = "http://menesesj2.leto.feralhosting.com/APPetite/setPoints.php";
    String checkin_URL = "http://menesesj2.leto.feralhosting.com/APPetite/checkIn.php";
    String menu_URL = "http://menesesj2.leto.feralhosting.com/APPetite/getMenu.php";
    String review_URL = "http://menesesj2.leto.feralhosting.com/APPetite/getReviews.php";
    DatabaseConnection (Context ctx){
        context = ctx;
    }

    String result="";
    String line="";
    String username, type, JSON_STRING, usrPts, rdmPts, pts;
    ProgressDialog loading;

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
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
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("checkin")) {
            username = params[1];
            try {
                URL url = new URL(checkin_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
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
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("redeemPoints")) {
            usrPts = params[1];
            rdmPts = params[2];
            try {
                URL url = new URL(redeem_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(usrPts, "UTF-8") + "&" +
                        URLEncoder.encode("points", "UTF-8") + "=" + URLEncoder.encode(rdmPts, "UTF-8");
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
            String regPhone = params[6];
            String regStreet = params[7];
            String regCity = params[8];
            String regState = params[9];
            String regZip = params[10];
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
                        URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(regEmail, "UTF-8")+"&"+
                        URLEncoder.encode("phone", "UTF-8")+"="+URLEncoder.encode(regPhone, "UTF-8")+"&"+
                        URLEncoder.encode("street", "UTF-8")+"="+URLEncoder.encode(regStreet, "UTF-8")+"&"+
                        URLEncoder.encode("city", "UTF-8")+"="+URLEncoder.encode(regCity, "UTF-8")+"&"+
                        URLEncoder.encode("state", "UTF-8")+"="+URLEncoder.encode(regState, "UTF-8")+"&"+
                        URLEncoder.encode("zip", "UTF-8")+"="+URLEncoder.encode(regZip, "UTF-8");
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
        else if (type.equals("getPromotion"))
        {
            try {
                URL url = new URL(getpromotion_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = reader.readLine())!=null) {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                reader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                result = stringBuilder.toString().trim();
                return result;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("getMenu"))
        {
            try {
                URL url = new URL(menu_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = reader.readLine())!=null) {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                reader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                result = stringBuilder.toString().trim();
                return result;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("getReviews"))
        {
            try {
                URL url = new URL(review_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = reader.readLine())!=null) {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                reader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                result = stringBuilder.toString().trim();
                return result;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("getUserPoints"))
        {
            String userPts = params[1];
            try {
                URL url = new URL(points_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(userPts, "UTF-8");
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
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {

    }
    /**
     * onPostExecute determines the action to be performed dependending on the string retrieved from the php file
     * @param result returns the string a selected php file displays. Depending on the value, it will perform specified actions
     */
    @Override
    protected void onPostExecute(String result) {
        alertDialog = new AlertDialog.Builder(context).create();
        if (result.equals("Login Success"))
        {
            alertDialog.setMessage(result);
            alertDialog.show();
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Intent successIntent = new Intent(context, MainActivity.class);
                    modPrefs loginPref = new modPrefs(context, "LoginPrefs");
                    loginPref.putData("Username", username);
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
        else if (result.contains("review_response"))
        {
            modPrefs prefs = new modPrefs(context,"JSONReview");
            prefs.putData("JSON", result);
        }
        else if (result.contains("result"))
        {
            modPrefs prefs = new modPrefs(context,"JSONPromo");
            prefs.putData("JSON",result);
        }
        else if (result.contains("menu_response"))
        {
            modPrefs prefs = new modPrefs(context,"JSONMenu");
            prefs.putData("JSON",result);
        }
        else if (result.contains("Points"))
        {
            String arr[] = result.split(" ");
            String firstWord = arr[0];
            if (firstWord.equals("Points"))
            {
                String points = arr[1];
                modPrefs ptsPref = new modPrefs(context, "PointsPref");
                ptsPref.putData("Points", points);
            }
        }
        else if (result.equals("Redeemed"))
        {
            alertDialog.setMessage(result);
            alertDialog.show();
            modPrefs ptsPref = new modPrefs(context, "PointsPref");
            ptsPref.putData("Points" , rdmPts);
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Intent back2promo = new Intent(context,PromotionsActivity.class);
                    context.startActivity(back2promo);
                }
            });
        }
        else if (result.equals("Successfully checked in! You've received 200 points.!"))
        {
            modPrefs setPtsPref = new modPrefs(context, "PointsPref");
            String points = setPtsPref.getNameFromPref("Points");
            int iPoints = Integer.parseInt(points);
            int totalPts = (iPoints + 200);
            String total = Integer.toString(totalPts);
            setPtsPref.putData("Points", total);
        }
        else if (result.equals(null))
        {
            alertDialog.setMessage("Error in DatabaseConnection");
            alertDialog.show();
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
