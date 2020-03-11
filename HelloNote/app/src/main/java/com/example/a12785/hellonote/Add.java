package com.example.a12785.hellonote;


import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Add extends Activity {
    private String val;
    private Button savebtn,deletebtn;
    private EditText e_edit;
    private ImageView c_img;
    private VideoView c_video;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);
        val =getIntent().getStringExtra("flag");
        savebtn=(Button) findViewById(R.id.save);//保存
        deletebtn =(Button) findViewById(R.id.delete);//取消
        e_edit =(EditText) findViewById(R.id.e_edit);//编辑栏
        c_img = (ImageView) findViewById(R.id.c_img);//图片
        c_video =(VideoView) findViewById(R.id.c_video);
        dbWriter = notesDB.getWritableDatabase();

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            addDB();//点击即可保存
            finish();//点击取消退出
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//点击取消退出
            }
        });
    }

    public void addDB(){//增加一个记事项目
        ContentValues cv = new ContentValues();
        cv.put(NotesDB.CONTENT,e_edit.getText().toString());
        cv.put(NotesDB.TIME,getTime());//给时间

        dbWriter.insert(NotesDB.TABLE_NAME,null,cv);//向数据库中加入新的数据
    }
    private String getTime(){//从系统中得到时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());//获得系统时间，在Note有出处注释
        String str =format.format(date);
        return str;//返回时间值
    }
}
