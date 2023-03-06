package com.yangj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Content implements Serializable {
	private int type;
	private String name;
	private String msg;
	private List<Properties> messages;
	private Date sendTime;
	public Content(int type, String msg) {
		super();
		this.type = type;
		this.msg = msg;
	}
	
	public Content(int type, String msg, Date sendTime) {
		super();
		this.type = type;
		this.msg = msg;
		this.sendTime = sendTime;
	}
	

	public Content(int type, List<Properties> messages, Date sendTime) {
		super();
		this.type = type;
		this.messages = messages;
		this.sendTime = sendTime;
	}
	public Content(int type, String name, String msg, Date sendTime) {
		super();
		this.type = type;
		this.name = name;
		this.msg = msg;
		this.sendTime = sendTime;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public List<Properties> getMessages() {
		return messages;
	}

	public void setMessages(List<Properties> messages) {
		this.messages = messages;
	}
	
}
