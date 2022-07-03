package my.deutscheaalla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;


public class DbVersion {
    DbVersionHelper myhelper;
    private List bookTitles;

    public DbVersion(Context context)
    {
        myhelper = new DbVersionHelper(context);
    }

    public void updateVersion(String lang) {
        myhelper.editVersion(lang);
    }

    static class DbVersionHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "version";   // Table Name
        private static final int DATABASE_Version = 2;   // Database Version
        private static final String UID="id";     // Column I (Primary Key)
        private static final String version = "version";    //Column II

        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ version +" VARCHAR(255));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public DbVersionHelper(Context context) {
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
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        public void editVersion(String version){
            SQLiteDatabase db = this.getWritableDatabase();
            String strSQL = "UPDATE "+TABLE_NAME+" SET lang = '"+version+"' WHERE id = 1";
            db.execSQL(strSQL);
        }

    }

    public long addFirstVersion(String version)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbVersionHelper.version, version);
        long id = dbb.insert(DbVersion.DbVersionHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public void ubdateVersion(String version)
    {
        myhelper.editVersion(version);
    }

    public String getVersion(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String Query = "SELECT * FROM "+myhelper.TABLE_NAME+" WHERE id=1";
        String cursData = "";

        try
        {
            Cursor cursor = db.rawQuery(Query, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        cursData = cursor.getString(cursor.getColumnIndex("version"));
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
        String Query = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+ DbVersion.DbVersionHelper.TABLE_NAME+"'";
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