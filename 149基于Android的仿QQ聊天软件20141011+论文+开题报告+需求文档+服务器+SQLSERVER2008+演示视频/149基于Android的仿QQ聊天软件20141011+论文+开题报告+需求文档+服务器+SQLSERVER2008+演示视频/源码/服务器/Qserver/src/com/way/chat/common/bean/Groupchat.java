package com.way.chat.common.bean;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



public class Groupchat  implements java.io.Serializable {


    // Fields    

     private Integer chatid;
     private String chatPersonnelid;
     private String chattime;
     private String chatcontent;
     private String chatuserid;
     private int img;
     
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public Integer getChatid() {
		return chatid;
	}
	public void setChatid(Integer chatid) {
		this.chatid = chatid;
	}
	
	public String getChatPersonnelid() {
		return chatPersonnelid;
	}
	public void setChatPersonnelid(String chatPersonnelid) {
		this.chatPersonnelid = chatPersonnelid;
	}
	public String getChattime() {
		return chattime;
	}
	public void setChattime(String chattime) {
		this.chattime = chattime;
	}
	public String getChatcontent() {
		return chatcontent;
	}
	public void setChatcontent(String chatcontent) {
		this.chatcontent = chatcontent;
	}
	public String getChatuserid() {
		return chatuserid;
	}
	public void setChatuserid(String chatuserid) {
		this.chatuserid = chatuserid;
	}


}