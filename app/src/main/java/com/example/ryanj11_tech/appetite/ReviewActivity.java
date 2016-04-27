package com.example.ryanj11_tech.appetite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        listView = (ListView) findViewById(R.id.listViewReview);
        linear = (LinearLayout) findViewById(R.id.linearReview);

        databaseConnection = new DatabaseConnection(this);
        type = "getReviews";
        databaseConnection.execute(type);

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
}
