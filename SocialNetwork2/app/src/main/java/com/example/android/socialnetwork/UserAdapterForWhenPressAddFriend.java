package com.example.android.socialnetwork;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Martin on 5/15/2018.
 */

public class UserAdapterForWhenPressAddFriend extends ArrayAdapter<user> {

    public UserAdapterForWhenPressAddFriend(@NonNull Context context, ArrayList<user> userList){
        super(context, R.layout.view_for_user_adapter_with_add, userList);
        arrayList = userList ;
    }

    ArrayList<user>arrayList = new ArrayList<>();

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.view_for_user_adapter_with_add, parent, false);



        return customView ;
    }
}
