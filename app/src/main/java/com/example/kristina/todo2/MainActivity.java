package com.example.kristina.todo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void  buttonREG (View view){
        Intent myintent = new Intent(this, RegActivity.class);
                startActivity(myintent);
    }

    public void  buttonLOGIN (View view){
        Intent myintent2 = new Intent(this, ToDoListe.class);
        startActivity(myintent2);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }
}
