package com.jsp.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.sms.controller.AdminController;
import com.jsp.sms.model.Admin;
import com.jsp.sms.model.DataBase;
import com.jsp.sms.model.Student;
@WebServlet(value = "/FetchStudent")
public class FetchStudent extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String StudentId = req.getParameter("fetch");
		
		int studentid = Integer.parseInt(StudentId);
		PrintWriter printWriter = resp.getWriter();
		AdminController adminController = new AdminController();
		Student student = adminController.findStudent(studentid);
		
		if (student!=null) {
			printWriter.print("<html>\r\n"
					+ "<head>\r\n"
					+ "    <style>\r\n"
					+ "        body {\r\n"
					+ "            font-family: Arial, sans-serif;\r\n"
					+ "            background-color: #f0f0f0;\r\n"
					+ "            text-align: center;\r\n"
					+ "            padding: 20px;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        h1 {\r\n"
					+ "            color: #333;\r\n"
					+ "            border-bottom: 2px solid #333;\r\n"
					+ "            padding: 10px;\r\n"
					+ "            margin: 10px 0;\r\n"
					+ "        }\r\n"
					+ "    </style>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <h1>Student Id : "+student.getId()+"</h1>\r\n"
					+ "    <h1>Student name : "+student.getName()+"</h1>\r\n"
					+ "    <h1>Student address : "+student.getAddress()+"</h1>\r\n"
					+ "    <h1>Student branch : "+student.getBranch()+"</h1>\r\n"
					+ "    <h1>Student email : "+student.getEmail()+"</h1>\r\n"
					+ "    <h1>Student contact : "+student.getContact()+"</h1>\r\n"
					+ "    <h1>Student Gender : "+student.getGender()+"</h1>\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "");
			req.setAttribute("StudentId", student.getId());
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("AdminDashboard.html");
			requestDispatcher.include(req, resp);			
		} else {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("FetchStudent.html");
			requestDispatcher.forward(req, resp);
		}
	}
}
