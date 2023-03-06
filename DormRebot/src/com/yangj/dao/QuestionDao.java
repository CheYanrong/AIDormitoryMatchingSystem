package com.yangj.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.yangj.model.Question;


public class QuestionDao {
	private List<Question> questions=new LinkedList<>();
	public void load(){
		try {
			final String fileName="res/宿舍智能匹配系统数据.txt";
			
			BufferedReader bufferedReader=
					new BufferedReader(new InputStreamReader(
							new FileInputStream(fileName), "GBK"));
			String line;
			int lineNum=1;
			while((line=bufferedReader.readLine())!=null) {
				String[] array = line.split("\t");
				List<String> options=new LinkedList<>();
				for (int i = 1; i < array.length; i++) {
					options.add(array[i]);
				}
				Question question=new Question(lineNum++, array[0], options);
				questions.add(question);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<Question> get() {
		return questions;
	}
}
