package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database3 extends SQLiteOpenHelper {
    public Database3(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table booklab(fullname text,fees text, date text, time text)";
        sqLiteDatabase.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int checkBookExists(String fullname, String fees, String date, String time) {
        int result = 0;
        String str[] = new String[4];

        str[0] = fullname;
        str[1] = fees;
        str[2] = date;
        str[3] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from booklab where fullname = ? and fees = ?  and date = ? and time = ?", str);
        if (c.moveToFirst()) {
            result = 1;

        }

        return result;


    }

    public void addBook(String fullname, String fees, String date, String time) {
        ContentValues cv = new ContentValues();
        cv.put("fullname", fullname);
        cv.put("fees", fees);
        cv.put("date", date);
        cv.put("time", time);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("booklab", null, cv);
        db.close();

    }
}
