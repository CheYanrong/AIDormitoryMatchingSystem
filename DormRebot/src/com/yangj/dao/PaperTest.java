package com.yangj.dao;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.yangj.model.Paper;
import com.yangj.model.Student;



public class PaperTest {
	private PaperDao paperDao=new PaperDao();
	
	@Before
	public void initPaper() {
		Random random=new Random();
		for (int i = 0; i < 150; i++) {
			Student student = new Student(13100010001L+i+"");
			student.setName("学生"+i);
			Paper paper=new Paper(student);
			paper.answer=random.nextInt(256);
			paper.answered=255;
			paperDao.savePaper(paper);
		}
	}
	
	@Test
	public void test() {
		Paper paper = paperDao.getPaperByPhone("13100010001");
		System.out.println(paper.getStudent().getName());
		System.out.println(Integer.toBinaryString(paper.getAnswer()));
		
		List<Paper> papers = paperDao.getPapers();
		
		for (Paper paper2 : papers) {
			if(paper2.equals(paper)) continue;//自己不用匹配自己
			if(paper2.answered!=255) continue;//没有完成
			if((paper2.answer & paper.answer) == paper.answer) {
				System.out.println(paper2.getStudent().getName());
				System.out.println(Integer.toBinaryString(paper2.answer));
			}
		}
	}
}
