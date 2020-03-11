package cn.trunch.a20180402simpleframe1;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
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

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null)
            actionBar.hide();

        initObject();
        setCliked();
        setFragment(213245);//最先显示的


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
    private void setCliked(){
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(1);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(2);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(3);
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(4);
            }
        });
//        imageView5.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
//                dialog.setTitle("警告");
//                dialog.setMessage("确认打开这个活动吗？");
//                dialog.setCancelable(false);
//                AlertDialog.Builder builder = dialog.setPositiveButton("是的，别墨迹", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(Dialog Interfacedialog, int which) {
//                        Intent intent = new Intent(MainActivity.this, DialogActivity.class);
//                        startActivity(intent);
//                    }
//                });
//                dialog.setNegativeButton("点错了",new DialogInterface.OnClickListener(){
//                    @Override
//                            public void onClick(DialogInterface dialog,int which){
//
//                    }
//                });
//                dialog.show();
//
//            }
//        });
    }
    private void setFragment(int i){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        //所有fragment 都隐藏，然后下面点击显示
        setDefault();
        switch (i){
            case 1:
                imageView1.setSelected(true);
                textView1.setSelected(true);
                headerName.setText("首页");
                fragmentTransaction.show(fragmentFirst);
                break;
            case 2:
                imageView2.setSelected(true);
                textView2.setSelected(true);
                headerName.setText("市场");
                fragmentTransaction.show(fragmentSecond);
                break;
            case 3:
                imageView3.setSelected(true);
                textView3.setSelected(true);
                headerName.setText("消息");
                fragmentTransaction.show(fragmentThird);
                break;
            case 4:
                imageView4.setSelected(true);
                textView4.setSelected(true);
                headerName.setText("个人");
                fragmentTransaction.show(fragmentFourth);
                break;
            default:
                addAllFragment(fragmentTransaction);
                hideAllFragment(fragmentTransaction);
                imageView1.setSelected(true);
                textView1.setSelected(true);
                headerName.setText("首页");
                fragmentTransaction.show(fragmentFirst);
                break;
        }
        fragmentTransaction.commit();
    }
    private void addAllFragment(FragmentTransaction fragmentTransaction){
        fragmentTransaction.add(R.id.main_body,fragmentFirst);
        fragmentTransaction.add(R.id.main_body,fragmentSecond);
        fragmentTransaction.add(R.id.main_body,fragmentThird);
        fragmentTransaction.add(R.id.main_body,fragmentFourth);
    }
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        fragmentTransaction.hide(fragmentFirst);
        fragmentTransaction.hide(fragmentSecond);
        fragmentTransaction.hide(fragmentThird);
        fragmentTransaction.hide(fragmentFourth);

    }
    private void setDefault(){
        imageView1.setSelected(false);
        textView1.setSelected(false);
        imageView2.setSelected(false);
        textView2.setSelected(false);
        imageView3.setSelected(false);
        textView3.setSelected(false);
        imageView4.setSelected(false);
        textView4.setSelected(false);
    }

}
