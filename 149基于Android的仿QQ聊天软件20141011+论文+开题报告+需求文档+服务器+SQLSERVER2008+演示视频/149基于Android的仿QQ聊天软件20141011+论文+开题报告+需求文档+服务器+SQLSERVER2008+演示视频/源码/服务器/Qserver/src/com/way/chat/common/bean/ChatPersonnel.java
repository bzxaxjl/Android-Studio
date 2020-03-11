package com.way.chat.common.bean;
// default package

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



public class ChatPersonnel  implements java.io.Serializable {


    // Fields    

     private Integer personnelid;
     private String personnelalluser;
     private String personnelname;
     private Set<Groupchat> groupchats = new HashSet<Groupchat>(0);
     private String chatname;
     
	public String getChatname() {
		return chatname;
	}
	public void setChatname(String chatname) {
		this.chatname = chatname;
	}
	public Integer getPersonnelid() {
		return personnelid;
	}
	public void setPersonnelid(Integer personnelid) {
		this.personnelid = personnelid;
	}
	public String getPersonnelalluser() {
		return personnelalluser;
	}
	public void setPersonnelalluser(String personnelalluser) {
		this.personnelalluser = personnelalluser;
	}
	public String getPersonnelname() {
		return personnelname;
	}
	public void setPersonnelname(String personnelname) {
		this.personnelname = personnelname;
	}
	public Set<Groupchat> getGroupchats() {
		return groupchats;
	}
	public void setGroupchats(Set<Groupchat> groupchats) {
		this.groupchats = groupchats;
	}


}