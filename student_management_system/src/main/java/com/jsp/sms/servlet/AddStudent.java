package com.jsp.sms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(value = "/AddStudent")
public class AddStudent extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Name = req.getParameter("name");
		String Contact = req.getParameter("contact");
		String Address = req.getParameter("address");
		String Gender = req.getParameter("gender");
		String Branch = req.getParameter("branch");
		String Email = req.getParameter("email");
		String Password = req.getParameter("password");
		
		long contactNo = Long.parseLong(Contact);
		
		if (Name!=null && Contact!=null && Address!=null && Gender!=null && Branch!=null && Email!=null && Password!=null ) {
			AdminController adminController = new AdminController();
			Admin admin = adminController.findAdmin(1);
			List<Student> students = admin.getStudents();
			Student student = new Student();
			student.setName(Name);
			student.setContact(contactNo);
			student.setAddress(Address);
			student.setGender(Gender);
			student.setBranch(Branch);
			student.setEmail(Email);
			student.setPassword(Password);
			students.add(student);
			admin.setStudents(students);
			
			PrintWriter printWriter = resp.getWriter();
			
			
			adminController.addStudent(student, admin);
			printWriter.print("<html><body>"
					+ "<h1>Student Added</h1>"
					+ "</body></html>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("AdminDashboard.html");
			requestDispatcher.include(req, resp);
		} else {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("addStudent.html");
			requestDispatcher.forward(req, resp);
		}
		
		
		
	}
}
