package com.example.kristina.todo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;





public class ToDoListe extends AppCompatActivity {
    private ActionMenuView amv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_liste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        amv = (ActionMenuView) toolbar.findViewById(R.id.amvID);

        amv.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Forward to our activity method onOptionsItemSelected
                return onOptionsItemSelected(item);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, amv.getMenu());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home: {
                Log.i("ToolbarActivity", "Home button was clicked");
                break;
            }
            case R.id.action_settings: {
                Log.i("ToolbarActivity", "Settings action was clicked!");
                break;
            }
            case R.id.action_save: {
                Log.i("ToolbarActivity", "Save action was clicked");
                break;
            }
            case R.id.action_delete: {
                Log.i("ToolbarActivity", "Delete action was clicked");
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
