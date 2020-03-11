package com.example.a12785.activitylifecycletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DialogActivity extends BaseActivity {
private EditText editText;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Button button =(Button) findViewById(R.id.button4);
        editText =(EditText)findViewById(R.id.EditText);
        progressBar =(ProgressBar) findViewById(R.id.ProgressBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText =editText.getText().toString();
                Toast.makeText(DialogActivity.this,"你输入了"+inputText,
                        Toast.LENGTH_SHORT).show();
                if (progressBar.getVisibility()==View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility(View.GONE);
                }
                int progress =progressBar.getProgress();
                progress =progress+10;
                progressBar.setProgress(progress);

            }
        });
    }
}
