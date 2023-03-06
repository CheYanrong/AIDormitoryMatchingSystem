package com.yangj.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import com.yangj.model.Paper;
import com.yangj.model.Question;
import com.yangj.model.Student;

public class PaperDao {
	private static final String dataFile = "res/data.dat";
	private List<Paper> papers=new LinkedList<>();
	static {
		
	}
	public PaperDao(){
		if(new File(dataFile).exists()) {
			try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(dataFile))){
				papers=(List<Paper>) objectInputStream.readObject();
			}catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean savePaper(Paper paper) {
		return papers.add(paper);
	}
//	设定题目
	public void setQuestions(List<Question> questions) {
		Paper.setQuestions(questions);
	}
	
//	收集学生信息
//	private boolean saveStudent(Student student) {
//		int index=papers.indexOf(new Paper(student));
//		if(index<0) return false;
//		papers.get(index).setStudent(student);
//		return true;
//	}
//	发题
	
//	答题
//	显示所有的调查问卷
	
	public Paper getPaperByPhone(String phone) {
		int index=papers.indexOf(
				new Paper(new Student(phone)));
		if(index<0) return null;
		return papers.get(index);
	}
	public List<Paper> getPapers() {
		return papers;
	}
	public void saveInfos() {
		try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(dataFile))){
			objectOutputStream.writeObject(papers);
			objectOutputStream.flush();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<Student> getMactchStudents(Student student){
		Paper paper =getPaperByPhone(student.getPhone());
//		System.out.println(paper.getStudent().getName());
//		System.out.println(Integer.toBinaryString(paper.getAnswer()));
		List<Student> students=new LinkedList<>();
		
		for (Paper paper2 : papers) {
			if(paper2.equals(paper)) continue;//自己不用匹配自己
			if(paper2.answered!=255) continue;//没有完成
			if((paper2.answer & paper.answer) == paper.answer) {
//				System.out.println(paper2.getStudent().getName());
//				System.out.println(Integer.toBinaryString(paper2.answer));
				students.add(paper2.getStudent());
			}
		}
		return students;
	}
}
