package com.example.deutschea1alla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;


public class DbLocation {
    DbLocationHelper myhelper;
    private List bookTitles;

    public DbLocation(Context context)
    {
        myhelper = new DbLocationHelper(context);
    }

    public void updateLocation(String lang) {
        myhelper.editLocation(lang);
    }

    static class DbLocationHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "location";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="id";     // Column I (Primary Key)
        private static final String lang = "lang";    //Column II

        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ lang +" VARCHAR(255));";
        //private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public DbLocationHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                //db.execSQL(DROP_TABLE);
                //onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        public void editLocation(String lang){
            SQLiteDatabase db = this.getWritableDatabase();
            String strSQL = "UPDATE "+TABLE_NAME+" SET lang = '"+lang+"' WHERE id = 1";
            db.execSQL(strSQL);
        }

    }

    public long addFirstLocation()
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbLocationHelper.lang, "ua");
        long id = dbb.insert(DbLocation.DbLocationHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public void ubdateLocation(String lang)
    {
        myhelper.editLocation(lang);
    }

    public String getLocation(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String Query = "SELECT * FROM "+myhelper.TABLE_NAME+" WHERE id=1";
        String cursData = "";

        try
        {
            Cursor cursor = db.rawQuery(Query, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        cursData = cursor.getString(cursor.getColumnIndex("lang"));
                    } while (cursor.moveToNext());
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return cursData;
    }

    public void emptyTable(String tableName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
    }

    public void createTable()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        myhelper.onCreate(db);
    }

    public int checkTable(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String Query = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+ DbLocation.DbLocationHelper.TABLE_NAME+"'";
        int name = 0;
        try
        {
            Cursor c = null;
            c = db.rawQuery(Query, null);
            c.moveToFirst();
            name = c.getCount();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return name;
    }

}