package com.example.a12785.hellonote;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;
    private Button textbutton, imgbutton, videobutton;//三个注册按钮
    private ListView LV;//ListView
    private Intent i;//点击时发生跳转的活动
    private MyAdapter adapter;//List的显示适配器
    private SQLiteDatabase dbReader;//读取数据库操作

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();
        addDB();
//        LV = (ListView) findViewById(R.id.List_item);
//        textbutton = (Button) findViewById(R.id.text);
//        imgbutton = (Button) findViewById(R.id.img);
//        videobutton = (Button) findViewById(R.id.video);
        notesDB = new NotesDB(this);//实例一个note
        dbReader = notesDB.getReadableDatabase();//读取数据库的内容
        initView();
//        textbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                textbutton =(Button) findViewById(R.id.text);
//                i.putExtra("flag", "1");
//                startActivity(i);
//            }
//        });
//        imgbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                imgbutton =(Button) findViewById(R.id.img);
//                i.putExtra("flag", "2");
//                startActivity(i);
//            }
//        });
//        videobutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                videobutton =(Button) findViewById(R.id.video);
//                i.putExtra("flag", "3");
//                startActivity(i);
//            }
//        });

    }
    public void initView() {
        LV = (ListView) findViewById(R.id.List_item);
        textbutton = (Button) findViewById(R.id.text);
        imgbutton = (Button) findViewById(R.id.img);
        videobutton = (Button) findViewById(R.id.video);
        i = new Intent(this, Add.class);

        textbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textbutton =(Button) findViewById(R.id.text);
                i.putExtra("flag", "1");
                startActivity(i);
            }
        });
        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbutton =(Button) findViewById(R.id.img);
                i.putExtra("flag", "2");
                startActivity(i);
            }
        });
        videobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videobutton =(Button) findViewById(R.id.video);
                i.putExtra("flag", "3");
                startActivity(i);
            }
        });
    }


    public void selectDB() {
        Cursor cursor = dbReader.query(NotesDB.TABLE_NAME, null, null, null, null, null, null);
        adapter = new MyAdapter(this, cursor);
        LV.setAdapter(adapter);
    }

    public void addDB() {
        ContentValues cv = new ContentValues();
        cv.put(NotesDB.CONTENT, "Hello");//添加内容列
        cv.put(NotesDB.TIME, getTime());//添加时间类
        dbWriter.insert(NotesDB.TABLE_NAME, null, cv);//插入数据库中
    }

    public String getTime() {//获取时间
        SimpleDateFormat format = new SimpleDateFormat("yyy年MM月dd日  HH:mm:ss");//时间格式并直接实例化
        Date curDate = new Date();//直接实例化data
        String str = format.format(curDate);//获取时间
        return str;//返回时间
    }


    public void onResume() {
        super.onResume();
        selectDB();
    }


}
//    public void onClick(View v){
//          i =new Intent(this,Add.class);
//        switch (v.getId()){
//            case R.id.text:
//            i.putExtra("flag","1");
//            startActivity(i);
//                break;
//            case R.id.img:
//                i.putExtra("flag","2");
//                startActivity(i);
//                break;
//            case R.id.video:
//                i.putExtra("flag","3");
//                startActivity(i);
//                break;
//        }
//
//    }

