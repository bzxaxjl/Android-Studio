package com.example.a12785.activitytext;

import android.content.Intent;
import android.content.SharedPreferences;
import android.drm.DrmStore;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button Login;
    private CheckBox rememberPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//设置layout的标记
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        Login  =(Button) findViewById(R.id.button);
        accountEdit =(EditText) findViewById(R.id.EditText_1);
        passwordEdit =(EditText) findViewById(R.id.EditText_2);
        rememberPass =(CheckBox)findViewById(R.id.remember_pass);//记住密码的功能
        boolean isRemember =pref.getBoolean("remember_password",false);
        if (isRemember){
            String account =pref.getString("account","");
            String password =pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }//记住密码功能而新加入的代码
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account =accountEdit.getText().toString();
                String password =passwordEdit.getText().toString();
                if (account.equals("admin")&&password.equals("123456")){//设置密码和账户//
                    editor =pref.edit();
                    if (rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this,FirstActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "输入的账户或密码错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ActionBar actionBar =getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }
}
