package com.way.util;


public class Web {
	public static String userid="";
	public static String img="";
	//服务器地址
	public static final String url="http://10.0.2.2:8080/Qserver/servlet/"; 
	//服务器地址
	public static final String url1="http://10.0.2.2:8080/Qserver/"; 
	//添加留言
	public static final String addLiuyan=url+"chat?type=1";
	//留言信息
	public static final String liuyan=url+"chat?type=2";
	//留言条数
	public static final String liuyancountry=url+"chat?type=3";
	//查询群数
	public static final String qunshu=url+"chat?type=4";
	//查询群成员
	public static final String changyuan=url+"chat?type=5";
	//登陆
	public static final String login=url1+"login";
	//查询所有qq
	public static final String getalluser=url1+"add?type=1";
	//添加好友
	public static final String addOneUser=url1+"add?type=2";
	//添加群组
	public static final String addGroupUser=url1+"add?type=3";
	
}
