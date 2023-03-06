package com.yangj.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;
import com.yangj.model.Content;
public class Rebot implements Serializable {
	private String name;
	private String welcom;
	private String defaultAnswer;
	
	private Queue<Content> contentes=new LinkedList<Content>();
	
	public Rebot() {
		super();
	}
	
	public Rebot(String name, String welcom, String defaultAnswer) {
		super();
		this.name = name;
		this.welcom = welcom;
		this.defaultAnswer = defaultAnswer;
	}

	public void dealInput(String input) {
		textMsg(welcom);
	}
	
//	上行信息
	public boolean inputMsg(String input) {
		return contentes.add(new Content(0, input));
	}
	
//	下行文本信息
	public boolean textMsg(String ret) {
		return contentes.add(new Content(1, ret,new Date()));
	}
	
//	下行链接信息
	public boolean linkMsg(List<String> texts) {
		contentes.add(new Content(1, texts.get(0)));
		List<Properties> messages=new ArrayList<Properties>();
		for (int i = 1; i < texts.size(); i+=2) {
			Properties properties=new Properties();
			properties.setProperty("text", texts.get(i));
			properties.setProperty("url", texts.get(i+1));
			messages.add(properties);
		}
		contentes.add(new Content(2,messages ,new Date()));
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Queue<Content> getContentes() {
		return contentes;
	}

	public void setContentes(Queue<Content> contentes) {
		this.contentes = contentes;
	}
}
