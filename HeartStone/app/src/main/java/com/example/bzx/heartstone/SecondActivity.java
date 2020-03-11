package com.example.bzx.heartstone;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;



public class SecondActivity extends AppCompatActivity {
    private WebView webView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        //取消系统自带的标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        webView= (WebView) findViewById(R.id.mWebView);
        //加载指定网页
        webView.loadUrl("http://hs.fbigame.com");
        webView.loadUrl("javascript:$(\".btn-deck-bilder\").click()");
    }
}
