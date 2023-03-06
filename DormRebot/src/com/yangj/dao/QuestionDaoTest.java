package com.yangj.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuestionDaoTest {
	private QuestionDao dao=new QuestionDao();
	
	@Before
	public void testLoad() {
		dao.load();
	}

	@Test
	public void testGet() {
		System.out.println(dao.get());
	}

}
