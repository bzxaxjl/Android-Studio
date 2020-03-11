package com.way.chat.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.way.service.GroupChatService;
import com.way.util.JsonUtil;
import com.way.util.Web;

import android.R.integer;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GroupChat extends Activity implements android.view.View.OnClickListener{
	private static final int GETALLLIUYAN = 1;
	private static final int ERROR = 2;
	private static final int GETALLLCOUNT = 3;
	private Button chat_back;
	private TextView chengyuan;
	private ListView listview;
	private EditText chat_editmessage;
	private Button chat_send;
	private String chatp;
	private Handler handler;
	private String counts = "0";
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 消息对象数组
	private ChatMsgViewAdapter mAdapter;// 消息视图的Adapter
	private int state=0;
	private String users;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group_chat);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case GETALLLCOUNT:
					Map<String, String> getAll = (Map<String, String>) msg.obj;
					if (counts.equals(getAll.get("count"))) {
						GetMessageCount();
					} else {
						counts=getAll.get("count");
						getAllMessage();
						
					}
					break;

				case ERROR:
					Toast.makeText(GroupChat.this, "网络错误", 0).show();
					break;

				case GETALLLIUYAN:
					Map<String, String> getAllcount = (Map<String, String>) msg.obj;

					List<Map<String, String>> list = null;
					try {
						String string = getAllcount.get("groupchats") + "";
						list = new ArrayList<Map<String, String>>();
						JSONArray jsonArray = new JSONArray(string);
						for (int i = 0; i < jsonArray.length(); i++) {
							String s = jsonArray.get(i) + "";
							getAllcount = JsonUtil.getJosn(s);
							list.add(getAllcount);
						}
					} catch (Exception e) {
					}
					if (list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							ChatMsgEntity chatMsgEntity = new ChatMsgEntity();
							if (list.get(i).get("chatuserid")
									.equals(Web.userid)) {
								chatMsgEntity.setMsgType(false);
							} else {
								chatMsgEntity.setMsgType(true);
							}
							chatMsgEntity.setImg(Integer.parseInt(list.get(i)
									.get("img")));
							chatMsgEntity.setMessage(list.get(i).get(
									"chatcontent"));
							chatMsgEntity.setDate(list.get(i).get("chattime"));
							mDataArrays.add(chatMsgEntity);
						}
						mAdapter = new ChatMsgViewAdapter(GroupChat.this,
								mDataArrays);
						listview.setAdapter(mAdapter);
						listview.setSelection(mAdapter.getCount() - 1);
						chat_editmessage.setText("");
					}

					GetMessageCount();
					break;
				}
			}
		};
		chat_back = (Button) findViewById(R.id.chat_back);
		chengyuan = (TextView) findViewById(R.id.chengyuan);
		listview = (ListView) findViewById(R.id.listview);
		chat_editmessage = (EditText) findViewById(R.id.chat_editmessage);
		chat_send = (Button) findViewById(R.id.chat_send);
		chat_back.setOnClickListener(this);
		chat_send.setOnClickListener(this);
		chatp = getIntent().getStringExtra("chatp");
		users=getIntent().getStringExtra("users");
		Toast.makeText(this, users, 0).show();
		chengyuan.setOnClickListener(this);
		if (TextUtils.isEmpty(chatp)) {

		} else {
			Toast.makeText(this, "加载", 0).show();
			getAllMessage();
		}
	}

	/**
	 * 获得所有的留言
	 */
	private void getAllMessage() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Map<String, String> map = GroupChatService.getService(Web.liuyan,
						"&chatp=" + chatp);
				if (state==0) {
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
			}
		}).start();
		
	}

	/**
	 * 获得留言的条数
	 */
	private void GetMessageCount() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Map<String, String> map = GroupChatService.getService(
						Web.liuyancountry, "&chatp=" + chatp);
				if (state==0) {
					if (map != null) {
						Message msg = new Message();
						msg.what = GETALLLCOUNT;
						msg.obj = map;
						
						handler.sendMessage(msg);
					} else {
						Message msg = new Message();
						msg.what = ERROR;
						handler.sendMessage(msg);
					}
				}
			}
		}).start();
	}

	/**
	 * 发送留言
	 * @throws UnsupportedEncodingException 
	 */
	private void saveMessage() throws UnsupportedEncodingException {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String string = null;
				try {
					string = URLEncoder.encode(chat_editmessage.getText().toString().trim() ,"utf-8");
					Map<String, String> map = GroupChatService.getService(Web.addLiuyan,
							"&content=" + string
									+ "&chatp=" + chatp + "&img=" + Web.img + "&userid="
									+ Web.userid);
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
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.chengyuan:
			Intent intent1=new Intent(this, GroupChatList.class);
			intent1.putExtra("users", users);
			startActivity(intent1);
			break;
		case R.id.chat_back:
			state=1000;
			finish();
			break;
		case R.id.chat_send:
			if (TextUtils.isEmpty(chat_editmessage.getText().toString().trim())) {
				
			}else {
				
				try {
					saveMessage();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			break;
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) { // 监控/拦截/屏蔽返回键
			state=1000;
			finish();
			//do something
			} else if (keyCode == KeyEvent.KEYCODE_MENU) {
				state=1000;
				finish();
			//do something
			} else if (keyCode == KeyEvent.KEYCODE_HOME) {
				state=1000;
				finish();
			//这里操作是没有返回结果的
			}
		
		return super.onKeyDown(keyCode, event);
	}
}
