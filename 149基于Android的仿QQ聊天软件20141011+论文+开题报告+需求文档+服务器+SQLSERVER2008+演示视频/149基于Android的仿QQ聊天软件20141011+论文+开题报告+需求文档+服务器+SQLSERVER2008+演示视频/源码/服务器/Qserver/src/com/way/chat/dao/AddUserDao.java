package com.way.chat.dao;

import java.util.List;

import com.way.chat.common.bean.ChatPersonnel;
import com.way.chat.common.bean.User;

public interface AddUserDao {
	public List<User> getAllUsers(String  id);
	public int addOneUser(int id,User user);
	public int addOneGroup(ChatPersonnel chatPersonnel);
}
