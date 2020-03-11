package com.example.a12785.activitytext;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FourthActivity extends AppCompatActivity {
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                if (requestCode==RESULT_OK){
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FourthActivity",returnedData);
                }
                break;
                default://返回值测试，失败，目前没搞明白为什么。
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Button   startDialogActivity=(Button) findViewById(R.id.Button_4);
        Button button2=(Button) findViewById(R.id.Button_5);

        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog =new AlertDialog.Builder(FourthActivity.this);
                dialog.setTitle("警告");
                dialog.setMessage("确认打开这个活动吗？");
                dialog.setCancelable(false);//false表示事件打开后不能通过返回键或者退出键退出，只能使用指定的按钮退出。
                dialog.setPositiveButton("是的，别墨迹", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent =new Intent(FourthActivity.this, DialogActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("点错了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();//dialog的显示

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FourthActivity.this,five5_Activity.class);//事件（界面）跳转
                startActivity(intent);
//                Toast.makeText(FourthActivity.this, "点这个才对嘛~", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
