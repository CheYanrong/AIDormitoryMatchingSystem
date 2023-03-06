package com.yangj.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.yangj.dao.QuestionDao;

public class Paper implements Serializable {
	private static final long serialVersionUID = 1L;//数据的版本号
	private static QuestionDao questionDao=new QuestionDao();
	private static List<Question> questions;
	static {
		questionDao.load();
		questions=questionDao.get();
	}
	private Student student;
//	1011 0011
	public int answer;//选择的档案
//	private String[] answered;
//	private int[] answered;
	public int answered;//记录是否参与
	
	public Paper(Student student) {
		super();
		this.student = student;
	}

	public Paper(Student student, int answer) {
		super();
		this.student = student;
		this.answer = answer;
	}
	
	public int getAnswer() {
		return answer;
	}

	public boolean getAnswer(int index) {
		return (answer & (1<<index))== (1<<index) ;
	}
	public void setAnswer(int index) {
		answer |= 1<<index;
	}
	public int getFirst() {
		for (int i = 0; i < questions.size(); i++) {
			if((answered & (1<<i))!= (1<<i)) return i;//如果遇到第1个没有答过，返回下标
		}
		return -1;
	}
	
	public void setAnswered(int index) {
//		2       0100
//		000000000000
		answered |= 1<<index;
	}
	public boolean isAnswered(int index) {
//		0000 0100
//		0100 0100
//		0000 0100
		return (answered & (1<<index))== (1<<index) ;
//		return (answered ==4 ) ;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(student);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paper other = (Paper) obj;
		return Objects.equals(student, other.student);
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public static void setQuestions(List<Question> questions) {
		Paper.questions = questions;
	}

	public static List<Question> getQuestions() {
		return questions;
	}
	
}
