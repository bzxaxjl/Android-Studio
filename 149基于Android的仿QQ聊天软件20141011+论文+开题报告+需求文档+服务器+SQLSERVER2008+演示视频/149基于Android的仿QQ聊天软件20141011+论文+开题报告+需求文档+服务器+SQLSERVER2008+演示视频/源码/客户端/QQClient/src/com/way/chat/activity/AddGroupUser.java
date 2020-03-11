package com.way.chat.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.way.service.GroupChatService;
import com.way.util.JsonUtil;
import com.way.util.Web;

public class AddGroupUser extends Activity {
	private Button to_back;
	private ListView listView;
	private String qqid;
	private Handler handler;
	private Dialog dialog;
	private int state = 0;
	private List<String> allId = new ArrayList<String>();
	private String chatname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addgroupuser);
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
					Toast.makeText(AddGroupUser.this, "网络错误，请检查网络是否连接", 0)
							.show();
					break;
				case 3:
					Map<String, String> map2 = (Map<String, String>) msg.obj;
					if (TextUtils.isEmpty(map2.get("success"))) {
						Toast.makeText(AddGroupUser.this, "添加失败", 0).show();
					}
					if (!TextUtils.isEmpty(map2.get("success"))) {
						if (map2.get("success").equals("success")) {
							Toast.makeText(AddGroupUser.this, "添加成功", 0).show();
						}
					}
					if (!TextUtils.isEmpty(map2.get("error"))) {
						Toast.makeText(AddGroupUser.this, map2.get("error"), 0)
								.show();
					}
					break;
				}
			}
		};
		qqid = getIntent().getStringExtra("id");
		if (TextUtils.isEmpty(qqid)) {
			Toast.makeText(AddGroupUser.this, "网络错误，请检查网络是否连接", 0).show();
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

	private void addGroupUser(final String alluser, final String chatname) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Map<String, String> map = GroupChatService.getService(
						Web.addGroupUser, "&id=" + qqid + "&alluser=" + alluser
								+ "&chatname=" + chatname);
				if (map != null) {
					Message msg = new Message();
					msg.what = 3;
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

	private void select(final String id) {
		// 将布局文件转化成view对象
		LayoutInflater inflaterDl = LayoutInflater.from(this);
		LinearLayout layout = (LinearLayout) inflaterDl.inflate(
				R.layout.tuichu_lmsj_dialog, null);
		final Dialog dialog = new AlertDialog.Builder(this).create();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		dialog.show();
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
		params.width = width * 4 / 5;
		dialog.getWindow().setAttributes(params);
		dialog.getWindow().setContentView(layout);
		TextView update_count = (TextView) layout
				.findViewById(R.id.update_count);
		TextView yihou_update = (TextView) layout
				.findViewById(R.id.yihou_update);
		TextView now_update = (TextView) layout.findViewById(R.id.now_update);
		update_count.setText("确定要添加" + id + "？");
		yihou_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		now_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				allId.add(id);
				dialog.dismiss();
				jixu();

			}
		});
		dialog.setCanceledOnTouchOutside(false);

	}

	private void jixu() {
		// 将布局文件转化成view对象
		LayoutInflater inflaterDl = LayoutInflater.from(this);
		LinearLayout layout = (LinearLayout) inflaterDl.inflate(
				R.layout.tuichu_lmsj_dialog, null);
		final Dialog dialog = new AlertDialog.Builder(this).create();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		dialog.show();
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
		params.width = width * 4 / 5;
		dialog.getWindow().setAttributes(params);
		dialog.getWindow().setContentView(layout);
		TextView update_count = (TextView) layout
				.findViewById(R.id.update_count);
		TextView yihou_update = (TextView) layout
				.findViewById(R.id.yihou_update);
		TextView now_update = (TextView) layout.findViewById(R.id.now_update);
		update_count.setText("是否继续添加？");
		yihou_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				String alluser = qqid;
				for (int i = 0; i < allId.size(); i++) {
					alluser = alluser + "," + allId.get(i);
				}
				addGroupUser(alluser, chatname);
			}
		});
		now_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		dialog.setCanceledOnTouchOutside(false);

	}

	private void addGroup() {
		// 将布局文件转化成view对象
		LayoutInflater inflaterDl = LayoutInflater.from(this);
		LinearLayout layout = (LinearLayout) inflaterDl.inflate(
				R.layout.staff_manager_add_group_station_dialog, null);
		dialog = new Dialog(this, R.style.CustomDialogStyle);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		dialog.show();
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
		params.width = width * 4 / 5;
		dialog.getWindow().setAttributes(params);
		dialog.getWindow().setContentView(layout);
		Button add_quxiao = (Button) layout
				.findViewById(R.id.staff_manager_update_quxiao);
		Button add_submit = (Button) layout
				.findViewById(R.id.staff_manager_update_submit);
		final EditText station = (EditText) layout
				.findViewById(R.id.staff_manager_add_station_name);
		add_quxiao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});
		add_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				if (TextUtils.isEmpty(station.getText().toString().trim())) {
					Toast.makeText(AddGroupUser.this, "请输入群组名", 0).show();
					return;
				}
				try {
					chatname = URLEncoder.encode(station.getText().toString().trim() ,"utf-8");
					state = 1;
					jixu();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// tishi();

			}
		});
		dialog.setCanceledOnTouchOutside(false);
	}

	class MyAdapter extends BaseAdapter {
		private List<Map<String, String>> list;
		private int[] imgs = { R.drawable.icon, R.drawable.f1, R.drawable.f2,
				R.drawable.f3, R.drawable.f4, R.drawable.f5, R.drawable.f6,
				R.drawable.f7, R.drawable.f8, R.drawable.f9 };// 头像资源数组
		private LayoutInflater inflater;

		MyAdapter(List<Map<String, String>> list) {
			this.list = list;
			inflater = LayoutInflater.from(AddGroupUser.this);
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
					if (state == 0) {
						addGroup();
					} else {
						select(list.get(arg0).get("id"));
					}
					// TODO Auto-generated method stub
					/*
					 * User user=new User();
					 * user.setId(Integer.parseInt(list.get(arg0).get("id")));
					 * user.setName(list.get(arg0).get("name"));
					 * user.setGroup(Integer
					 * .parseInt(list.get(arg0).get("group")));
					 * user.setImg(Integer.parseInt(list.get(arg0).get("img")));
					 * user
					 * .setIsOnline(Integer.parseInt(list.get(arg0).get("isOnline"
					 * ))); addOneUser(user);
					 */
				}
			});
			return view;
		}

	}
}
