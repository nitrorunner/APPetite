package com.example.ryanj11_tech.appetite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class TipActivity extends AppCompatActivity {

    DecimalFormat df = new DecimalFormat("#.00");
    EditText billCost, tipCost, billTotal, tipTotal;
    double bCost, tCost, bTotal, tTotal;
    Button btnCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        billCost = (EditText) findViewById(R.id.billCost);
        tipCost = (EditText) findViewById(R.id.tipCost);
        billTotal = (EditText) findViewById(R.id.billTotal);
        tipTotal = (EditText) findViewById(R.id.tipTotal);
        btnCal = (Button) findViewById(R.id.btnCal);

       billTotal.setFocusable(false);
       billTotal.setFocusableInTouchMode(false);
       tipTotal.setFocusable(false);
       tipTotal.setFocusableInTouchMode(false);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (billCost.getText().length() > 0) {
                    bCost = Double.parseDouble(billCost.getText().toString());
                } else {
                    bCost = 0.00;
                }
                if (tipCost.getText().length() > 0) {
                    tCost = Double.parseDouble(tipCost.getText().toString());
                } else {
                    tCost = .00;
                }
                tTotal = bCost * (tCost*.01);
                bTotal = tTotal + bCost;

                String tSum = df.format(tTotal);
                String bSum = df.format(bTotal);

                tipTotal.setText(tSum);
                billTotal.setText(bSum);

            }
        });



    }
}
