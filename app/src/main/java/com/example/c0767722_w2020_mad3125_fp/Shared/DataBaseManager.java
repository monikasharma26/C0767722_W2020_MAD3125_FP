package com.example.c0767722_w2020_mad3125_fp.Shared;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.c0767722_w2020_mad3125_fp.Models.LoginDetails;

import java.util.ArrayList;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users";
    private static final int DATABASE_VERSION = 1;


    private static String tableName;
    private static String tableCreatorString;

    public DataBaseManager(Context context)
    {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        db.execSQL(tableCreatorString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if existed
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        //recreate the table
        onCreate(db);
    }

    

}