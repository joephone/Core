package com.transcendence.core.publicModule.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.transcendence.core.publicModule.db.bean.BrowseRecords;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ravi on 15/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "wan_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(BrowseRecords.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + BrowseRecords.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(String title,String link) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(BrowseRecords.COLUMN_TITLE, title);

        values.put(BrowseRecords.COLUMN_LINK, link);
        // insert row
        long id = db.insert(BrowseRecords.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public BrowseRecords getRecord(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(BrowseRecords.TABLE_NAME,
                new String[]{BrowseRecords.COLUMN_ID, BrowseRecords.COLUMN_TITLE, BrowseRecords.COLUMN_LINK,BrowseRecords.COLUMN_TIMESTAMP},
                BrowseRecords.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        BrowseRecords bean = new BrowseRecords(
                cursor.getInt(cursor.getColumnIndex(BrowseRecords.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(BrowseRecords.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(BrowseRecords.COLUMN_LINK)),
                cursor.getString(cursor.getColumnIndex(BrowseRecords.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return bean;
    }

    public List<BrowseRecords> getAllRecords() {
        List<BrowseRecords> list = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + BrowseRecords.TABLE_NAME + " ORDER BY " +
                BrowseRecords.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BrowseRecords bean = new BrowseRecords();
                bean.setId(cursor.getInt(cursor.getColumnIndex(BrowseRecords.COLUMN_ID)));
                bean.setTitle(cursor.getString(cursor.getColumnIndex(BrowseRecords.COLUMN_TITLE)));
                bean.setLink(cursor.getString(cursor.getColumnIndex(BrowseRecords.COLUMN_LINK)));
                bean.setTimestamp(cursor.getString(cursor.getColumnIndex(BrowseRecords.COLUMN_TIMESTAMP)));

                list.add(bean);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return list;
    }

    public int getRecordsCount() {
        String countQuery = "SELECT  * FROM " + BrowseRecords.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateRecord(BrowseRecords bean) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BrowseRecords.COLUMN_TITLE, bean.getTitle());
        values.put(BrowseRecords.COLUMN_LINK, bean.getLink());

        // updating row
        return db.update(BrowseRecords.TABLE_NAME, values, BrowseRecords.COLUMN_ID + " = ?",
                new String[]{String.valueOf(bean.getId())});
    }

    public int deleteNote(BrowseRecords bean) {
        SQLiteDatabase db = this.getWritableDatabase();
        int flag = db.delete(BrowseRecords.TABLE_NAME, BrowseRecords.COLUMN_ID + " = ?",
                new String[]{String.valueOf(bean.getId())});
        db.close();
        return flag;
    }
}
