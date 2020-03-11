package com.way.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.way.chat.common.bean.ChatPersonnel;
import com.way.chat.common.bean.Groupchat;
import com.way.chat.common.bean.User;
import com.way.chat.common.util.HttpUtil;
import com.way.chat.dao.ChatDao;
import com.way.chat.dao.impl.ChatDaoImpl;

public class GroupChat extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding ("UTF-8");
		String type=request.getParameter("type");
		String userid=request.getParameter("userid");
		String content=request.getParameter("content");
		String img=request.getParameter("img");
		String chatp=request.getParameter("chatp");
		String userids=request.getParameter("userids");
		ChatDao chatDao=new ChatDaoImpl();
		if (type.equals("1")) {//添加
			Groupchat groupchat=new Groupchat();
			content=new String(content.getBytes("ISO8859_1"),"UTF-8");
			groupchat.setChatcontent(content);
			System.out.println(content);
			groupchat.setChatPersonnelid(chatp);
			groupchat.setImg(Integer.parseInt(img));
			groupchat.setChatuserid(userid);
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dates=sdf.format(date);
			groupchat.setChattime(dates);
			int temp =chatDao.saveOneChat(groupchat);
			if (temp>0) {
				List<Groupchat > groupchats=new ArrayList<Groupchat>();
				groupchats=chatDao.getAllChat(chatp);
				Map map = new HashMap();
				map.put("groupchats", groupchats);
				HttpUtil.http(map,response);
			}
		}else if (type.equals("2")) {//查询留言
			List<Groupchat > groupchats=new ArrayList<Groupchat>();
			groupchats=chatDao.getAllChat(chatp);
			System.out.println("条数变化，重新加载");
			Map map = new HashMap();
			map.put("groupchats", groupchats);
			HttpUtil.http(map,response);
		}else if (type.equals("3")) {//查询留言条数
			int temp=chatDao.getAllChat(chatp).size();
			System.out.println("留言条数："+temp);
			Map map = new HashMap();
			map.put("count", temp);
			HttpUtil.http(map,response);
		}else if (type.equals("4")) {//查询qq群
			List<ChatPersonnel> chatPersonnels=new ArrayList<ChatPersonnel>();
			chatPersonnels=chatDao.getAllChatPersonnels(userid);
			Map map = new HashMap();
			map.put("chatPersonnels", chatPersonnels);
			HttpUtil.http(map,response);
		}else if (type.equals("5")) {//查询qq群人员的信息
			String[] useridMessage=userids.split(",");
			List<User> users=new ArrayList<User>();
			for (int i = 0; i < useridMessage.length; i++) {
				User user=chatDao.getUsers(useridMessage[i]).get(0);
				users.add(user);
			}
			Map map = new HashMap();
			map.put("users", users);
			HttpUtil.http(map,response);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
