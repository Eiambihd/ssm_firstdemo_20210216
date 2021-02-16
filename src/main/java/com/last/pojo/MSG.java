package com.last.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * �Զ����һ��������
 * ���ڴ���json����
 * code����״̬��
 * messageΪ��Ϣ
 * map���Խ����󴫵ݻ�ǰ̨
 *
 */
public class MSG {
	
	private int code;
	
	private String message;
	
	private Map<String,Object> map = new HashMap<String,Object>();
	
	public static MSG success() {
		MSG m = new MSG();
		m.setCode(100);
		m.setMessage("success");
		return m;
	}
	
	public static MSG fail() {
		MSG m = new MSG();
		m.setCode(200);
		m.setMessage("fail");
		return m;
	}

	public MSG add(String key,Object value) {
		this.getMap().put(key,value);
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
}
