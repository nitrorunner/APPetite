package com.example.ryanj11_tech.appetite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jace on 4/23/2016.
 */
public class PromotionsListAdapter extends ArrayAdapter {

    List list = new ArrayList<>();
    public PromotionsListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Promotions object) {
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
        PromotionsHolder promotionsHolder;

        //checks if row exists. If it does not exist, creates it using LayoutInflater
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_promo,parent,false);
            promotionsHolder = new PromotionsHolder();
            promotionsHolder.tx_name = (TextView) row.findViewById(R.id.txtName);
            promotionsHolder.tx_desc = (TextView) row.findViewById(R.id.txtDesc);
            promotionsHolder.tx_points = (TextView) row.findViewById(R.id.txtPts);

            row.setTag(promotionsHolder);

        }
        else
        {
            promotionsHolder = (PromotionsHolder) row.getTag();
        }
        Promotions promotions = (Promotions)this.getItem(position);

        promotionsHolder.tx_name.setText(promotions.getName());
        promotionsHolder.tx_desc.setText(promotions.getDescription());
        promotionsHolder.tx_points.setText(promotions.getPoints());
        return row;
    }

    static class PromotionsHolder
    {
        TextView tx_name, tx_desc, tx_points;
    }
}
