package com.jsp.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/StudentDashboard")
public class StudentDashboard extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String find = req.getParameter("find");
		String update = req.getParameter("update");
		String logout = req.getParameter("logout");
		if (find!=null && "finddetails".equals(find)) {
			resp.sendRedirect("FetchStudent2.html");
		} else if (update!=null && "updatedetails".equals(update)) {
			resp.sendRedirect("updateStudent2.html");
		} else { 
			resp.sendRedirect("LandingPage.html");
		}
	}
}
