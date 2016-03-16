package com.example.kristina.todo2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Timo on 16.03.2016.
 */
public class MySQLHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "database.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLMN_NAME = "name";
    public static final String COLMN_PASSWORD = "password";

    public static final String SQL_CREATE =
            "create table " + TABLE_USER + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLMN_NAME + " text not null, " +
                    COLMN_PASSWORD + " text not null);";


    public MySQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
