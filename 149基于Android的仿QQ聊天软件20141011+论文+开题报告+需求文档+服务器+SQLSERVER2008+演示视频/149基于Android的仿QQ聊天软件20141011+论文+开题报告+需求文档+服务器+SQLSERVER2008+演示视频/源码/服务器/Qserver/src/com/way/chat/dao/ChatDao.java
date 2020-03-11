package com.way.chat.dao;

import java.util.List;

import com.way.chat.common.bean.ChatPersonnel;
import com.way.chat.common.bean.Groupchat;
import com.way.chat.common.bean.User;

public interface ChatDao {
	public List<Groupchat> getAllChat(String id);
	public int getAllChatCount(String id);
	public int saveOneChat(Groupchat groupchat);
	public List<User> getUsers(String id);
	public List<ChatPersonnel> getAllChatPersonnels(String id);
}
