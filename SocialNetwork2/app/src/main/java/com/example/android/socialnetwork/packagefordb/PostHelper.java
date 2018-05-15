package com.example.android.socialnetwork.packagefordb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.socialnetwork.packagefordb.usercontract.PostEntry;

/**
 * Created by Martin on 5/4/2018.
 */

public class PostHelper extends SQLiteOpenHelper {

    private static final String DatabaseName = "post.db";
    private static final int DatabaseVersion = 1 ;

    public PostHelper(Context c ){
        super(c,DatabaseName,null,DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_Create_post_table = "CREATE TABLE "+PostEntry.TABLE_NAME + "("
                +PostEntry.COLUMN_postid + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                PostEntry.COULMN_userid + " INTEGER , " +
                PostEntry.COLUMN_likes + " INTEGER , " +
                PostEntry.COULMN_POST + " TEXT "
                +");";
        sqLiteDatabase.execSQL(SQL_Create_post_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertPost(int userId,int likes,String body)
    {
        SQLiteDatabase postDb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PostEntry.COULMN_userid,userId);
        cv.put(PostEntry.COLUMN_likes,likes);
        cv.put(PostEntry.COULMN_POST,body);
        long result = postDb.insert(PostEntry.TABLE_NAME,null,cv);
        if(result == -1) return false;
        else return true;
    }
}
