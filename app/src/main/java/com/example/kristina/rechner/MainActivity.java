package com.example.kristina.rechner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String sRechenmodus = "+";
    boolean mainisopen = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSource = new VerlaufDataSource(this);
    }

    private VerlaufDataSource dataSource;           //Erstellen einer neuen Datasource
    List<Entry> RechnungenList = new ArrayList<Entry>();
    //List<Entry> RechnungenList = new ArrayList<String>();



    public void ButtonClick(View view) {
        int zahl1;
        int zahl2;
        int Ergebnis = 0;
        EditText Feld1 = (EditText) findViewById(R.id.zahl1);
        EditText Feld2 = (EditText) findViewById(R.id.zahl2);
        EditText Feld3 = (EditText) findViewById(R.id.summe);
        if (Feld1.getText().toString().length() == 0) {
            return;
        }

        if (Feld2.getText().toString().length() == 0) {
            return;
        }

        zahl1 = Integer.parseInt(Feld1.getText().toString());
        zahl2 = Integer.parseInt(Feld2.getText().toString());

        if (sRechenmodus.equals("+") ){
            Ergebnis = zahl1 + zahl2;
        }

        if (sRechenmodus.equals("-") ){
            Ergebnis = zahl1 - zahl2;
        }

        if (sRechenmodus.equals("*") ){
            Ergebnis = zahl1 * zahl2;
        }

        if (sRechenmodus.equals("/") ){
            Ergebnis = zahl1 / zahl2;
        }



        Feld3.setText(String.valueOf(Ergebnis));

        //RechnungenList.add(Feld1.getText().toString()+ " " + sRechenmodus + " " +Feld2.getText().toString() + " = " + Feld3.getText().toString());
        // wird stattdessen  die Datenbank geschrieben

        try{
            dataSource.open();
            dataSource.createEntry(zahl1, sRechenmodus, zahl2);         //neues Entry wird erstellt
            dataSource.close();
        }
        catch (Exception ex){
            Toast.makeText(this,ex.toString(),Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        TextView TvRechenmodus = (TextView) findViewById(R.id.TvRechenmodus);

        if (item.toString().equals("Addieren")){

            TvRechenmodus.setText("Rechenmodus: Addieren");
            sRechenmodus = "+";

        }
        if (item.toString().equals("Subtrahieren")){

            TvRechenmodus.setText("Rechenmodus: Subtrahieren");
            sRechenmodus = "-";

        }
        if (item.toString().equals("Multiplizieren")){

            TvRechenmodus.setText("Rechenmodus: Multiplizieren");
            sRechenmodus = "*";

        }
        if (item.toString().equals("Dividieren")){

            TvRechenmodus.setText("Rechenmodus: Dividieren");
            sRechenmodus = "/";

        }

        if (item.toString().equals(R.string.Schliessen)){

            TvRechenmodus.setText(item.toString());
                    showDialog(10);


        }
       return true;
    }
    @Override
    protected Dialog onCreateDialog (int id){

        switch(id){
            case 10:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Applikation wird geschlossen");
                builder.setCancelable(true);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });

                builder.setNegativeButton("Nein, doch nicht", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Applikation wird fortgesetzt", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

    }
        return super.onCreateDialog(id);
    }


    public void VerlaufKlick (View view){
        setContentView(R.layout.activity_verlauf);
        mainisopen = false;

        RechnungenList.clear();
        try{
            dataSource.open();
            RechnungenList = dataSource.getAllEntries();        // strings werden umgewandelt in Entrys
            dataSource.close();
        }
        catch(Exception ex){
            Toast.makeText(this,ex.toString(), Toast.LENGTH_SHORT).show();
        }
        //ArrayAdapter<String> adapterVerlauf = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, RechnungenList);
        ArrayAdapter<Entry> adapterVerlauf = new ArrayAdapter<Entry>(MainActivity.this, android.R.layout.simple_list_item_1, RechnungenList);  //statt strings werden jetzt entrys genommen

        ListView lVerlauf = (ListView) findViewById(R.id.listView);
                lVerlauf.setAdapter(adapterVerlauf);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_BACK && mainisopen == false){
            mainisopen = true;
            setContentView(R.layout.activity_main);
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}
