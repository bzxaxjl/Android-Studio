package com.example.a12785.activitytext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//右上角的菜单栏
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//右上角的点击菜单提示
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "预约垃圾佬成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "预约小姜姜成功", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);//1.首先在setContentView()之前执行，用于告诉Window页面切换需要使用动画
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Button button3 = (Button) findViewById(R.id.button_3);
        Button button4 = (Button) findViewById(R.id.button_4);
        Button button5 = (Button) findViewById(R.id.Button_5);
        Button button6 = (Button) findViewById(R.id.Button_qiangtui);
        Button button7 = (Button) findViewById(R.id.Button_6);
        Button button8 = (Button) findViewById(R.id.Button_7);
        Button button9 = (Button) findViewById(R.id.Button_8);


        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);//广播申明



        button1.setOnClickListener(new View.OnClickListener() {//单纯的文字显示i
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);
                Toast.makeText(FirstActivity.this, "交易成功，点击右上角选择gay佬",
                        Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//垃圾佬
                Intent intent=new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {//姜瑞康
            @Override

            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this, ThirdActivity.class);
                startActivity(intent);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {//点击进入新世纪嗯
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.activitytext.ACTION_START");
                startActivity(intent);
                Toast.makeText(FirstActivity.this, "你点击了我",
                        Toast.LENGTH_SHORT).show();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {//空的按钮，还没有活动，ListViewActivity中没有任何代码，空白页
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,ListViewActivity.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {//强制退出“调戏开发人员”
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("FORCE_OFFLINE");
                sendBroadcast(intent);//发送指定广播

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {//课堂测试按钮，毛都没有
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(FirstActivity.this,ClassTest.class);
                startActivity(intent);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,Main2Activity.class);
                startActivity(intent);//打开读取系统联系人的界面//
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,Class320Activity.class);
                startActivity(intent);//打开课堂测试2.0
            }
        });

    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);//注销动态注册
    }

    class  NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();//网络是否可用的变量
            if (networkInfo!= null && networkInfo.isAvailable()){//判断网络是否可用，
                Toast.makeText(context, "网络处于可用状态", Toast.LENGTH_SHORT).show();//网络可用时的提醒
            }else{
                Toast.makeText(context,"您当前处于断网环境",Toast.LENGTH_SHORT).show();//网络不可用的提醒
            }//此处需要权限提示,调用系统网络的监控，在manifests中进行相应的权限申请
         //网络显示监控
        }
    }

}
