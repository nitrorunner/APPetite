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

public class MenuActivity extends AppCompatActivity {

    DatabaseConnection databaseConnection;
    String type, name, description, price;
    String jName = "item_name";
    String jPrice = "price";
    String jDescription = "description";

    modPrefs menuPref;
    JSONObject jsonObject;
    JSONArray jsonArray;
    LinearLayout linear;
    ListView listView;
    Menu menu;
    String result;
    MenuListAdapter menuListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        listView = (ListView) findViewById(R.id.listViewMenu);
        linear = (LinearLayout) findViewById(R.id.linearMenu);

        databaseConnection = new DatabaseConnection(this);
        type = "getMenu";
        databaseConnection.execute(type);

        modPrefs jsonPref = new modPrefs(MenuActivity.this, "JSONMenu");

        if (jsonPref.getNameFromPref("JSON").equals(null))
        {
            //do nothing
        }
        else
        {
            result = jsonPref.getNameFromPref("JSON");

            menuListAdapter = new MenuListAdapter(this, R.layout.list_menu);
            listView = (ListView) findViewById(R.id.listViewMenu);
            listView.setAdapter(menuListAdapter);
            try {
                jsonObject = new JSONObject(result);
                jsonArray = jsonObject.getJSONArray("menu_response");
                int count = 0;

                while (count<jsonArray.length())
                {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    name = JO.getString(jName);
                    description = JO.getString(jDescription);
                    price = JO.getString(jPrice);

                    menu = new Menu(name, description, price);
                    menuListAdapter.add(menu);
                    count++;
                }

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> promotionsListAdapter, View view, int position, long id) {

                        Intent intent = new Intent (MenuActivity.this, ViewOrder.class);
                        TextView textName = (TextView) view.findViewById(R.id.menuName);
                        TextView textDescription = (TextView) view.findViewById(R.id.menuDescription);
                        TextView textPrice = (TextView) view.findViewById(R.id.menuPrice);

                        String text = textName.getText().toString();
                        String text2 = textDescription.getText().toString();
                        String text3 = textPrice.getText().toString();

                        intent.putExtra("name", text);
                        intent.putExtra("description", text2);
                        intent.putExtra("price", text3);
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
