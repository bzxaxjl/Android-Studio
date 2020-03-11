package com.example.a12785.hellonote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDB extends SQLiteOpenHelper {
    public static final String TABLE_NAME ="notes";//表名字
    public static final String CONTENT ="content";
    public static final String PATH ="path";
    public static final String VIDEO ="video";
    public static final String ID ="_id";
    public static final String TIME="time";
    public static final String CREATE_Note="create TABLE_NAME ("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "CONTENT TEXT NOT NULL,"
            + "PATH TEXT NOT NULL,"
            + "VIDEO TEXT NOT NULL,"
            + "TIME TEXT NOT NULL)";
    public NotesDB(Context context){

        super(context,"nots",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
    db.execSQL(CREATE_Note);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}