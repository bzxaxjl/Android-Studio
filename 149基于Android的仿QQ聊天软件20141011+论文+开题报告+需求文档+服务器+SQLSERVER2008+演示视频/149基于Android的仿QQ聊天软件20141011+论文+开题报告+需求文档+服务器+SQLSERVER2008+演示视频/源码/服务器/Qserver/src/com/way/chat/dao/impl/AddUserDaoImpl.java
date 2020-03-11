package com.way.chat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.way.chat.common.bean.ChatPersonnel;
import com.way.chat.common.bean.User;
import com.way.chat.common.util.DButil;
import com.way.chat.dao.AddUserDao;

public class AddUserDaoImpl implements AddUserDao {
	private Statement sta = null;

	/**
	 * 查询所有的qq
	 */
	@Override
	public List<User> getAllUsers(String id) {
		Connection con = DButil.connect();
		String sql2 = "select * from users where _id!='" + id + "'";
		List<User> users = new ArrayList<User>();
		try {
			sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sta.executeQuery(sql2);
			while (rs.next()) {
				User user = new User();
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
	/**
	 * 添加好友
	 */
	@Override
	public int addOneUser(int id, User user) {
		// TODO Auto-generated method stub
		Connection con = DButil.connect();
		int temp = 0;
		String sql2 = "select * from _"+id+" where _qq=" + user.getId() + "";
		List<User> users = new ArrayList<User>();
		try {
			sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sta.executeQuery(sql2);
			while (rs.next()) {
				User user1 = new User();
				user1.setName(rs.getString(2));
				user1.setImg(rs.getInt(6));
				user1.setId(rs.getInt(1));
				users.add(user1);
			}
			if (users.size() > 0) {
				return -1;
			} else {
				String sql = "insert into _"+id+" (_name,_isOnline,_group,_qq,_img) values(?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getName());
				ps.setInt(2, user.getIsOnline());
				ps.setInt(3, user.getGroup());
				ps.setInt(4, user.getId());
				ps.setInt(5, user.getImg());
				temp = ps.executeUpdate();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(con);
		}
		return temp;
	}

	@Override
	public int addOneGroup(ChatPersonnel chatPersonnel) {
		Connection con = DButil.connect();
		int temp = 0;
		try {
			String sql = "insert into chat_personnel(personnelalluser,personnelname,chatname) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, chatPersonnel.getPersonnelalluser());
			ps.setString(2, chatPersonnel.getPersonnelname());
			ps.setString(3, chatPersonnel.getChatname());
			temp = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(con);
		}
		return temp;
	}

}
