package com.example.bzx.finalwork.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.bzx.finalwork.R;
import com.example.bzx.finalwork.adapter.Message_Books_SimpleAdapter;
import com.example.bzx.finalwork.model._User;
import com.example.bzx.finalwork.model.message_books;
import com.example.bzx.finalwork.model.user_followers;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yc on 2018/3/5.
 */

public class MessageBooksActivity extends AppCompatActivity {
    private _User current_user = BmobUser.getCurrentUser(_User.class);
    private ImageView back;
    private ListView listview;
    private List<Map<String,Object>> data = new ArrayList<>();
    private Message_Books_SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_books);

        findView();
        initialization();
        clickEvents();

    }

    public void findView() {
        back = (ImageView) findViewById(R.id.back);
        listview = (ListView) findViewById(R.id.listview);
        listview.addFooterView(new ViewStub(getApplicationContext()));
    }

    public void initialization() {

        BmobQuery<message_books> query = new BmobQuery<message_books>();
        query.order("-createdAt");
        query.include("focus_book");
        query.addWhereEqualTo("acceptor_id", current_user.getObjectId());
        query.findObjects(new FindListener<message_books>() {
            @Override
            public void done(List<message_books> list, BmobException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        Toast.makeText(getApplicationContext(), "当前未有消息提示~", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            Map<String,Object> temp = new LinkedHashMap<>();
                            temp.put("user_name", list.get(i).getUser_name());
                            temp.put("user_id", list.get(i).getUser_id());
                            temp.put("create_time", "于"+list.get(i).getCreatedAt().toString().split(" ")[0]);
                            temp.put("acceptor_id", list.get(i).getAcceptor_id());
                            temp.put("book_id", list.get(i).getFocus_book().getObjectId());
                            temp.put("book_name", "<"+list.get(i).getFocus_book().getName()+">");
                            temp.put("book_cover_image", list.get(i).getFocus_book().getImage().getFileUrl());
                            data.add(temp);
                        }
                        simpleAdapter = new Message_Books_SimpleAdapter(getApplicationContext(), data, R.layout.message_books_item,
                                new String[] {"user_name","user_id","create_time","acceptor_id","book_id","book_name","book_cover_image"}, new int[] {R.id.user_name, R.id.user_id, R.id.create_time,R.id.acceptor_id,R.id.book_id, R.id.book_name, R.id.book_cover_image});
                        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                            public boolean setViewValue(View view, Object data,
                                                        String textRepresentation) {
                                //判断是否为我们要处理的对象
                                if(view instanceof ImageView && data instanceof String){
                                    ImageView iv = (ImageView) view;
                                    ImageLoader.getInstance().displayImage((String) data, iv);
                                    return true;
                                }else
                                    return false;
                            }
                        });
                        listview.setAdapter(simpleAdapter);
                        //Toast.makeText(getApplicationContext(), "查询成功", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //Toast.makeText(getApplicationContext(), "查询失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        BmobQuery<user_followers> query2 = new BmobQuery<user_followers>();
        query2.addWhereEqualTo("user_id", current_user.getObjectId());
        query2.findObjects(new FindListener<user_followers>() {
            @Override
            public void done(List<user_followers> list, BmobException e) {
                if (e == null) {
                    if (list.get(0).getMessage_books_sum() != list.get(0).getMessage_books_read()) {
                        list.get(0).setMessage_books_read(list.get(0).getMessage_books_sum());
                        list.get(0).increment("notification_read",0);
                        list.get(0).increment("follower_sum",0);
                        list.get(0).increment("message_fans_sum",0);
                        list.get(0).increment("message_fans_read",0);
                        list.get(0).increment("message_sayings_read",0);
                        list.get(0).increment("message_sayings_sum",0);
                        list.get(0).update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                //if (e == null)
                                    //Toast.makeText(getApplicationContext(), "赋值成功", Toast.LENGTH_SHORT).show();
                                //else
                                    //Toast.makeText(getApplicationContext(), "赋值失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    //Toast.makeText(getApplicationContext(), "查询失败", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void clickEvents() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(2, i);
                finish();
            }
        });

    }


}
