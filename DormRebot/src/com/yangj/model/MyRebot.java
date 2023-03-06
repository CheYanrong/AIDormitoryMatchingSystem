package com.yangj.model;

import java.util.LinkedList;
import java.util.List;

import com.yangj.dao.PaperDao;
import com.yangj.dao.QuestionDao;
import com.yangj.server.Rebot;

public class MyRebot extends Rebot {
	private static PaperDao paperDao=new PaperDao();
	private Paper paper=new Paper(new Student());
	
	@Override
	public void dealInput(String input) {
//		1.缺电话
//			1.1 提示输入电话，return
//			1.2 合法电话
		if(paper.getStudent().getPhone()==null) {
			if(input!=null && input.matches("\\d{11}")) {
				paper.getStudent().setPhone(input);
				Paper paperByPhone = paperDao.getPaperByPhone(input);
				if(paperByPhone!=null) {
					paper=paperByPhone;
				}else {
					paperDao.savePaper(paper);
				}
				paperDao.saveInfos();
				inputMsg(input);
			}else {
				textMsg("欢迎进入小艾宿舍匹配系统，感谢参与。请输您的电话。");
				return;
			}
		}
//		2.缺名字
//			2.1 提示输入名字 return
//			2.2 合理的名字 
//				2.2.1 加载原来的信息
		if(paper.getStudent().getName()==null) {
			if(input!=null && input.matches("\\S{2,10}")) {
				paper.getStudent().setName(input);
				paperDao.saveInfos();
				inputMsg(input);
			}else {
				textMsg("请输入您的姓名。");
				return;
			}			
		}
		
//		3 找到首个没有的提目
//			3.1 出题 return
//			3.2 答题
//				3.2.1 出下个题
		if(paper.answered!=255) {//还有项目没有做完
			if (!answerNextQuestion(input)) return;
		}else {
//			4.显示当前匹配的情况
			showSuccess(input);
		}
	}
	private void showSuccess(String input) {
		if(input!=null && input.contains("查看")) {
			showMatchResult();
		}else {
			textMsg("你已经完成了宿舍匹配调查,发送查看可以看匹配结果");
		}
		
	}
	private void showMatchResult() {
		List<Student> mactchStudents = paperDao.getMactchStudents(paper.getStudent());
		if(mactchStudents.size()==0) {
			textMsg("暂时还没有和您匹配的舍友，请稍后发送查看查询匹配结果");
			return;
		}
		StringBuffer sb=new StringBuffer();
		for (Student student : mactchStudents) {
			sb.append(student);
		}
		textMsg(sb.toString());
	}
	private boolean answerNextQuestion(String input) {
		int index=paper.getFirst();//获得第一个没有答过的项目
//		System.out.println(index);
		if(index==-1) return true;//全部答完
		if(input!=null && input.matches("[0-7][01]")) {
//			01
			int questionIndex=input.charAt(0)-48;
			int answer=input.charAt(1)-48;
			if(answer==1) {
				paper.setAnswer(questionIndex);
			}
			paper.setAnswered(questionIndex);
			paperDao.saveInfos();
			answerNextQuestion(null);
		}else {
			List<String> texts=new LinkedList<>();
			texts.add(paper.getQuestions().get(index).getTitle());
			List<String> options = paper.getQuestions().get(index).getOptions();
			for (int j = 0; j < options.size(); j++) {
				texts.add((1-j)+"."+options.get(j));
				texts.add("BaseServlet?content="+index+(1-j));
			}
//			题干 选项1 URL1  选项2 URL2 
			linkMsg(texts);
			return false;
		}
		return true;
	}
	private boolean doAction(int state,String action) {
		if(state==0 && "合法电话".equals(action)) {
//			paper.getStudent().setPhone(input);
//			Paper paperByPhone = paperDao.getPaperByPhone(input);
//			if(paperByPhone!=null) {
//				paper=paperByPhone;
//			}else {
//				paperDao.savePaper(paper);
//			}
//			paperDao.saveInfos();
		

		}
		return false;
	}
	private String getStudentName() {
		textMsg("请输入您的姓名");
		return null;
	}  

	@Override
	public String getName() {
		return "开发喵宿舍匹配系统";
	}
}
