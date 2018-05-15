package com.example.android.socialnetwork.packagefordb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;

/**
 * Created by Martin on 5/3/2018.
 */

public class UserHelper extends SQLiteOpenHelper {


    /**
     * Created by Martin on 5/2/2018.
     */

        private static final String DATABASE_NAME = "user.db";

        private static final int DATABASE_VERSION = 1 ;

        public UserHelper(Context c){
            super(c,DATABASE_NAME ,null , DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //Create table , here we need to import contract class
            String SQL_CREATE_USER_TABLE =
                    "CREATE TABLE "+ userEntry.TABLE_NAME + "(" +
                            userEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                            userEntry.COULMN_UserName + " TEXT ,"  // UNIQUE should be added
                            +userEntry.COULMN_number_friends + " INTEGER ,"
                            + userEntry.COULMN_friends + " TEXT , " +
                            userEntry.COULMN_Ids_liked_posts + " TEXT ," +
                            userEntry.COULMN_password + " TEXT  "+
                            ")"  + ";"  ;
            // pass string to excuete function //
            sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

        public boolean insertUser(String username,int noOfFriends , String friends , String password)
        {
            SQLiteDatabase userdb = this.getWritableDatabase();
            ContentValues contentvalues = new ContentValues();
            contentvalues.put(userEntry.COULMN_UserName,username);
            contentvalues.put(userEntry.COULMN_number_friends,noOfFriends);
            contentvalues.put(userEntry.COULMN_friends,friends);
            contentvalues.put(userEntry.COULMN_password,password);
            long result = userdb.insert(userEntry.TABLE_NAME,null,contentvalues);
            if(result == -1) return false;
            else return true;
        }
    }

