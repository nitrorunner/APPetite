package com.example.ryanj11_tech.appetite;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectPromotion extends AppCompatActivity {

    String name, description, points;
    Bundle extras;
    TextView promoName, promoDesc, promoPoints;
    Button button;
    String type, totalPts, userPts, username;
    modPrefs ptsPref, userPref;
    int usrPts, rdmPts, total;
    DatabaseConnection databaseConnection;
    DialogInterface.OnClickListener dialog;
    AlertDialog.Builder alertDialog, builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_promotion);

        extras = getIntent().getExtras();
        name = extras.getString("name");
        description = extras.getString("description");
        points = extras.getString("points");

        promoName = (TextView) findViewById(R.id.sPromoName);
        promoDesc = (TextView) findViewById(R.id.sPromoDesc);
        promoPoints = (TextView) findViewById(R.id.sPromoPts);

        button = (Button) findViewById(R.id.btnRedeem);

        promoName.setText(name);
        promoDesc.setText(description);
        promoPoints.setText("Cost: " + points);

        ptsPref = new modPrefs(this, "PointsPref");
        userPts = ptsPref.getNameFromPref("Points");

        userPref = new modPrefs(this, "LoginPrefs");
        username = userPref.getNameFromPref("Username");

        usrPts = Integer.parseInt(userPts);
        rdmPts = Integer.parseInt(points);

        total = (usrPts - rdmPts);
        totalPts = Integer.toString(total);


        {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog = new AlertDialog.Builder(SelectPromotion.this);
                    if (total < 0)
                    {
                        alertDialog.setMessage("You don't have enough points to redeem this.");
                        alertDialog.show();
                    }
                    else
                    {
                        builder = new AlertDialog.Builder(SelectPromotion.this);
                        builder.setMessage("Are you sure you wish to redeem. You will be left with " + total + " points").setPositiveButton("Yes", dialog).setNegativeButton("No", dialog);

                        dialog = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        databaseConnection = new DatabaseConnection(SelectPromotion.this);
                                        type = "redeemPoints";
                                        databaseConnection.execute(type, username, totalPts);
                                        break;
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        };
                        builder.show();
                    }
                }
            });
        }
    }
}
