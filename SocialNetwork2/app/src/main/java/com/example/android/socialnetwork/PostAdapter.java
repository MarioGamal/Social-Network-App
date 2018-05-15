package com.example.android.socialnetwork;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class PostAdapter extends ArrayAdapter<post> {
    public PostAdapter(@NonNull Context context, ArrayList<post> postList) {
        super(context,R.layout.custom_row,postList);

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.custom_row, parent, false);
        // get references.
        post singlePost = getItem(position);
        TextView itemText = (TextView) customView.findViewById(R.id.item_text);
        TextView smallItemText = (TextView) customView.findViewById(R.id.item_small_text);
        ImageView buckysImage = (ImageView) customView.findViewById(R.id.my_profile_image);
        // dynamically update the text from the array
        itemText.setText(singlePost.content);
        smallItemText.setText("");
        // using the same image every time
        buckysImage.setImageResource(R.drawable.icons8_account_96);
        // Now we can finally return our custom View or custom item
        return customView;
    }
}
