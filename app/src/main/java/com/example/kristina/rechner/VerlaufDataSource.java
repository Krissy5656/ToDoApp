package com.example.kristina.rechner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristina on 14.01.2016.
 */
public class VerlaufDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = new String[]{"ID", "ZAHL1", "OPERATOR", "ZAHL2"};

    public VerlaufDataSource(Context context) {
        this.dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        this.database = this.dbHelper.getWritableDatabase();            //um die Datenbank zu öffnen
    }

    public void close() {
        this.dbHelper.close();
    }                   //um die Datenbank zu schließen

    public Entry createEntry(int zahl1, String operator, int zahl2) {
        ContentValues values = new ContentValues();
        values.put("ZAHL1", Integer.valueOf(zahl1));        //darin werden die Daten gespeichert
        values.put("OPERATOR", operator);
        values.put("ZAHL2", Integer.valueOf(zahl2));
        long insertId = this.database.insert("VERLAUF", (String)null, values);      //speichert den Verlauf
        Cursor cursor = this.database.query("VERLAUF", this.allColumns, "ID = " + insertId, (String[])null, (String)null, (String)null, (String)null);
        cursor.moveToFirst();
        return this.cursorToEntry(cursor);
    }       // benutzt die Klasse Entry erstellt einen Eintrag

    protected List<Entry> getAllEntries() {             // um alle werte auszulesen
        new ArrayList();
        ArrayList EntriesList = new ArrayList();            //Liste voller Entrys
        Cursor cursor = this.database.query("VERLAUF", this.allColumns, (String)null, (String[])null, (String)null, (String)null, (String)null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0) {
            return EntriesList;
        } else {
            while(!cursor.isAfterLast()) {              //Cursor liest so lange aus bis zum letzten zeichen
                Entry entry = this.cursorToEntry(cursor);
                EntriesList.add(entry);
                cursor.moveToNext();
            }

            cursor.close();
            return EntriesList;
        }
    }

    private Entry cursorToEntry(Cursor cursor) {        // ein neues Entry wird erstellt in dem alle werte gespeichert werden
        Entry entry = new Entry();
        entry.setId(cursor.getLong(0));
        entry.setZahl1(cursor.getInt(1));
        entry.setOperator(cursor.getString(2));
        entry.setZahl2(cursor.getInt(3));
        return entry;
    }
}
