package com.way.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.way.chat.common.bean.ChatPersonnel;
import com.way.chat.common.bean.User;
import com.way.chat.common.util.HttpUtil;
import com.way.chat.dao.AddUserDao;
import com.way.chat.dao.impl.AddUserDaoImpl;

public class AddUser extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding ("UTF-8");
		String type=request.getParameter("type");
		String id=request.getParameter("id");
		String qq=request.getParameter("qq");
		String name=request.getParameter("name");
		String img=request.getParameter("img");
		String group=request.getParameter("group");
		String isOnline=request.getParameter("isOnline");
		String alluser=request.getParameter("alluser");
		String chatname=request.getParameter("chatname");
		AddUserDao addUserDao=new AddUserDaoImpl();
		if (type.equals("1")) {//查询所有的qq
			List<User> users=addUserDao.getAllUsers(id);
			Map map = new HashMap();
			map.put("users", users);
			HttpUtil.http(map,response);
		}else if (type.equals("2")) {//添加好友
			//"id":9,"name":"这个世界","isOnline":0,"img":2,"group":0
			name=new String(name.getBytes("ISO8859_1"),"UTF-8");
			User user=new User();
			user.setId(Integer.parseInt(qq));
			user.setName(name);
			user.setGroup(Integer.parseInt(group));
			user.setImg(Integer.parseInt(img));
			user.setIsOnline(Integer.parseInt(isOnline));
			int temp=addUserDao.addOneUser(Integer.parseInt(id), user);
			if (temp==-1) {
				Map map = new HashMap();
				map.put("error", "已经是QQ好友不能重复添加");
				HttpUtil.http(map,response);
			}else if (temp==1) {
				Map map = new HashMap();
				map.put("success", "success");
				HttpUtil.http(map,response);
			}else {
				Map map = new HashMap();
				map.put("error", "网络错误");
				HttpUtil.http(map,response);
			}
			
		}else if (type.equals("3")) {//添加群组
			chatname=new String(chatname.getBytes("ISO8859_1"),"UTF-8");
			ChatPersonnel chatPersonnel=new ChatPersonnel();
			chatPersonnel.setPersonnelalluser(alluser);
			chatPersonnel.setChatname(chatname);
			chatPersonnel.setPersonnelname(id);
			int temp=addUserDao.addOneGroup(chatPersonnel);
			 if (temp==1) {
				Map map = new HashMap();
				map.put("success", "success");
				HttpUtil.http(map,response);
			}else {
				Map map = new HashMap();
				map.put("error", "网络错误");
				HttpUtil.http(map,response);
			}
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
