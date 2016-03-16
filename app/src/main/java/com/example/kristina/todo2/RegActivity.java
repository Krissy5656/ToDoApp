package com.example.kristina.todo2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {

    EditText editText3;
    EditText editText4;
    EditText editText5;
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);
        button2 = (Button)findViewById(R.id.button2);
    }


    public void adduser(String name, String password){

        ContentValues values = new ContentValues();
        values.put(MySQLHelper.COLMN_NAME, name);
        values.put(MySQLHelper.COLMN_PASSWORD, password);
        //SQLiteDatabase database = MySQLHelper.getWritableDatabase();      //getWritableDatabase() warum geht das nicht?
      //  database.insert(MySQLHelper.TABLE_USER, null, values);            // Schätze wenn die vorherige Zeile geht, geht auch das
       // database.close();
        Toast.makeText(this, "SUCCEDED!", Toast.LENGTH_SHORT).show();
    }

    public void button2OnClick(View view) {
        String username;
        String password;
        String passwordwdh;

        username = editText5.getText().toString();
        password = editText4.getText().toString();
        passwordwdh = editText3.getText().toString();

        if(editText5.getText().toString().length() == 0){
            editText5.setText("Missing Statemant");
            return;
        }

        if(password.length() > 4 && password.length() < 19 && password.matches(".*\\d+.*")){

            if (password.equals(passwordwdh)){
                adduser(username, password);
            }
        }
        else
            editText4.setText("Wrong Entry");

    }
}
