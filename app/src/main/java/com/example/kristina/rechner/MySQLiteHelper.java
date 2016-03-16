package com.example.kristina.rechner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kristina on 14.01.2016.
 */
public class MySQLiteHelper  extends SQLiteOpenHelper{              //DATENbANK erstellen

    private static final String DATABASE_NAME = "verlaufRechnungen.db";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_CREATE_VERLAUF = ""           //TABELLE wird erstellt
            +"create table VERLAUF("
            +"  ID integer primary key autoincrement,"
            +   "ZAHL1 int,"
            +   "OPERATOR text,"
            +   "ZAHL2 int)";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(TABLE_CREATE_VERLAUF);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){           // fuktion falls die Datenbank upgegradet werden muss
        Log.w(MySQLiteHelper.class.getName(),
            "Upgrading database from Version" + oldVersion + "to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS SCANITEM");
        onCreate(db);
    }
}
