package com.example.android.socialnetwork.packagefordb;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.example.android.socialnetwork.packagefordb.usercontract;

/**
 * Created by Martin on 5/4/2018.
 */

public class postprovider extends ContentProvider {


    PostHelper postHelper ;

    private static final UriMatcher matcher2  = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // A query to return a cursor to the whole post table is mapped to the value 1
        matcher2.addURI(usercontract.content_authority,usercontract.path_to_post_table,1);

        // A query to return a cursor to specific row in post table is mapped to the value 2
        matcher2.addURI(usercontract.content_authority,usercontract.path_to_post_table+"/#",2);
    }

    @Override
    public boolean onCreate() {
        postHelper = new PostHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
