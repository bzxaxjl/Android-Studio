package com.way.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import android.text.TextUtils;

public class JsonUtil {

	/**
	 * ��map���ݽ�����������ƴ�ӳ�json�ַ���
	 * 
	 * @param map
	 * @return
	 */
	public static JSONObject setJosn(Map<String,String> map) throws Exception {
		JSONObject json = null;
		StringBuffer temp = new StringBuffer();
		if (!map.isEmpty()) {
			temp.append("{");

			// ����map
			Set set = map.entrySet();
			Iterator i = set.iterator();
			while (i.hasNext()) {
				Map.Entry entry = (Map.Entry) i.next();
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				temp.append("'" + key + "':");
				temp.append("'" + value + "',");
			}
			if (temp.length() > 1) {
				temp = new StringBuffer(temp.substring(0, temp.length() - 1));
			}
			temp.append("}");
			json = new JSONObject(temp.toString());
		}
		return json;
	}

	/**
	 * ��json�ַ��������������õ�map��
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<String,String> getJosn(String jsonStr) throws Exception {
		Map<String,String> map = null;
		if (!TextUtils.isEmpty(jsonStr)) {
			map = new HashMap<String,String>();
			JSONObject json = new JSONObject(jsonStr);
			Iterator i = json.keys();
			while (i.hasNext()) {
				String key = (String) i.next();
				String value = json.getString(key);
				map.put(key, value);
			}
		}
		return map;
	}
}
