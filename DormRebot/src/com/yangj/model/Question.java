package com.yangj.model;

import java.util.List;

public class Question {
	private int id;
	private String title;
	private List<String>  options;
	public Question(int id, String title, List<String> options) {
		super();
		this.id = id;
		this.title = title;
		this.options = options;
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", options=" + options + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
}
