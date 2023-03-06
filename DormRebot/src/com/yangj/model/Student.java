package com.yangj.model;

import java.io.Serializable;
import java.util.Objects;
//model必须实现Serializable
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String phone;
	
	public Student() {
		super();
	}
	public Student(String phone) {
		super();
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "<p>" + name + " " + phone + "</p>";
	}
	@Override
	public int hashCode() {
		return Objects.hash(phone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(phone, other.phone);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
