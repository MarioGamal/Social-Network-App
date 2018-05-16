package com.example.android.socialnetwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.socialnetwork.packagefordb.UserHelper;

import com.example.android.socialnetwork.packagefordb.UserHelper;
import com.example.android.socialnetwork.packagefordb.usercontract.userEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Created by Martin on 5/15/2018.
 */

public class UserAdapterWithAddFriend extends ArrayAdapter<user> {

    public UserAdapterWithAddFriend(@NonNull Context context, ArrayList<user> userList){
        super(context, R.layout.view_for_user_adapter_with_add, userList);
        arrayList = userList ;
    }


    ArrayList<user>arrayList = new ArrayList<>();


    public boolean add_this_friend(int id ){

        if(id==personal_page.this_user_id){
            Toast.makeText(getContext() , "Thst's You !!" , Toast.LENGTH_LONG).show();
            return false;
        }

        //id is the id of the pressed user
        UserHelper userHelper = new UserHelper(getContext());
        SQLiteDatabase sql_read = userHelper.getReadableDatabase();

        String [] projection = {userEntry.COULMN_UserName , userEntry.COULMN_number_friends , userEntry.COULMN_friends} ;
        String selection = userEntry._ID+"=?";
        String[]selectionargs={String.valueOf(personal_page.this_user_id)};

        Cursor c = sql_read.query(userEntry.TABLE_NAME , projection , selection , selectionargs , null , null ,null) ;
        c.moveToFirst();
        String[] all_friends = c.getString(c.getColumnIndex(userEntry.COULMN_friends)).split(",");

        int no_friends_of_user = c.getInt(c.getColumnIndex(userEntry.COULMN_number_friends)) ;
        int no = c.getInt(c.getColumnIndex(userEntry.COULMN_number_friends));

        for(int i = 0 ; i < no ; i++)
        {
            if(all_friends[i].equals(String.valueOf(id)) )
            {
             Toast.makeText(getContext(),"Already a friend " ,Toast.LENGTH_LONG).show();
             return false;
            }
            if(String.valueOf(id).equals(String.valueOf(personal_page.this_user_id))){
                Toast.makeText(getContext(),"You are this user" ,Toast.LENGTH_LONG).show();
                return false;
            }
        }


        String friends = c.getString(c.getColumnIndex(userEntry.COULMN_friends)) ;

        ContentValues contentValues = new ContentValues() ;

        int no_friends = no_friends_of_user+1 ;

        contentValues.put(userEntry.COULMN_number_friends , no_friends); // update number of user friends

        String friends2 ;

        if(no_friends_of_user==0) friends2 = String.valueOf(id)+"," ;
        else {
            friends2=friends;
            friends2+=String.valueOf(id);
            friends2+=",";
        }
         contentValues.put(userEntry.COULMN_friends,friends2);


        SQLiteDatabase sql_update = userHelper.getWritableDatabase();
        int y =sql_update.update(userEntry.TABLE_NAME ,
                contentValues ,userEntry._ID+"="+String.valueOf(personal_page.this_user_id),null);
        if(y>0){
           Toast.makeText(getContext(),"Updated successfully !!"+no_friends_of_user+" /  "+friends2,Toast.LENGTH_LONG).show();
            /*
            Intent intent = new Intent(getContext() , specific_names.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
            */
        }

        //Add user to added friend too
        String selection2 = userEntry._ID+"=?";
        String [] selectionargs2 = {String.valueOf(id)};

        Cursor c2 = sql_read.query(userEntry.TABLE_NAME , projection , selection2  ,selectionargs2 ,null,null,null);

        c2.moveToFirst() ;

        int no_friends_of_added_user = c2.getInt(c.getColumnIndex(userEntry.COULMN_number_friends)) ;

        String friends_of_added_user = c2.getString(c.getColumnIndex(userEntry.COULMN_friends)) ;

        ContentValues contentValues2 = new ContentValues() ;

        contentValues2.put(userEntry.COULMN_number_friends , no_friends_of_added_user+1);

        String friends3;
        if(no_friends_of_added_user==0) friends3 = String.valueOf(personal_page.this_user_id)+",";
        else{
            friends3 = c2.getString(c2.getColumnIndex(userEntry.COULMN_friends)) ;
            friends3+=String.valueOf(personal_page.this_user_id) ;
            friends3+=",";
        }
        contentValues2.put(userEntry.COULMN_friends , friends3);

        int y2 =sql_update.update(userEntry.TABLE_NAME , contentValues2 ,userEntry._ID+"="+String.valueOf(id),null);
        if(y2>0){
            Toast.makeText(getContext() , "Another user added successfully " ,Toast.LENGTH_LONG).show();
        }
        return true ;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
       final View customView = myCustomInflater.inflate(R.layout.view_for_user_adapter_with_add, parent, false);

       final user singleUser = getItem(position);
        TextView itemText = (TextView) customView.findViewById(R.id.item_text);
       final TextView smallItemText = (TextView) customView.findViewById(R.id.item_small_text);
        ImageView buckysImage = (ImageView) customView.findViewById(R.id.my_profile_image);
        // dynamically update the text from the array
        itemText.setText(singleUser.name);
        smallItemText.setText("Has " + singleUser.no_of_friends + " friends");
        // using the same image every time
        buckysImage.setImageResource(R.drawable.icons8_account_96);


         final Button add = (Button) customView.findViewById(R.id.add);

        // Get all friends of the friend who searched

        UserHelper userHelper = new UserHelper(getContext());
        SQLiteDatabase sql = userHelper.getReadableDatabase();
        String[] projection = {userEntry.COULMN_UserName, userEntry.COULMN_number_friends, userEntry.COULMN_friends};
        String selection = userEntry._ID + "=?";
        String[] selectionargs = {String.valueOf(personal_page.this_user_id)};

        Cursor c = sql.query(userEntry.TABLE_NAME, projection, selection, selectionargs, null, null, null);

        c.moveToFirst();

        final int no_friends = c.getInt(c.getColumnIndex(userEntry.COULMN_number_friends));






            String[] all_friends = c.getString(c.getColumnIndex(userEntry.COULMN_friends)).split(",");

            boolean is_friend = false;


            for (int i = 0; i < no_friends; i++) {
                if ( all_friends[i].equals(String.valueOf(arrayList.get(position)._Id) ) ||
                        arrayList.get(position).get_Id()==personal_page.this_user_id  )
                {
                    add.setVisibility(View.INVISIBLE);
                    TextView textView = customView.findViewById(R.id.text) ;
                    textView.setText("Friend");
                }
            }



            final String print;





            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // position is its position within adapter and arraylist then we need to acces arraylist
                    // then check wheather he is a friend or not

                    //  Toast.makeText(getContext(), print + "   " + arrayList.get(position)._Id, Toast.LENGTH_LONG).show();
                    if (add_this_friend(arrayList.get(position)._Id))
                    {

                        add.setVisibility(View.INVISIBLE);

                        TextView textView = customView.findViewById(R.id.text);
                        textView.setText("Friend");

                        smallItemText.setText("Has " + String.valueOf(singleUser.no_of_friends+1) + " friends");
                    }
                } });


        return customView;
    }
}
