package com.example.ryanj11_tech.appetite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jace on 4/25/2016.
 */
public class MenuListAdapter extends ArrayAdapter{

    List list = new ArrayList<>();
    public MenuListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Menu object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        MenuHolder menuHolder;

        //checks if row exists. If it does not exist, creates it using LayoutInflater
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_menu,parent,false);
            menuHolder = new MenuHolder();
            menuHolder.txt_name = (TextView) row.findViewById(R.id.menuName);
            menuHolder.txt_price = (TextView) row.findViewById(R.id.menuPrice);
            menuHolder.txt_description = (TextView) row.findViewById(R.id.menuDescription);

            row.setTag(menuHolder);

        }
        else
        {
            menuHolder = (MenuHolder) row.getTag();
        }
        Menu menu = (Menu) this.getItem(position);

        menuHolder.txt_name.setText(menu.getName());
        menuHolder.txt_price.setText(menu.getDescription());
        menuHolder.txt_description.setText(menu.getPrice());
        return row;
    }

    static class MenuHolder
    {
        TextView txt_name, txt_price, txt_description;
    }

}
