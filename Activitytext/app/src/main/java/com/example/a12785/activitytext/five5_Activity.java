package com.example.a12785.activitytext;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class five5_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five5_);
        Button button1 = (Button) findViewById(R.id.baidu);
        Button button2 = (Button) findViewById(R.id.bohao);
        Button button3 = (Button) findViewById(R.id.zhijiebohao);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data return", "Hello first");
                setResult(RESULT_OK, intent);
                finish();
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http;//www.baidu.com"));
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:13879188550"));
                startActivity(intent);//进入拨打电话的界面

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:13879188550"));
                    startActivity(intent);//直接进行手机拨号操作
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        });
//        button3.setOnClickListener(new View.OnClickListener() {//直接拨打电话的按钮事件
//            @Override
//            public void onClick(View v) {//if判断一下软件是否已经获得拨打权限
//                if (ContextCompat.checkSelfPermission(five5_Activity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_DENIED){
//                    ActivityCompat.requestPermissions(five5_Activity.this,new String[]{Manifest.permission.CALL_PHONE},1);
//                }else{//requestPermissions()在手机中调出是否授予软件拨号的权限的提示框，实际操作中并没有弹出。
//                    call(); //如果软件已经获得打电话的权限则直接执行call（）；从而拨打出电话
//                }
//            }
//        });
//    }
//    private void call(){//将拨打电话的事件封装进call（）函数中，在上面调用call（）即可；
//        try{
//            Intent intent =new Intent(Intent.ACTION_CALL);
//            intent.setData(Uri.parse("tel:13879188550"));
//            startActivity(intent);
//        } catch(SecurityException e){
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode,String [] permissions,int [] grantResults){
//        switch(requestCode){
//            case 1:
//                if (grantResults.length > 0 &&grantResults[0] ==PackageManager.PERMISSION_DENIED){
//                    call();
//                }else{
//                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
//                }
//                break;
//                default:
//        }
//
//    }
    }
}

