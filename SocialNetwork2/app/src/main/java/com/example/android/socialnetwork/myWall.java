package com.example.android.socialnetwork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.PostHelper;
import com.example.android.socialnetwork.packagefordb.UserHelper;
import com.example.android.socialnetwork.packagefordb.UserHelper;
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;

import com.example.android.socialnetwork.packagefordb.usercontract.PostEntry;

import java.util.ArrayList;

public class myWall extends AppCompatActivity {
    private PostHelper postHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wall);
        postHelper = new PostHelper(this);
        show_my_posts();
    }

    public void show_my_posts(){


        String id = String.valueOf(personal_page.this_user_id);

        String [] projection = {PostEntry.COLUMN_postid , PostEntry.COLUMN_likes , PostEntry.COULMN_POST} ;
        String selection = PostEntry.COULMN_userid+" = ?";
        String [] selectionargs = {id} ;
        SQLiteDatabase sql= postHelper.getReadableDatabase();
        Cursor c = sql.query(PostEntry.TABLE_NAME ,projection,selection,selectionargs,null,null,PostEntry.COLUMN_postid + " DESC" ,null);
        // Get all posts of that user into an arraylist

        ArrayList<post>my_posts=new ArrayList<>();

        int n = c.getCount() ;

        c.moveToFirst();
        int i = 0 ;
        while(i<n) {
            post a = new post(c.getString(c.getColumnIndex(PostEntry.COULMN_POST)), c.getInt(c.getColumnIndex(PostEntry.COLUMN_likes))
                    , c.getInt(c.getColumnIndex(PostEntry.COLUMN_postid)) ) ;
            my_posts.add(a);
            c.moveToNext();
            i++;
        }
        // Pass them to arrayadapter then to screen
        ListAdapter customListAdapter = new PostAdapter(this,my_posts);// Pass the array to the constructor.
        ListView customListView = (ListView) findViewById(R.id.wall_list_view);
        customListView.setAdapter(customListAdapter);
        //customListView.setOnItemClickListener(
          //      new AdapterView.OnItemClickListener() {
            //        @Override
              //      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //        String postBody = String.valueOf(parent.getItemAtPosition(position));
                  //      Toast.makeText(myWall.this, postBody, Toast.LENGTH_LONG).show();
            //        }
          //      }
        //);
    }




}
