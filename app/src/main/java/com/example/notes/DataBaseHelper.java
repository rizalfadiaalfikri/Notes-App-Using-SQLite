package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "MyNotesList.db";
    public static int DATABASE_VERSION = 1;

    public static String TABLE_NAME = "myNotesList";
    public static String COLUMN_ID = "COLUMN_ID";
    public static String COLUMN_NAME = "COLUMN_NAME";
    public static String COLUMN_DESCRIPTION = "COLUMN_DESCRIPTION";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY = "CREATE TABLE " + TABLE_NAME + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNotes (String title,String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,title);
        cv.put(COLUMN_DESCRIPTION,description);

        db.insert(TABLE_NAME,null,cv);
    }

    public Cursor getAllData () {
        SQLiteDatabase db = this.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery(QUERY,null);
        }
        return cursor;
    }

    public void deleteAllData () {
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "DELETE FROM " + TABLE_NAME;

        db.execSQL(QUERY);
    }

    public void update(String id,String title,String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME,title);
        contentValues.put(COLUMN_DESCRIPTION,description);

        db.update(TABLE_NAME,contentValues,"COLUMN_ID=?",new String[]{id});
    }

    public void deleteSingleData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"COLUMN_ID=?",new String[]{id});
    }
}
