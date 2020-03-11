package com.way.servlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.way.chat.common.bean.ChatPersonnel;
import com.way.chat.common.bean.Groupchat;
import com.way.chat.common.bean.User;
import com.way.chat.common.util.HttpUtil;
import com.way.chat.dao.ChatDao;
import com.way.chat.dao.impl.ChatDaoImpl;
import com.way.chat.dao.impl.UserDaoImpl;

public class Test {
	public static void main(String[] args) {
		ChatDao chatDao=new ChatDaoImpl();
		/*List<ChatPersonnel> chatPersonnels=new ArrayList<ChatPersonnel>();
		chatPersonnels=chatDao.getAllChatPersonnels("8");
		for(ChatPersonnel chatPersonnel:chatPersonnels){
			String[] useridMessage=chatPersonnel.getPersonnelalluser().split(",");
			List<User> users=new ArrayList<User>();
			for (int i = 0; i < useridMessage.length; i++) {
				User user=chatDao.getUsers(useridMessage[i]).get(0);
				users.add(user);
			}
			for (int i = 0; i < users.size(); i++) {
				System.out.println(users.get(i).getName());
			}
		}*/
		/*Groupchat groupchat=new Groupchat();
		groupchat.setChatcontent("呵呵，是的就是这样的");
		groupchat.setChatPersonnelid("3");
		groupchat.setImg(Integer.parseInt("1"));
		groupchat.setChatuserid("8");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dates=sdf.format(date);
		groupchat.setChattime(dates);
		int temp =chatDao.saveOneChat(groupchat);
		if (temp>0) {
			System.out.println("添加成功");
		}*/
	//	System.out.println(chatDao.getAllChat("3").size());
		/*List<Groupchat > groupchats=new ArrayList<Groupchat>();
		groupchats=chatDao.getAllChat("3");
		for (int i = 0; i < groupchats.size(); i++) {
			System.out.println(groupchats.get(i).getChatcontent());
		}*/
		Random random=new Random();
		int a=random.nextInt(9);
		System.out.println(a);
		User user=new User();
		user.setId(8);
		user.setPassword("e10adc3949ba59abbe56e057f20f883e");
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		List<User> users=userDaoImpl.login(user);
		for(User user2:users){
			System.out.println(user2.getName());
		}
	}
}
