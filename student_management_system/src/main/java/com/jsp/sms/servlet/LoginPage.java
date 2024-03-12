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
import com.jsp.sms.controller.StudentController;
import com.jsp.sms.model.Admin;
import com.jsp.sms.model.DataBase;
import com.jsp.sms.model.Student;

@WebServlet(value = "/LoginPage")
public class LoginPage extends HttpServlet {
	AdminController adminTable = new AdminController();
	StudentController studentTable = new StudentController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		req.setAttribute("username", username);
		PrintWriter printWriter = resp.getWriter();
		
		AdminController adminController = new AdminController();
		Admin admin = adminController.findAdmin(1);
		if (username.equals(admin.getEmail()) && password.equals(admin.getPassword())) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("AdminDashboard.html");
			requestDispatcher.forward(req, resp);
		} else {
			List<Student> students = admin.getStudents();
			for (Student student : students) {
				if (username.equals(student.getEmail()) && password.equals(student.getPassword())) {

					RequestDispatcher requestDispatcher = req.getRequestDispatcher("StudentDashboard.html");
					requestDispatcher.forward(req, resp);
					
				}
				else {
					printWriter.print("<html><body>");
					printWriter.print("<h1>Username / Password Invalid !</h1>");
					printWriter.print("</body></html>");
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("LandingPage.html");
					requestDispatcher.include(req, resp);
				}
			}
			
		}

	}
}
