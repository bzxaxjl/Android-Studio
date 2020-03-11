package com.way.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.way.chat.common.bean.User;
import com.way.chat.common.util.HttpUtil;
import com.way.chat.dao.UserDao;
import com.way.chat.dao.impl.UserDaoFactory;
import com.way.chat.dao.impl.UserDaoImpl;

public class UserLogin extends HttpServlet {

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
		UserDao dao = new UserDaoImpl();
		String userid=request.getParameter("userid");
		String mima=request.getParameter("mima");
		/*String email=request.getParameter("email");
		String nicheng=request.getParameter("nicheng");
		String mima=request.getParameter("mima");
		Random random=new Random();
		int a=random.nextInt(9);
		User user=new User();
		user.setEmail(email);
		user.setImg(a);
		user.setName(nicheng);
		user.setPassword(mima);
		int temp=dao.register(user);*/
		User user=new User();
		user.setId(Integer.parseInt(userid));
		user.setPassword(mima);
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		List<User> users=userDaoImpl.login(user);
		Map map = new HashMap();
		map.put("users", users);
		HttpUtil.http(map,response);
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

}
