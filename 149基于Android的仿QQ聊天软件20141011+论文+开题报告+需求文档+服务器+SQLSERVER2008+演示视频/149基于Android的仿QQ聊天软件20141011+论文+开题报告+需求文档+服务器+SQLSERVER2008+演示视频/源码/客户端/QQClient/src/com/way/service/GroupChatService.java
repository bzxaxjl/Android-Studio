package com.way.service;

import java.util.Map;

import com.way.util.SendGETRequest;
import com.way.util.Web;


public class GroupChatService {
	/**
	 * 从服务器获取数据
	 * @param params
	 * @return
	 */
	public static Map<String, String> getService(String url,String params){
		Map<String ,String> tempMap = null;
		try {
			return SendGETRequest.sendGETRequest(url, params,
					"UTF-8");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempMap;
	}
}
