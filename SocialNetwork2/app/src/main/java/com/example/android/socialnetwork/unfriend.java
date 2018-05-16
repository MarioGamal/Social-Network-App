package com.example.android.socialnetwork;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class unfriend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfriend);
        show_friends();
    }

  public   ArrayList<user> friends_all = new ArrayList<>();

    public void show_friends() {


        UserHelper userHelper = new UserHelper(this);
        SQLiteDatabase sql = userHelper.getReadableDatabase();

        String[] projection = {userEntry.COULMN_UserName, userEntry.COULMN_number_friends, userEntry.COULMN_friends, userEntry._ID};

        Cursor c = sql.query(userEntry.TABLE_NAME, projection, userEntry._ID + "=?", new String[]{String.valueOf(personal_page.this_user_id)},
                null, null, null);
        c.moveToFirst();

        String friends1 = c.getString(c.getColumnIndex(userEntry.COULMN_friends));
        int n = c.getInt(c.getColumnIndex(userEntry.COULMN_number_friends));

        String[] friends2 = friends1.split(",");

        for (int i = 0; i < n; i++) {
            c = sql.query(userEntry.TABLE_NAME, projection, userEntry._ID + "=?", new String[]{friends2[i]}, null, null, null);
            c.moveToFirst();
            user a = new user(c.getString(c.getColumnIndex(userEntry.COULMN_UserName)), c.getInt(c.getColumnIndex(userEntry.COULMN_number_friends))
                    , c.getInt(c.getColumnIndex(userEntry._ID)));
            friends_all.add(a);
        }



        //Pass to adapter then to screen

         ListAdapter customListAdapter = new UserAdapterWhenUnfriend(this, friends_all);// Pass the array to the constructor.
         ListView customListView = (ListView) findViewById(R.id.liste);
        customListView.setAdapter(customListAdapter);

        customListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        check_if_want_to_delete(position);
                    }
                }
        );

    }

    public void check_if_want_to_delete(int position) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        String title = "You are about to unfriend a friend of yours";
        String message = "Are you sure you want to remove this friend from friends list ? ";
        alertDialog.setMessage(message);
        alertDialog.setTitle(title);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                remove_friend(position);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                do_nothing();
            }
        });
        alertDialog.show();
    }

    public void do_nothing() {
        return;
    }


    public void remove_friend(int position)
    {
        int my_id = personal_page.this_user_id ;
        int another_user_id = friends_all.get(position)._Id;

        String[] projection = {userEntry.COULMN_UserName, userEntry.COULMN_number_friends, userEntry.COULMN_friends, userEntry._ID};
        UserHelper userHelper = new UserHelper(this);
        SQLiteDatabase sql = userHelper.getReadableDatabase();



        Cursor c = sql.query(userEntry.TABLE_NAME, projection, userEntry._ID + "=?", new String[]{String.valueOf(my_id)}
                , null, null, null);
        c.moveToFirst();

        String friends = c.getString(c.getColumnIndex(userEntry.COULMN_friends));
        String new_friends = friends.replace(String.valueOf(another_user_id) + ",", "");

        ContentValues contentValues = new ContentValues();

        int no_old_friends = c.getInt(c.getColumnIndex(userEntry.COULMN_number_friends));

        contentValues.put(userEntry.COULMN_friends, new_friends);
        contentValues.put(userEntry.COULMN_number_friends, no_old_friends - 1);

        SQLiteDatabase sql4 = userHelper.getWritableDatabase() ;

        sql4.update(userEntry.TABLE_NAME, contentValues,  userEntry._ID + "=" + String.valueOf(my_id), null);



        //update another user too

        Cursor d ;
        SQLiteDatabase sql2 = userHelper.getReadableDatabase() ;
         d = sql.query(userEntry.TABLE_NAME, projection, userEntry._ID + "=?", new String[]{String.valueOf(another_user_id)}
                , null, null, null);
         d.moveToFirst() ;

         friends = d.getString(d.getColumnIndex(userEntry.COULMN_friends)) ;
         new_friends = friends.replace(String.valueOf(another_user_id)+"," , "" );

         no_old_friends = d.getInt(d.getColumnIndex(userEntry.COULMN_number_friends))  ;

         contentValues.put(userEntry.COULMN_friends , new_friends);
         contentValues.put(userEntry.COULMN_number_friends,no_old_friends-1);

         SQLiteDatabase sql3 = userHelper.getWritableDatabase() ;

         sql.update(userEntry.TABLE_NAME , contentValues , userEntry._ID+"="+String.valueOf(another_user_id) , null) ;

        Toast.makeText(this, " deleted ", Toast.LENGTH_LONG).show();
    }
}


