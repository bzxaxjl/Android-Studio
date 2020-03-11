package com.example.a12785.mycalculator;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private LinearLayout LinearLayout1;
    private LinearLayout LinearLayout2;
    private LinearLayout LinearLayout3;
    private LinearLayout LinearLayout4;
    private ImageView imageView;
    private TextView textView;
    TextView header_name;
    FrameLayout frameLayout;
    private TextView headerName;
    private FirstFragment fragmentFirst;
    private SecondFragment fragmentSecond;
    private ThirdFragment fragmentThird;
    private FourthFragment fragmentFourth;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }
    private void initObject(){
        headerName =(TextView)findViewById(R.id.header_name);
        fragmentFirst=new FirstFragment();
        fragmentSecond=new SecondFragment();
        fragmentThird=new ThirdFragment();
        fragmentFourth=new FourthFragment();
        linearLayout1=(LinearLayout) findViewById(R.id.first);
        linearLayout2=(LinearLayout)findViewById(R.id.second);
        linearLayout3=(LinearLayout)findViewById(R.id.third);
        linearLayout4=(LinearLayout)findViewById(R.id.fourth);
        imageView1=(ImageView)findViewById(R.id.first_img);
        imageView2=(ImageView)findViewById(R.id.second_img);
        imageView3=(ImageView)findViewById(R.id.third_img);
        imageView4=(ImageView)findViewById(R.id.fourth_img);
        textView1=(TextView)findViewById(R.id.first_text);
        textView2=(TextView)findViewById(R.id.second_text);
        textView3=(TextView)findViewById(R.id.third_text);
        textView4=(TextView)findViewById(R.id.fourth_text);
    }

//        Button button1 =(Button) findViewById(R.id.button_1);               //进入2阶计算的按钮
//        Button button2 =(Button) findViewById(R.id.button_2);               //进入3阶计算的按钮
//        Button button3 =(Button) findViewById(R.id.button_3);               //进入4阶计算的按钮
//        Button button4 =(Button) findViewById(R.id.button_4);               //进入5阶计算的按钮
//        Button button5 =(Button) findViewById(R.id.button_5);               //进入6阶计算的按钮
//        Button button6 =(Button) findViewById(R.id.button_6);               //进入7阶计算的按钮
//        Button button7 =(Button) findViewById(R.id.button_7);               //进入8阶计算的按钮
//
//        button1.setOnClickListener(new View.OnClickListener() {             //点击进入2阶计算式的方法
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(MainActivity.this,N2Activity.class);
//                startActivity(intent);
//            }
//        });
//        button2.setOnClickListener(new View.OnClickListener() {             //点击进入3阶计算式的方法
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(MainActivity.this,N3Activity.class);
//                startActivity(intent);
//            }
//        });
//        button3.setOnClickListener(new View.OnClickListener() {             //点击进入4阶计算式的方法
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(MainActivity.this,N2Activity.class);
//                startActivity(intent);
//            }
//        });
//        button4.setOnClickListener(new View.OnClickListener() {             //点击进入5阶计算式的方法
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(MainActivity.this,N2Activity.class);
//                startActivity(intent);
//            }
//        });
//        button5.setOnClickListener(new View.OnClickListener() {             //点击进入6阶计算式的方法
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(MainActivity.this,N2Activity.class);
//                startActivity(intent);
//            }
//        });
//        button6.setOnClickListener(new View.OnClickListener() {             //点击进入7阶计算式的方法
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(MainActivity.this,N2Activity.class);
//                startActivity(intent);
//            }
//        });
//        button7.setOnClickListener(new View.OnClickListener() {             //点击进入8阶计算式的方法
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(MainActivity.this,N2Activity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
    }
}
