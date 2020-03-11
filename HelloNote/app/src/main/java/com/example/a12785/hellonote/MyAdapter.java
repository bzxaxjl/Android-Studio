package com.example.a12785.hellonote;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ViewAnimator;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private Cursor cursor;
    private LinearLayout Layout;
    public MyAdapter(Context context,Cursor cursor){
        this.context =context;
        this.cursor =cursor;
    }
    public int getCount(){
        return cursor.getCount();
    }
    public Object getItem(int position){
        return cursor.getPosition();
    }
    public long getItemId(int position){
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        Layout =(LinearLayout)inflater.inflate(R.layout.cell,null);
        TextView contenttv =(TextView) Layout.findViewById(R.id.List_content);
        TextView timetv = (TextView) Layout.findViewById(R.id.List_time);
        ImageView imgiv =(ImageView) Layout.findViewById(R.id.List_img);
        ImageView videoiv =(ImageView) Layout.findViewById(R.id.List_video);
        cursor.moveToPosition(position);
        String content =cursor.getString(cursor.getColumnIndex("context"));
        String time = cursor.getString(cursor.getColumnIndex("time"));
        contenttv.setText(content);
        timetv.setText(time);
        return Layout;
    }

}
