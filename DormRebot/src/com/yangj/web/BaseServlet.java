package com.yangj.web;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangj.model.Content;
import com.yangj.server.Rebot;


public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHARSET = "UTF-8";
    public BaseServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding("text/html;charset="+CHARSET);
		String input =request.getParameter("content");
		
		HttpSession session=request.getSession();
		Rebot rebot=(Rebot) session.getAttribute("rebot");
		if(rebot==null){
			String rebotClass=getServletContext().getInitParameter("RebotClass");
			try {
				rebot=(Rebot)(Class.forName(rebotClass).newInstance());
				session.setAttribute("rebot", rebot);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		rebot.dealInput(input);
		request.setAttribute("contentes", rebot.getContentes());
		request.setAttribute("name", rebot.getName());
		request.getRequestDispatcher("show.jsp").forward(request, response);;
	}

	public String dealInput(String content) {
		return "收到："+content;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
