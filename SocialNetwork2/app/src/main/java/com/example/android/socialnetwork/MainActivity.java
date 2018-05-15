package com.example.android.socialnetwork;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.android.socialnetwork.packagefordb.UserHelper;
import java.util.ArrayList;

import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView sign_up=(TextView)findViewById(R.id.sign_up); // Navigate to choose username , passwords
        TextView admin = (TextView)findViewById(R.id.admin);  // Navigate to show all users
        TextView log_in=(TextView)findViewById(R.id.log_in);  // Navigate to Enter id/username
    }

    public static ArrayList<user> users = new ArrayList<>();


    public void ne(View view) {
        Intent n = new Intent(this , signup.class);
        startActivity(n);
    }
    public void gotoLogin(View view){
        Intent n  = new Intent(this,Login.class);
        startActivity(n);
    }
    public void gotoAdmin(View view)
    {
        Intent n = new Intent(this,admin.class);
        startActivity(n);
    }


}
