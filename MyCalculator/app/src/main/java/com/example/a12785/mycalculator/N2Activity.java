package com.example.a12785.mycalculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class N2Activity extends AppCompatActivity {
    private  EditText editText11;
    private  EditText editText12;
    private  EditText editText21;
    private  EditText editText22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n2);
        editText11 = (EditText) findViewById(R.id.n11);
        editText12 = (EditText) findViewById(R.id.n12);
        editText21 = (EditText) findViewById(R.id.n21);
        editText22 = (EditText) findViewById(R.id.n22);
        Button button =(Button) findViewById(R.id.button_1);

        button.setOnClickListener(new View.OnClickListener() {//点击事件注册

            final TextView textView =(TextView) findViewById(R.id.text1);

            String s=editText11.getText().toString();
            int n11 = Integer.parseInt(s);
            int n12 = Integer.parseInt(editText12.getText().toString());
            int n21 = Integer.parseInt(editText21.getText().toString());
            int n22 = Integer.parseInt(editText22.getText().toString());
            int a[][] ={{n11,n12},{n21,n22}};
            final int  sum=Fun(2,a);
            public void onClick(View v)
            {
           textView.setText(sum);
            }
        });
    }


    public static int Fun(int n,int[][] a)
    {
        int b[][] = {{0,0},{0,0}};
        int i , j , sum = 0;                            //i，j为行与列，sum为行列式的值
        int x ,c ,p;                                    //用x加减，c,p为中间量
        if(n == 1)
            return a[0][0];
        for(i = 0;i < n; i++)                           //此处将余子式存入数组b中
        {
            for(c = 0;c < n-1; c++)
            {
                for(j = 0;j < n-1;j++)
                {
                    if (c < i){                         //借助c判断每行的移动方法
                        p = 0;                          //当p=0时，行列式向左移，去掉对应的第一列的数
                    }
                    else{                               //否则行列式左移后再上移
                        p = 1;
                    }
                    b[c][j] = a[c+p][j+1];
                }
            }
            if(i % 2 == 0){                             //i+j（此时j=0，故只考虑i）为偶数，加法
                x = 1;
            }
            else{                                       //i+j为奇数，减法
                x = (-1);
            }
            sum += a[i][0] * Fun(n - 1, b ) * x;     //计算行列式的值
        }
        return sum;
    }
}
