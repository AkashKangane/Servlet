package com.jsp.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.sms.controller.AdminController;
import com.jsp.sms.model.Student;

@WebServlet(value = "/RemoveStudent")
public class RemoveStudent extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String RemoveId = req.getParameter("remove");
		int StudentId = Integer.parseInt(RemoveId);
		
		AdminController adminController = new AdminController();
		
		System.out.println(StudentId);
		PrintWriter printWriter = resp.getWriter();
		
		if (adminController.deleteParticularStudent(StudentId)) {
			printWriter.print("<html><body>"
					+ "<h1>Remove Student successfully</h1>"
					+ "</body></html>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("AdminDashboard.html");
			requestDispatcher.include(req, resp);
		} else {
			printWriter.print("<html><body>"
					+ "<h1>Please enter valid student id !</h1>"
					+ "</body></html>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("RemoveStudent.html");
			requestDispatcher.include(req, resp);
		}
	}
}
