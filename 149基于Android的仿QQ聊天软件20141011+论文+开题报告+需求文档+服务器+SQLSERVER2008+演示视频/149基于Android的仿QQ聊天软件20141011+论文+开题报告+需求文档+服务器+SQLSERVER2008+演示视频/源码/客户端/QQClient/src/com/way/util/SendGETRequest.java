package com.way.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import android.util.Log;


public class SendGETRequest {
	  /**
     * �ύ���ݵ�������
     * ���ҷ���ȡ����ֵ
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
		// ���������Ӧ����200�����ʾ�ɹ�
		if (conn.getResponseCode() == 200) {
			System.out.println(conn.getResponseCode());
			// ��÷�������Ӧ������
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), encode));
			// ����
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
		// tempMap.put("innerEorr", "��������Ӧ��ʱ");
		return tempMap;
    }
}
