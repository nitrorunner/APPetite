package com.example.ryanj11_tech.appetite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by john on 4/13/2016.
 */
public class ReviewActivity extends AppCompatActivity  {
    private static Button button_sbm;
    private static TextView text_v;
    private static RatingBar rating_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_write_review);

        setContentView(R.layout.activity_reviews);

        listenerForRatingBar();
        setButtonClickListener();

        Button btnWrtRe =(Button) findViewById(R.id.btnWrtRe);
        btnWrtRe.setOnClickListener (this);

        Button btnPstRe =(Button) findViewById(R.id.btnPstRe);
        btnPstRe.setOnClickListener (this);


    }


    public void listenerForRatingBar() {
        rating_b = (RatingBar) findViewById(R.id.ratingBar);
        text_v = (TextView) findViewById(R.id.textView);

        rating_b .setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener(){
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        text_v.setText(String.valueOf(rating));
                    }
                }
        );

    }
    public void setButtonClickListener(){
        rating_b = (RatingBar) findViewById(R.id.ratingBar);
        button_sbm = (Button) findViewById(R.id.button);

        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ReviewActivity.this,
                                String.valueOf(rating_b.getRating()),
                                Toast.LENGTH_SHORT) .show();
                    }
                }
        );
    }



    public void OnClick(View view) {


    }
}
