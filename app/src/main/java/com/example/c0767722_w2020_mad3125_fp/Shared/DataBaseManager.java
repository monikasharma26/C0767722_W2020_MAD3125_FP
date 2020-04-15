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
    public void dbInitialize(String tableName, String tableCreatorString)
    {
        this.tableName=tableName;
        this.tableCreatorString=tableCreatorString;
    }


    public boolean addRecord  (ContentValues values) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        // Insert the row
        long nr= db.insert(tableName, null, values);
        db.close();
        return nr> -1;
    }

    public ArrayList<String> ShowLoginDetails() throws Exception {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tableName , null);
        LoginDetails loginDetails = new LoginDetails(); //create a new flower object
        ArrayList<String> timedata=new ArrayList();
        if (cursor != null) {
            // move cursor to first row
            if (cursor.moveToFirst()) {
                do {
                    String time = cursor.getString(cursor.getColumnIndex("loginTime"));
                    timedata.add(time);
                } while (cursor.moveToNext());
            }


        } else
            loginDetails = null;


        db.close();
        return timedata;

    }



}