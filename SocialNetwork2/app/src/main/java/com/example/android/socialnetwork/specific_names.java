package com.example.android.socialnetwork;

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

public class specific_names extends AppCompatActivity {

    public static String name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_names);
        name = search_name.name_to_search ;
        show_users_looked_for();

    }
    public void show_users_looked_for()
    {

        UserHelper userHelper = new UserHelper(this) ;
        SQLiteDatabase sqLiteDatabase = userHelper.getReadableDatabase() ;

        String [] projection = {userEntry.COULMN_UserName , userEntry.COULMN_number_friends , userEntry._ID} ;
        String selection = userEntry.COULMN_UserName+"=?" ;
        String [] selectionargs = {name} ;

        Cursor c = sqLiteDatabase.query(userEntry.TABLE_NAME , projection , selection , selectionargs , null , null, null ) ;

        ArrayList <user> all_users = new ArrayList<>();

        Cursor c2 = sqLiteDatabase.query(userEntry.TABLE_NAME , projection , null,null,null,null,null);



        if (c2.getCount() > 0) {
            int d = c2.getCount();
            int i = 0 ;
            c2.moveToFirst() ;
            while (i<d)
            {
                if (c2.getString(c2.getColumnIndex(userEntry.COULMN_UserName)).equals(name) ||
                        c2.getString(c2.getColumnIndex(userEntry.COULMN_UserName)).contains(name))
                {


                    user a = new user(c2.getString(c2.getColumnIndex(userEntry.COULMN_UserName)),
                            c2.getInt(c2.getColumnIndex(userEntry.COULMN_number_friends)),
                            c2.getInt(c2.getColumnIndex(userEntry._ID)));
                    all_users.add(a);
                }
                i++;
                c2.moveToNext();
            }

            Collections.sort(all_users , new  UsersArrange());

            final ListAdapter customListAdapter = new UserAdapterWithAddFriend(this,all_users);// Pass the array to the constructor.
            final ListView customListView = (ListView) findViewById(R.id.list);
            customListView.setAdapter(customListAdapter);

            /* When press a user it's position in ArrayList is position -> add this friend
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

            */
        }

        else {
            Toast.makeText(this , "No such User   " + search_name.name_to_search  ,Toast.LENGTH_LONG).show() ;
            //Intent intent = new Intent(this , search_name.class) ;
            //startActivity(intent);
            }

        }


}



