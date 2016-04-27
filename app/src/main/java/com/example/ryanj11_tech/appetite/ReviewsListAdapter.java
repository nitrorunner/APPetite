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
 * Created by Jace on 4/27/2016.
 */
public class ReviewsListAdapter extends ArrayAdapter {

    List list = new ArrayList<>();
    public ReviewsListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Reviews object) {
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
        ReviewsHolder reviewsHolder;

        //checks if row exists. If it does not exist, creates it using LayoutInflater
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_review,parent,false);
            reviewsHolder = new ReviewsHolder();
            reviewsHolder.tx_id = (TextView) row.findViewById(R.id.revID);
            reviewsHolder.tx_content = (TextView) row.findViewById(R.id.revContent);
            reviewsHolder.tx_reviewer = (TextView) row.findViewById(R.id.revUser);

            row.setTag(reviewsHolder);

        }
        else
        {
            reviewsHolder = (ReviewsHolder) row.getTag();
        }
        Reviews reviews = (Reviews) this.getItem(position);

        reviewsHolder.tx_id.setText(reviews.getID());
        reviewsHolder.tx_content.setText("\"" + reviews.getContent() + "\"");
        reviewsHolder.tx_reviewer.setText("-" + reviews.getReviewer());
        return row;
    }

    static class ReviewsHolder
    {
        TextView tx_id, tx_content, tx_reviewer;
    }
}
