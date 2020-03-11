package com.example.a12785.activitytext;



        import android.content.DialogInterface;
        import android.graphics.Bitmap;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.webkit.WebChromeClient;
        import android.webkit.WebSettings;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ProgressBar;

        import java.util.Map;

public class Class320Activity extends AppCompatActivity {
    private EditText edit;
    private ImageView search;
    private WebView webView;
    private ImageView back;
    private ImageView forward;
    private ImageView home;
    private ImageView refresh;
    private ImageView exit;
    private ProgressBar progressBar;
    private Map<String ,String > titles;

    private WebSettings webSettings;
    private static final String URL="http://www.baidu.com";

    private    String myurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        webView.loadUrl(URL);

        webView.setWebViewClient(new WebViewClient()
        {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress>0&&newProgress<100)
                    progressBar.setProgress(newProgress);

                else if(newProgress==100)
                {
                    progressBar.setProgress(0);
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                edit.setText(title);
                super.onReceivedTitle(view, title);
            }
        });

        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    edit.setText(webView.getTitle());
                if(hasFocus)
                    edit.setText(webView.getUrl());
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myurl="http://"+edit.getText().toString();
                webView.loadUrl(myurl);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoBack())
                {
                    webView.goBack();
                }
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoForward())
                {
                    webView.goForward();
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(URL);
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Class320Activity.this)
                        .setTitle("浏览器")
                        .setMessage("确认退出？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create().show();


            }
        });



    }
    //初始化控件
    void init()
    {
        edit=findViewById(R.id.edit);
        search=findViewById(R.id.search);
//        webView=findViewById(R.id.webview);
        back=findViewById(R.id.go_back);
        forward=findViewById(R.id.go_forward);
        refresh=findViewById(R.id.refresh);
        home=findViewById(R.id.go_home);
        exit=findViewById(R.id.exit);
        //webview的相关设置类
        webSettings=webView.getSettings();
    }

    //重写返回方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&webView.canGoBack())
        {
            webView.goBack();
        }
        return true;
    }

    //webview的安全销毁
    @Override
    protected void onDestroy() {
        if(webView!=null)
        {
            webView.clearHistory();
            ((ViewGroup)webView.getParent()).removeView(webView);
            webView.destroy();
            webView=null;
        }
        super.onDestroy();
    }
}

