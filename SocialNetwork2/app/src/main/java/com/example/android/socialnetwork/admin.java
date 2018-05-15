package com.example.android.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.socialnetwork.packagefordb.UserHelper;
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;

import java.util.ArrayList;
import java.util.Collections;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        show_all_users();
    }

    public void show_all_users(){
        UserHelper userHelper = new UserHelper(this);
        SQLiteDatabase sqLiteDatabase = userHelper.getReadableDatabase();

        String [] projection = {userEntry.COULMN_UserName , userEntry.COULMN_number_friends , userEntry._ID} ;

        Cursor c = sqLiteDatabase.query(userEntry.TABLE_NAME , projection , null, null,null,null,null,null) ;

        final ArrayList <user> all_users = new ArrayList<>();

        Collections.sort(all_users,new UsersArrange());

        int d = c.getCount();
        int i = 0  ;
        c.moveToFirst() ;
        while(i<d){
            user a = new user(c.getString(c.getColumnIndex(userEntry.COULMN_UserName)),c.getInt(c.getColumnIndex(userEntry.COULMN_number_friends)) ,
                    c.getInt(c.getColumnIndex(userEntry._ID))) ;
            all_users.add(a);
            i++;
            c.moveToNext();
        }


        Collections.sort(all_users ,new  UsersArrange()) ;
        // Pass them to users arrayadapter
        final ListAdapter customListAdapter = new UserAdapter(this,all_users);// Pass the array to the constructor.
        final ListView customListView = (ListView) findViewById(R.id.wall_list_view);
        customListView.setAdapter(customListAdapter);
        customListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //String postBody = String.valueOf(parent.getItemAtPosition(position));
                       // Toast.makeText(admin.this, ""+position + "  " + all_users.get(position).get_Id(), Toast.LENGTH_LONG ).show();

                        go_to_user_as_admin(all_users.get(position).get_Id());
                    }
                }
        );


    }

    public void go_to_user_as_admin(int id_of_user){
        Intent intent = new Intent(this , user_page_in_admin_mode.class);
        user_page_in_admin_mode.id = id_of_user ;
        startActivity(intent);
    }



}