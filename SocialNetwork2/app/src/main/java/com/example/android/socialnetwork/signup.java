
package com.example.android.socialnetwork;

import android.content.ContentValues;
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
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;

public class signup extends AppCompatActivity {



    private UserHelper userDb ;

    // Identify the 2 checkboxes of male and female in activity_signup//

    CheckBox male ;
    CheckBox female;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userDb = new UserHelper(this);
    }

    // This id we will use to get info about this user ready when navigate to personal_page

    public static int id = 0;

    public static String user = "";

    /* function navigates into personal page after signing up */

    public void save(View view){
        EditText name = (EditText)findViewById(R.id.username);
        EditText pass = (EditText)findViewById(R.id.password);
        EditText repass = (EditText)findViewById(R.id.repeat_password);

        String name2 = name.getText().toString() ;
        String pass2 =  pass.getText().toString();
        String passChecker = repass.getText().toString();
        boolean isInserted = false;

        // Filling the table //
        if(name2.equals("") || pass2.equals("") || !(pass2.equals(passChecker)))
        {
            Toast.makeText(this,"Please fill in your data correctly",Toast.LENGTH_LONG).show();
        }
        else {
            SQLiteDatabase userdb = new UserHelper(this).getWritableDatabase();
            ContentValues contentvalues = new ContentValues();
            contentvalues.put(userEntry.COULMN_UserName,name2);
            contentvalues.put(userEntry.COULMN_number_friends,0);
            contentvalues.put(userEntry.COULMN_friends,"");
            contentvalues.put(userEntry.COULMN_password,pass2);
            long result = userdb.insert(userEntry.TABLE_NAME,null,contentvalues);
            Intent personal_page = new Intent(this,personal_page.class);

                Toast.makeText(this, "Data inserted", Toast.LENGTH_LONG).show();
                startActivity(personal_page);
        }

        SQLiteDatabase ins = userDb.getWritableDatabase();



        Cursor z = ins.rawQuery("select * from "+userEntry.TABLE_NAME,null);
        int count = z.getCount();
        // To move the cursor to the last row which contains the info we just inserted
        z.moveToLast();

        id = z.getInt(z.getColumnIndex(userEntry._ID));
        user = z.getString(z.getColumnIndex(userEntry.COULMN_UserName));




       // Next window will open
        Intent personal_page = new Intent(this,personal_page.class);
        if(isInserted) {
            Toast.makeText(this, "Data inserted", Toast.LENGTH_LONG).show();
            startActivity(personal_page);
        }



       /*====================The following is to check wheather the data was inserted correctly or not ===================*/


       /*

        String[] projection ={userEntry._ID , userEntry.COULMN_UserName , userEntry.COULMN_friends, userEntry.COULMN_number_friends
       ,userEntry.COULMN_password};

        Cursor z = ins.query(userEntry.TABLE_NAME  ,
                projection , null ,null ,
                null,null,null ) ;
        String h ;

            z.moveToFirst();
            int index = z.getColumnIndex(userEntry.COULMN_UserName);
             h = z.getString(index);

             TextView s = (TextView)findViewById(R.id.sure);
        s.setText(h);
        */
    }
}
