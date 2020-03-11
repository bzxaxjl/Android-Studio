package com.example.bzx.finalwork.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.bzx.finalwork.tabFragments.MainActivity;
import com.example.bzx.finalwork.R;
import com.example.bzx.finalwork.model._User;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by yc on 2018/2/7.
 */

public class WelcomeActivity extends AppCompatActivity {
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        // 默认初始化Bmob
        Bmob.initialize(this, "b736b518b877ba9618c827d4a1641574");

        // 判断是否存在当前用户，如果存在，则直接进入主界面，不用再次登录
        _User current_user = BmobUser.getCurrentUser(_User.class);
        if (current_user != null) {
            it = new Intent(this, MainActivity.class);
        } else {
            // 不存在当前用户，转向登录界面
            it = new Intent(this, LoginActivity.class);
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(it); //执行意图
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        };
        timer.schedule(task, 1000 * 2); //2秒后跳转
    }
}

