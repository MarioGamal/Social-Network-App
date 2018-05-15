package com.example.android.socialnetwork.packagefordb;

import android.content.UriMatcher;
import android.provider.BaseColumns;
import android.net.Uri ;

/**
 * Created by Martin on 5/2/2018.
 */

public final class usercontract {


    // for the content provider //

    public static final String content_authority = "com.example.android.Socialnetwork" ;

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + content_authority );
    public static final String path_to_user_table = "users_table";
    public static final String path_to_post_table = "posts";







    // ================================Users Table========================================= //

    public static final class userEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, path_to_user_table);



        public static final String TABLE_NAME = "users_table";

        public static final String _ID = "_ID";
        public static final String COULMN_UserName = "UserName";
        public static final String COULMN_friends = "friends";
        public static final String COULMN_number_friends = "number_friends";
        public static final String COULMN_password= "password";
        public static final String COULMN_Ids_liked_posts = "posts_liked" ;
    }


                //============================= Posts table================================ //

    public static final class PostEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, path_to_post_table) ;

        public static final String TABLE_NAME = "posts";

        public static final String COULMN_userid = "userid";
        public static final String COLUMN_postid="postid"; // id of the post related to the user (Primary key)
        public static final String COLUMN_likes="likes";
        public static final String COULMN_POST = "Body";
    }
}
