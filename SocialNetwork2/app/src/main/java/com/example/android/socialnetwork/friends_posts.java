package com.example.android.socialnetwork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.socialnetwork.packagefordb.PostHelper;
import com.example.android.socialnetwork.packagefordb.UserHelper;
import com.example.android.socialnetwork.packagefordb.usercontract;
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;
import com.example.android.socialnetwork.packagefordb.usercontract.PostEntry;
import com.example.android.socialnetwork.post ;

import java.util.ArrayList;
import java.util.Collections;

public class friends_posts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_posts);
      // show_no();
        show_posts();
    }

    /* This is a function to check that info of user was transferred correctly
    public void show_no(){
        TextView t = (TextView)findViewById(R.id.show_number);
        t.setText(""+personal_page.this_user_id);
    }
    */



    public void show_posts(){

        String id_of_this_user = String.valueOf(signup.id) ;

        UserHelper userHelper = new UserHelper(this) ;
        SQLiteDatabase ins = userHelper.getReadableDatabase();
        String[] proj ={userEntry._ID , userEntry.COULMN_UserName , userEntry.COULMN_friends, userEntry.COULMN_number_friends
                ,userEntry.COULMN_password};
        String selection1 = userEntry._ID+"=?" ;
        String [] selargs1  =new String[]{id_of_this_user} ;

        Cursor c = ins.query(usercontract.userEntry.TABLE_NAME , proj , selection1,selargs1,null,null,null ) ;
        String friends = c.getString(c.getColumnIndex(userEntry.COULMN_friends)) ;


        // Parse friends strings to extract ids of user friends
        String []friends_list = friends.split(",");

        ArrayList<post>all_posts = new ArrayList<>(); // ArrayList which will be passed to ArrayAdapter

        for(String s : friends_list){
            // loop where friends_list[i] will be in s and so on
            int id_of_user_friend = Integer.parseInt(s) ;

           String [] selargs2 = new String[]{String.valueOf(id_of_user_friend)} ;
            // Then Search the post tablr for the id of that friend and get the post then push it into
            // the ArrayList all_posts then to arrayadapter then show on screen
            PostHelper postHelper = new PostHelper(this);
            SQLiteDatabase sqLiteDatabase = postHelper.getReadableDatabase() ;
            String[] projection ={PostEntry.COULMN_POST , PostEntry.COLUMN_likes , PostEntry.COULMN_userid , PostEntry.COLUMN_postid} ;
            String selection = PostEntry.COULMN_userid+"=?" ;
            String[] selectionargs = {s} ;
            Cursor pointer_to_post = sqLiteDatabase.query(PostEntry.TABLE_NAME , projection , selection , selectionargs ,
                    null,null,null) ;
            // Loop on all posts from that user
            pointer_to_post.moveToFirst();
            int d = pointer_to_post.getCount();
            int i = 0 ;
            while(i<d){
                String post_body = pointer_to_post.getString(pointer_to_post.getColumnIndex(PostEntry.COULMN_POST)) ;
                int id_user = pointer_to_post.getInt(pointer_to_post.getColumnIndex(PostEntry.COULMN_userid)) ;
                int post_no = pointer_to_post.getInt(pointer_to_post.getColumnIndex(PostEntry.COLUMN_postid)) ;
                int no_likes = pointer_to_post.getInt(pointer_to_post.getColumnIndex(PostEntry.COLUMN_likes)) ;
                post ThePost = new post(post_body , no_likes , post_no , id_user) ;
                all_posts.add(ThePost);
                i++;pointer_to_post.moveToNext();
            }

        }
        // By the end of this loop we will have added all posts of user friends to all_posts
        //Arrange posts from newest to oldest

        Collections.sort(all_posts , new PostArrange());

    }

}
