package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database2 extends SQLiteOpenHelper {
    public Database2(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table orderplace(fullname text,address text,contact text, date text, time text, amount text)";
        sqLiteDatabase.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int checkAppointmentExists(String fullname,String address,String contact,String date,String time){
        int result=0;
        String str[] = new String[5];

        str[0]=fullname;
        str[1]=address;
        str[2]=contact;
        str[3]=date;
        str[4]=time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where fullname = ? and address = ? and contact = ? and date = ? and time = ?",str);
        if(c.moveToFirst()){
            result=1;

        }

        return result;



    }

    public void addOrder(String fullname,String address,String contact,String date,String time, String amount){
        ContentValues cv = new ContentValues();
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contact",contact);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",amount);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();

    }

}
