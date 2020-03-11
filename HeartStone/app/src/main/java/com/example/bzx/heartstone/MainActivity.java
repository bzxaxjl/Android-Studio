package com.example.bzx.heartstone;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView;
        imageView =(ImageView) findViewById(R.id.imageView4);

        setContentView(R.layout.activity_main);
        //取消系统自带的标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://hs.fbigame.com");//设置访问的网站
                intent.setData(uri);
                startActivity(intent);


//        Button button1 = (Button) findViewById(R.id.button_1);
////        Button button2 = (Button) findViewById(R.id.button_2);
//
//
//        button1.setOnClickListener(new View.OnClickListener() {//单纯的文字显示i
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Uri uri = Uri.parse("http://hs.fbigame.com");//设置访问的网站
//                intent.setData(uri);
//                startActivity(intent);
//            }
//        });
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this, SecondActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
    }
}

//private WebView webView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        webView= (WebView) findViewById(R.id.webView);
//        //加载指定网页
//        webView.loadUrl("http://hs.fbigame.com");


    ////
//    public static final String EXIST = "exist";
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if (intent != null) {//判断其他Activity启动本Activity时传递来的intent是否为空
//            //获取intent中对应Tag的布尔值
//            boolean isExist = intent.getBooleanExtra(EXIST, false);
//            //如果为真则退出本Activity
//            if (isExist) {
//                this.finish();
//            }
//        }
//    }



