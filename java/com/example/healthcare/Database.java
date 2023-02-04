package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users(phone text,email text,password text)";
        sqLiteDatabase.execSQL(qry1);

        //String qry2 = "create table orderplace(fullname text,address text,contact text, date text, time text, amount text)";
        //sqLiteDatabase.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
    public void register(String phone, String email,String password){
        ContentValues cv = new ContentValues();
        cv.put("phone",phone);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();


    }
    public int login(String email,String password){
        int result=0;
        String str[]=new String[2];
        str[0] =email;
        str[1]=password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where email=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;

    }

    //public int checkAppointmentExists(String fullname,String address,String contact,String date,String time){
        //int result=0;
        //String str[] = new String[6];

        //str[0]=fullname;
        //st/r[1]=address;
        //str[2]=contact;
        //str[3]=date;
        //str[4]=time;
        //SQLiteDatabase db = getReadableDatabase();
        //Cursor c = db.rawQuery("select * from orderplace where fullname = ? and address = ? and contact = ? and date = ? and time = ?",str);
        //if(c.moveToFirst()){
          //  result=1;

        //}

        //return result;



    //}

    //public void addOrder(String fullname,String address,String contact,String date,String time, String amount){
      //  ContentValues cv = new ContentValues();
        //cv.put("fullname",fullname);
        //cv.put("address",address);
        //cv.put("contact",contact);
        //cv.put("date",date);
        //cv.put("time",time);
        //cv.put("amount",amount);
        //SQLiteDatabase db = getWritableDatabase();
        //db.insert("orderplace",null,cv);
        //db.close();

    //}

}
