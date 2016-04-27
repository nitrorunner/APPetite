package com.example.ryanj11_tech.appetite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PromotionsActivity extends AppCompatActivity {

    DatabaseConnection databaseConnection;
    String type, name, description, points;
    String jName = "name";
    String jDesc = "description";
    String jPoints = "points";
    modPrefs ptsPref;
    JSONObject jsonObject;
    JSONArray jsonArray;
    LinearLayout linear;
    ListView listView;
    Promotions promotions;
    PromotionsListAdapter promotionsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions);

        listView = (ListView) findViewById(R.id.listView);
        linear = (LinearLayout) findViewById(R.id.linearLay);

        databaseConnection = new DatabaseConnection(this);
        type = "getPromotion";
        databaseConnection.execute(type);

        ptsPref = new modPrefs(PromotionsActivity.this, "PointsPref");
        String points = ptsPref.getNameFromPref("Points");
        TextView textView = (TextView) findViewById(R.id.promoPts);
        textView.setText("You have: " + points + " points.");

        modPrefs jsonPref = new modPrefs(PromotionsActivity.this, "JSONPromo");
        if (jsonPref.getNameFromPref("JSON").equals(null))
        {
            //do nothing
        }
        else
        {
            String result = jsonPref.getNameFromPref("JSON");

            promotionsListAdapter = new PromotionsListAdapter(this, R.layout.list_promo);
            listView.setAdapter(promotionsListAdapter);
            try {
                jsonObject = new JSONObject(result);
                jsonArray = jsonObject.getJSONArray("result");
                int count = 0;

                while (count<jsonArray.length())
                {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    name = JO.getString(jName);
                    description = JO.getString(jDesc);
                    points = JO.getString(jPoints);

                    promotions = new Promotions(name, description,points);
                    promotionsListAdapter.add(promotions);
                    count++;
                }
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> promotionsListAdapter, View view, int position, long id) {
                                Intent intent = new Intent (PromotionsActivity.this, SelectPromotion.class);
                                TextView textName = (TextView) view.findViewById(R.id.txtName);
                                TextView textDescription = (TextView) view.findViewById(R.id.txtDesc);
                                TextView textPoints = (TextView) view.findViewById(R.id.txtPts);

                                String text = textName.getText().toString();
                                String text2 = textDescription.getText().toString();
                                String text3 = textPoints.getText().toString();

                                intent.putExtra("name", text);
                                intent.putExtra("description", text2);
                                intent.putExtra("points", text3);
                                startActivity(intent);
                            }
                        });

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
