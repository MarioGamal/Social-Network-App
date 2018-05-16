package com.example.android.socialnetwork;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.socialnetwork.packagefordb.UserHelper;
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import java.util.ArrayList;

/**
 * Created by Martin on 5/16/2018.
 */

public class UserAdapterWhenUnfriend extends ArrayAdapter<user> {

    public UserAdapterWhenUnfriend(@NonNull Context context, ArrayList<user> userList){
        super(context, R.layout.unfriend , userList);
        Toast.makeText(getContext() , "Arraylist etmlt "  , Toast.LENGTH_LONG).show();
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        final View customView = myCustomInflater.inflate(R.layout.unfriend, parent, false);


        TextView itemText = (TextView) customView.findViewById(R.id.item_text);
        final TextView smallItemText = (TextView) customView.findViewById(R.id.item_small_text);
        ImageView buckysImage = (ImageView) customView.findViewById(R.id.my_profile_image);
        final user singleUser = getItem(position);
        // dynamically update the text from the array
        itemText.setText(singleUser.name);
        smallItemText.setText("Has " + singleUser.no_of_friends + " friends");
        // using the same image every time
        buckysImage.setImageResource(R.drawable.icons8_account_96);


        return customView ;
    }


    }
