package com.way.chat.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.way.service.GroupChatService;
import com.way.util.JsonUtil;
import com.way.util.Web;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class GroupChatList extends Activity{
	private static final int GETALLLIUYAN = 0;
	private static final int ERROR = 1;
	private ListView listview;
	private Handler handler;
	private String users;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.groupchatlist);
		listview=(ListView) findViewById(R.id.listview);
		users=getIntent().getStringExtra("users");
		
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case GETALLLIUYAN:
					Map<String, String> map=(Map<String, String>) msg.obj;
					List<Map<String, String>> list = null;
					try {
						String string = map.get("users") + "";
						list = new ArrayList<Map<String, String>>();
						JSONArray jsonArray = new JSONArray(string);
						for (int i = 0; i < jsonArray.length(); i++) {
							String s = jsonArray.get(i) + "";
							map = JsonUtil.getJosn(s);
							list.add(map);
						}
					} catch (Exception e) {
					}
					if (list.size()>0) {
					listview.setAdapter(new MyAdapter(list));
						}
					break;
				case ERROR:
					break;
				}
			}
		};
		if (TextUtils.isEmpty(users)) {
			
		}else {
			GetUsers();
		}
	}
	public void onclick(View view){
		finish();
	}
	private void GetUsers(){
		Map<String, String> map=GroupChatService.getService(Web.changyuan, "&userids="+users);
		if (map != null) {
			Message msg = new Message();
			msg.what = GETALLLIUYAN;
			msg.obj = map;
			handler.sendMessage(msg);
		} else {
			Message msg = new Message();
			msg.what = ERROR;
			handler.sendMessage(msg);
		}
	}
	class MyAdapter extends BaseAdapter{
		private List<Map<String, String>> list;
		private int[] imgs = { R.drawable.icon, R.drawable.f1, R.drawable.f2,
				R.drawable.f3, R.drawable.f4, R.drawable.f5, R.drawable.f6,
				R.drawable.f7, R.drawable.f8, R.drawable.f9 };// 头像资源数组
		private LayoutInflater inflater;
		MyAdapter(List<Map<String, String>> list){
			this.list=list;
			inflater=LayoutInflater.from(GroupChatList.this);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			if (view==null) {
				view=inflater.inflate(R.layout.item, null);
				
			}
			ImageView imageView_item=(ImageView) view.findViewById(R.id.imageView_item);
			TextView name_item=(TextView) view.findViewById(R.id.name_item);
		name_item.setText(list.get(arg0).get("name"));
		imageView_item.setImageResource(imgs[Integer.parseInt(list.get(arg0).get("img"))]);
			return view;
		}
		
	}
}
