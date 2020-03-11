package com.way.chat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.way.chat.common.bean.ChatPersonnel;
import com.way.chat.common.bean.Groupchat;
import com.way.chat.common.bean.User;
import com.way.chat.common.util.DButil;
import com.way.chat.dao.ChatDao;
public class ChatDaoImpl implements ChatDao{
	private Statement sta = null;
	@Override
	public List<Groupchat> getAllChat(String id) {
		
		// TODO Auto-generated method stub
		Connection con = DButil.connect();
		String sql2 = "select *  from  groupchat where personnelid='"+id+"'";
		List<Groupchat > groupchats=new ArrayList<Groupchat>();
		try {
			sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sta.executeQuery(sql2);
			while(rs.next()){
				Groupchat groupchat=new Groupchat();
				groupchat.setChatcontent(rs.getString(4));
				groupchat.setChatid(rs.getInt(1));
				groupchat.setChattime(rs.getString(3));
				groupchat.setChatuserid(rs.getString(5));
				groupchat.setImg(rs.getInt(6));
				groupchat.setChatPersonnelid(rs.getString(2));
				groupchats.add(groupchat);
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		} finally {
			DButil.close(con);
		}
		return groupchats;
	}

	@Override
	public int getAllChatCount(String id) {
		// TODO Auto-generated method stub
		Connection con = DButil.connect();
		String sql2 = "select *  from  groupchat where personnelid=?";
		int temp=0;
		try {
			PreparedStatement ps = con.prepareStatement(sql2);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
		//	temp=rs.getc
		} catch (SQLException e) {
			 e.printStackTrace();
		} finally {
			DButil.close(con);
		}
		return temp;
	}

	@Override
	public int saveOneChat(Groupchat groupchat) {
		Connection con = DButil.connect();
		String sql="insert into groupchat(personnelid,chattime,chatcontent,chatuserid,img) values(?,?,?,?,?)";
		int temp=0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(groupchat.getChatPersonnelid()));
			ps.setString(2, groupchat.getChattime());
			ps.setString(3, groupchat.getChatcontent());
			ps.setString(4, groupchat.getChatuserid());
			ps.setInt(5, groupchat.getImg());
			temp = ps.executeUpdate();
		} catch (SQLException e) {
			 e.printStackTrace();
		} finally {
			DButil.close(con);
		}
		return temp;
	}

	@Override
	public List<User> getUsers(String id) {
		Connection con = DButil.connect();
		String sql2 = "select * from users where _id='"+id+"'";
		List<User> users=new ArrayList<User>();
		try {
			sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sta.executeQuery(sql2);
			while(rs.next()){
			User user=new User();
			user.setName(rs.getString(2));
			user.setImg(rs.getInt(6));
			user.setId(rs.getInt(1));
			users.add(user);
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		} finally {
			DButil.close(con);
		}
		return users;
	}

	@Override
	public List<ChatPersonnel> getAllChatPersonnels(String id) {
		// TODO Auto-generated method stub
		Connection con = DButil.connect();
		String sql2 = "select *  from  chat_personnel where personnelalluser  like ?";
		List<ChatPersonnel> chatPersonnels=new ArrayList<ChatPersonnel>();
		try {
			PreparedStatement ps = con.prepareStatement(sql2);
			ps.setString(1, "%"+id+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ChatPersonnel chatPersonnel=new ChatPersonnel();
				chatPersonnel.setPersonnelalluser(rs.getString(2));
				chatPersonnel.setPersonnelid(rs.getInt(1));
				chatPersonnel.setPersonnelname(rs.getString(3));
				chatPersonnel.setChatname(rs.getString(4));
				chatPersonnels.add(chatPersonnel);
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		} finally {
			DButil.close(con);
		}
		return chatPersonnels;
	}

}
