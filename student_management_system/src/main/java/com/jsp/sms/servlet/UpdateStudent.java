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
import com.jsp.sms.controller.StudentController;
import com.jsp.sms.model.Student;

@WebServlet(value = "/update")
public class UpdateStudent extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String studentid = req.getParameter("studentid");
		String contact = req.getParameter("contact");
		String address = req.getParameter("address");
		String password = req.getParameter("password");
		
		int id = Integer.parseInt(studentid);
		long mobile = Long.parseLong(contact);
		PrintWriter printWriter = resp.getWriter();
		AdminController admincontroller = new AdminController();
		Student student = admincontroller.findStudent(id);
		if(student!=null) {
			student.setContact(mobile);
			student.setAddress(address);
			student.setPassword(password);;
			
			StudentController sc = new StudentController();
			sc.updateStudent(id, student);
			printWriter.print("<html><body>"
					+ "<h1>Details Of The Student Is Updated Successfully</h1>"
					+ "</body></html>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("AdminDashboard.html");
			requestDispatcher.forward(req, resp);
			
		}else {
			printWriter.print("<html><body>"
					+ "<h1>Details Of The Student Not Updated Successfully</h1>"
					+ "</body></html>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("updateStudent.html");
			requestDispatcher.forward(req, resp);
		}
	}
}
