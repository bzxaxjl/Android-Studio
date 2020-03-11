package com.example.a12785.activitylifecycletest;

import android.app.AlertDialog;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends BaseActivity {
    public  static final String TAG ="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreat");
        setContentView(R.layout.activity_main);
        Button startNormalActivity =(Button)findViewById(R.id.button);
        Button startDialogActivity =(Button)findViewById(R.id.button2);
        Button button1 =(Button)findViewById(R.id.buttonList);
        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog =new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("干等一会儿？");
                progressDialog.setMessage("emmmmmmmmm");
                progressDialog.setCancelable(true);
                progressDialog.show();
                Intent intent =new Intent(MainActivity.this,NormalActivity.class);
                startActivity(intent);

            }
        });
        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog =new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("警告");
                dialog.setMessage("确认打开这个活动吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("是的，别墨迹", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent =new Intent(MainActivity.this, DialogActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("点错了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected  void onStart(){
        super.onStart();
        Log.d(TAG,"onStart");
    }
    @Override
    protected  void onResume(){
        super.onResume();
        Log.d(TAG,"onResume");
    }
    @Override
    protected  void onPause(){
        super.onPause();
        Log.d(TAG,"onPause");
    }
    @Override
    protected  void onStop(){
        super.onStop();
        Log.d(TAG,"onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart");
    }
}
