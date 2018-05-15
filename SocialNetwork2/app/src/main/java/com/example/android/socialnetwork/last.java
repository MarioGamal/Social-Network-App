package com.example.android.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.socialnetwork.packagefordb.PostHelper;
import com.example.android.socialnetwork.packagefordb.UserHelper;import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.socialnetwork.packagefordb.UserHelper;
import com.example.android.socialnetwork.packagefordb.usercontract;
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;

import java.util.ArrayList;
import java.util.List;


public class last extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        show_info();
    }

    public void show_info(){
        UserHelper userHelper = new UserHelper(this) ;
        PostHelper postHelper = new PostHelper(this) ;

        SQLiteDatabase sql_post = postHelper.getReadableDatabase();
        SQLiteDatabase sql = userHelper.getReadableDatabase() ;

        String [] projection = {userEntry._ID ,userEntry.COULMN_UserName ,userEntry.COULMN_Ids_liked_posts,userEntry.COULMN_number_friends} ;

        String [] projection2 = {usercontract.PostEntry.COLUMN_likes , usercontract.PostEntry.COULMN_POST , usercontract.PostEntry.COLUMN_postid
        , usercontract.PostEntry.COULMN_userid} ;

        Cursor c = sql.query(userEntry.TABLE_NAME,projection,null,null,null,null,null) ;

        int no_of_users = c.getCount() ;  // No of users


        Cursor d = sql_post.query(usercontract.PostEntry.TABLE_NAME , projection2,null,null,null,null,null);

        int no_posts = d.getCount() ;    // No of posts


        // Find mmost popular user

        int max = -1 ;
       if(c.getCount()>0)
       {
           c.moveToFirst();
           max = c.getInt(c.getColumnIndex(userEntry.COULMN_number_friends));

           int id_max = 1;
           c.moveToNext();
           for (int i = 1; i < no_of_users; i++) {
               if (c.getInt(c.getColumnIndex(userEntry.COULMN_number_friends)) > max) {
                   max = c.getInt(c.getColumnIndex(userEntry.COULMN_number_friends));
                   id_max = c.getInt(c.getColumnIndex(userEntry._ID));
               }
               c.moveToNext();
           }
           c = sql.query(userEntry.TABLE_NAME , projection , userEntry._ID+"=?" , new String[] {String.valueOf(id_max)} ,

                   null,null,null ) ;
           c.moveToFirst() ;

           String popular_user_name = c.getString(c.getColumnIndex(userEntry.COULMN_UserName)) ;
           TextView fam = (TextView)findViewById(R.id.most_popular) ;
           fam.setText("Most popular user is : "+popular_user_name);

       }

       // Find most popular post

        if(d.getCount()>0)
        {
            d.moveToFirst();
            int max_likes = d.getInt(d.getColumnIndex(usercontract.PostEntry.COLUMN_likes)) ;
            int id_max = d.getInt(d.getColumnIndex(usercontract.PostEntry.COLUMN_postid)) ;
            int limit = d.getCount() ;
            d.moveToNext();

            for(int i = 1 ; i<limit ; i++)
            {
                if(d.getInt(d.getColumnIndex(usercontract.PostEntry.COLUMN_likes)) > max_likes)
                {
                    max_likes = d.getInt(d.getColumnIndex(usercontract.PostEntry.COLUMN_likes)) ;
                    id_max = d.getInt(d.getColumnIndex(usercontract.PostEntry.COLUMN_postid)) ;
                }
                d.moveToNext();
            }
            d = sql_post.query(usercontract.PostEntry.TABLE_NAME , projection2 , usercontract.PostEntry.COLUMN_postid+"=?",
                    new String[] {String.valueOf(id_max)} ,null,null,null ) ;
            d.moveToFirst();

            String most_liked_post = d.getString(d.getColumnIndex(usercontract.PostEntry.COULMN_POST)) ;

            TextView most = (TextView)findViewById(R.id.most_liked_post);
            most.setText("Most liked post is:"+most_liked_post);
        }



        // Find user who liked most

        d = sql_post.query(usercontract.PostEntry.TABLE_NAME , projection2 , null,null,null,null,null);
        c = sql.query(userEntry.TABLE_NAME , projection , null,null,null,null,null);

        //Construct a list of users with users ids




        if( c.getCount()>0 && d.getCount()>0 )
        {
            int x = c.getCount();
            c.moveToFirst();

            int id_user = c.getInt(c.getColumnIndex(userEntry._ID)) ;
            String posts_liked = c.getString(c.getColumnIndex(userEntry.COULMN_Ids_liked_posts));
            int max_likes = 0 ;
            for (int i = 0; i < posts_liked.length(); i++)
            {
                if (posts_liked.charAt(i) == ',')
                {
                    max_likes++;
                }
            }
            c.moveToNext();

            for(int i= 0 ; i<x ; i++)
            {
                String temp = c.getString(c.getColumnIndex(userEntry.COULMN_Ids_liked_posts));
                int likes = 0 ;
                for (int j = 0; j < posts_liked.length(); j++)
                {
                    if (posts_liked.charAt(j) == ',')
                    {
                        likes++;
                    }
                }

               if(likes > max_likes)
               {
                   max_likes = likes ;
                   id_user = c.getInt(c.getColumnIndex(userEntry._ID));
               }

               c.moveToNext();
            }

            c=sql.query(userEntry.TABLE_NAME , projection ,userEntry._ID+"=?" , new String[] {String.valueOf(id_user)} ,null
            ,null,null);
            c.moveToFirst();

            TextView max_liked = (TextView)findViewById(R.id.user_liked_most) ;
            max_liked.setText("User who liked most is : " + c.getString(c.getColumnIndex(userEntry.COULMN_UserName)));
        }

        TextView users = (TextView)findViewById(R.id.no_users) ;
        users.setText("Number of users now is : "+no_of_users);

        TextView posts = (TextView)findViewById(R.id.no_posts);
        posts.setText("Number of posts now is : " + no_posts);


    }
}
