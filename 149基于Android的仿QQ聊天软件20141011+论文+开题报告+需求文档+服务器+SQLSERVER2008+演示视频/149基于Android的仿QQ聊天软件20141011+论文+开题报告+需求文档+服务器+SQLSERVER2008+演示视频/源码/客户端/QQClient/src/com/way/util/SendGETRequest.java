package com.way.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import android.util.Log;


public class SendGETRequest {
	  /**
     * 提交数据到服务器
     * 并且返回取出的值
     * @param path
     * @param params
     * @param encode
     * @return
     * @throws Exception
     */
    public static Map<String,String> sendGETRequest(String path,  
        String params,String encode) throws Exception {  
    	Map<String,String> tempMap = null;
    	
    	boolean b=false;
    	Log.v("url", path+params);
        HttpURLConnection conn=(HttpURLConnection)new URL(path+params).openConnection();  
    //  conn.setConnectTimeout(5000);  
        conn.setRequestMethod("POST");  
        conn.setRequestProperty("Content-Type", "text/xml");
		conn.setRequestProperty("Charset", encode);
//		conn.setRequestProperty("Cookie", Constant.youxinCookie);
		conn.setConnectTimeout(20000);
		// 如果请求响应码是200，则表示成功
		if (conn.getResponseCode() == 200) {
			System.out.println(conn.getResponseCode());
			// 获得服务器响应的数据
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), encode));
			// 数据
			String retData = null;
			String responseData = "";
			while ((retData = in.readLine()) != null) {
				responseData += retData;
			}
			in.close();
			System.out.println(responseData.toString());
			tempMap = JsonUtil.getJosn(responseData);
			return tempMap;
		}
		// tempMap.put("innerEorr", "服务器响应超时");
		return tempMap;
    }
}
