package com.way.chat.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.way.chat.common.bean.User;
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
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddUser extends Activity {
	private Button to_back;
	private ListView listView;
	private String qqid;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adduser);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
					Map<String, String> map = (Map<String, String>) msg.obj;
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
					listView.setAdapter(new MyAdapter(list));
					break;

				case 2:
					Toast.makeText(AddUser.this, "网络错误，请检查网络是否连接", 0).show();
					break;
				case 3:
					Map<String, String> map2=(Map<String, String>) msg.obj;
					if (TextUtils.isEmpty(map2.get("success"))) {
						Toast.makeText(AddUser.this, "添加失败", 0).show();
					}
					if (!TextUtils.isEmpty(map2.get("success"))) {
						if (map2.get("success").equals("success")) {
							Toast.makeText(AddUser.this, "添加成功", 0).show();
						}
					}
					if (!TextUtils.isEmpty(map2.get("error"))) {
						Toast.makeText(AddUser.this, map2.get("error"), 0).show();
					}
					break;
				}
			}
		};
		qqid = getIntent().getStringExtra("id");
		if (TextUtils.isEmpty(qqid)) {
			Toast.makeText(AddUser.this, "网络错误，请检查网络是否连接", 0).show();
		} else {
			getAllUser();
		}
		to_back = (Button) findViewById(R.id.to_back);
		listView = (ListView) findViewById(R.id.listview);
		to_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void getAllUser() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Map<String, String> map = GroupChatService.getService(
						Web.getalluser, "&id=" + qqid);
				if (map != null) {
					Message msg = new Message();
					msg.what = 1;
					msg.obj = map;
					handler.sendMessage(msg);
				} else {
					Message msg = new Message();
					msg.what = 2;
					handler.sendMessage(msg);
				}
			}
		}).start();
	}

	private void addOneUser(final User user) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Map<String, String> map = GroupChatService.getService(
						Web.addOneUser,
						"&id=" + qqid + "&qq=" + user.getId() + "&img="
								+ user.getImg() + "&name=" + user.getName()
								+ "&group=" + user.getGroup() + "&isOnline="
								+ user.getIsOnline());
				if (map!=null) {
					Message msg=new Message();
					msg.what=3;
					msg.obj=map;
					handler.sendMessage(msg);
				}else {
					Message msg = new Message();
					msg.what = 2;
					handler.sendMessage(msg);
				}
			}
		}).start();
	}

	class MyAdapter extends BaseAdapter {
		private List<Map<String, String>> list;
		private int[] imgs = { R.drawable.icon, R.drawable.f1, R.drawable.f2,
				R.drawable.f3, R.drawable.f4, R.drawable.f5, R.drawable.f6,
				R.drawable.f7, R.drawable.f8, R.drawable.f9 };// 头像资源数组
		private LayoutInflater inflater;

		MyAdapter(List<Map<String, String>> list) {
			this.list = list;
			inflater = LayoutInflater.from(AddUser.this);
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
		public View getView(final int arg0, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			if (view == null) {
				view = inflater.inflate(R.layout.item, null);

			}
			ImageView imageView_item = (ImageView) view
					.findViewById(R.id.imageView_item);
			TextView name_item = (TextView) view.findViewById(R.id.name_item);
			TextView id_item = (TextView) view.findViewById(R.id.id_item);
			id_item.setText(list.get(arg0).get("id"));
			name_item.setText(list.get(arg0).get("name"));
			imageView_item.setImageResource(imgs[Integer.parseInt(list
					.get(arg0).get("img"))]);
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub
					User user=new User();
					user.setId(Integer.parseInt(list.get(arg0).get("id")));
					try {
						user.setName(URLEncoder.encode(list.get(arg0).get("name") ,"utf-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.setGroup(Integer.parseInt(list.get(arg0).get("group")));
					user.setImg(Integer.parseInt(list.get(arg0).get("img")));
					user.setIsOnline(Integer.parseInt(list.get(arg0).get("isOnline")));
					addOneUser(user);
				}
			});
			return view;
		}

	}
}
