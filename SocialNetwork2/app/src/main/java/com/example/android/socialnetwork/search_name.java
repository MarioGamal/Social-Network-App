package com.example.android.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class search_name extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name);
    }

    public static String name_to_search = "" ;

    public void search(View view){
        EditText name = (EditText)findViewById(R.id.name);
        String username = name.getText().toString();
        name_to_search = username ;
        Intent intent = new Intent(this, specific_names.class);
        startActivity(intent);
    }
}
