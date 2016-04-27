package com.example.ryanj11_tech.appetite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReviewActivity extends AppCompatActivity {

    DatabaseConnection databaseConnection;
    String type, id, content, reviewer;
    String jID = "id";
    String jContent = "content";
    String jReviewer = "reviewer";

    modPrefs revPref;
    JSONObject jsonObject;
    JSONArray jsonArray;
    LinearLayout linear;
    ListView listView;
    Reviews reviews;
    String result;
    ReviewsListAdapter reviewsListAdapter;
    Button btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        listView = (ListView) findViewById(R.id.listViewReview);
        linear = (LinearLayout) findViewById(R.id.linearReview);

        databaseConnection = new DatabaseConnection(this);
        type = "getReviews";
        databaseConnection.execute(type);
        btnWrite = (Button) findViewById(R.id.btnWriteReview);

        modPrefs jsonPref = new modPrefs(ReviewActivity.this, "JSONReview");

        if (jsonPref.getNameFromPref("JSON").equals(null))
        {
            //do nothing
        }
        else
        {
            result = jsonPref.getNameFromPref("JSON");
            reviewsListAdapter = new ReviewsListAdapter(this, R.layout.list_review);
            listView.setAdapter(reviewsListAdapter);
            try {
                jsonObject = new JSONObject(result);
                jsonArray = jsonObject.getJSONArray("review_response");
                int count = 0;

                while (count<jsonArray.length())
                {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    id = JO.getString(jID);
                    content = JO.getString(jContent);
                    reviewer = JO.getString(jReviewer);

                    reviews = new Reviews(id, content, reviewer);
                    reviewsListAdapter.add(reviews);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void writeReview(View view) {
        final AlertDialog.Builder write = new AlertDialog.Builder(ReviewActivity.this);
        write.setMessage("Write a Review");
        final EditText review = new EditText(this);
        review.setHint("Give us your feedback...");
        write.setView(review);
        write.setPositiveButton("Review", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               String written = review.getText().toString();
                DatabaseConnection databaseConnection = new DatabaseConnection(ReviewActivity.this);
                String type = "writeReview";
                modPrefs checkLogin = new modPrefs(ReviewActivity.this, "LoginPrefs");
                String username = checkLogin.getNameFromPref("Username");
                databaseConnection.execute(type, username, written);
            }
        });
        write.show();
    }
}
