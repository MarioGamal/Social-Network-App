package com.example.android.socialnetwork;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.socialnetwork.packagefordb.UserHelper;import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import com.example.android.socialnetwork.packagefordb.UserHelper;
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import com.example.android.socialnetwork.packagefordb.UserHelper;
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import com.example.android.socialnetwork.packagefordb.UserHelper;
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import com.example.android.socialnetwork.packagefordb.UserHelper;
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;


public class view_all_not_friends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_not_friends);
        ListAllNotFriends();
    }

    public void ListAllNotFriends(){
        UserHelper userHelper = new UserHelper(this);
        SQLiteDatabase sql = userHelper.getReadableDatabase();
        String [] projection = {userEntry.COULMN_UserName , userEntry.COULMN_number_friends , userEntry.COULMN_friends , userEntry._ID} ;
        String selection = userEntry._ID+"=?";
        String [] selectionargs = {String.valueOf(personal_page.this_user_id)} ;
        Cursor c = sql.query(userEntry.TABLE_NAME , projection , selection , selectionargs ,null,null,null ) ;
        Cursor c2 = sql.query(userEntry.TABLE_NAME , projection,null,null,null,null,null);
        c2.moveToFirst();
        int no_of_all_users = c2.getCount() ;
        c.moveToFirst();

        String all_his_friends = c.getString(c.getColumnIndex(userEntry.COULMN_friends)) ;
        int no_his_friends = c.getInt(c.getColumnIndex(userEntry.COULMN_number_friends)) ;

        String [] friends = all_his_friends.split(",") ;

        boolean is_friend = false;

        ArrayList<user>a = new ArrayList<>() ;
        for(int i = 0 ; i<no_of_all_users ; i++)
        {
            is_friend = false ;
            String id_being_tested = String.valueOf(c2.getInt(c.getColumnIndex(userEntry._ID))) ;
            for(int j = 0 ; j<no_his_friends;j++)
            {
                if(id_being_tested.equals(friends[j]) || id_being_tested.equals(String.valueOf(personal_page.this_user_id))){
                    is_friend = true ;
                    break;
                }
            }
            if(!is_friend){
                // Add to ArrayList to display on screen
                user us = new user(c2.getString(c2.getColumnIndex(userEntry.COULMN_UserName)) ,
                        c2.getInt(c2.getColumnIndex(userEntry.COULMN_number_friends)),
                        c2.getInt(c2.getColumnIndex(userEntry._ID))) ;
                a.add(us);
            }
            c2.moveToNext();
        }


        // List them on screen
        final ListAdapter customListAdapter = new UserAdapterWithAddFriend(this,a);// Pass the array to the constructor.
        final ListView customListView = (ListView) findViewById(R.id.list_of_non_friends);
        customListView.setAdapter(customListAdapter);

    }
}
