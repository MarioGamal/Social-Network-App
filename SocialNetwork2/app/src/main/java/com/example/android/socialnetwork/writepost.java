package com.example.android.socialnetwork;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.PostHelper ;
import com.example.android.socialnetwork.packagefordb.usercontract.PostEntry ;


public class writepost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writepost);
    }

    public  void AddPost (View view){
        EditText post = (EditText)findViewById(R.id.new_post) ;
        String post_body = post.getText().toString() ;

        //Add post to posts table
        PostHelper postHelper = new PostHelper(this) ;
        boolean isInserted = postHelper.insertPost(personal_page.this_user_id,0,post_body);

        //Now display a toast message and return automatically to personal_page file
       if(isInserted)
       {
           Toast.makeText(this,"Posted Successfully",Toast.LENGTH_SHORT).show();
           finish();
       }
    }
}