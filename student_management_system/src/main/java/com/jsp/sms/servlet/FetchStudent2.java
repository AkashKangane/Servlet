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

@WebServlet(value = "/FetchStudent2")
public class FetchStudent2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String studentId = req.getParameter("fetch");
        int studentIdInt = Integer.parseInt(studentId);

        PrintWriter printWriter = resp.getWriter();
        AdminController adminController = new AdminController();
        Student student = adminController.findStudent(studentIdInt);

        if (student != null) {
            printWriter.print("<html><head>");
            printWriter.print("<style>");
            printWriter.print("body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; }");
            printWriter.print(".container { max-width: 600px; margin: 20px auto; padding: 20px; background-color: #e0e0e0; border: 1px solid #ccc; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); color: #333; }");
            printWriter.print("h6 { margin-bottom: 10px; color: #007BFF; }");
            printWriter.print("</style>");
            printWriter.print("</head><body>");
            printWriter.print("<div class='container'>");
            printWriter.print("<h6>Student Id: " + student.getId() + "</h6>");
            printWriter.print("<h6>Student Name: " + student.getName() + "</h6>");
            printWriter.print("<h6>Student Address: " + student.getAddress() + "</h6>");
            printWriter.print("<h6>Student Branch: " + student.getBranch() + "</h6>");
            printWriter.print("<h6>Student Email: " + student.getEmail() + "</h6>");
            printWriter.print("<h6>Student Contact: " + student.getContact() + "</h6>");
            printWriter.print("<h6>Student Gender: " + student.getGender() + "</h6>");
            printWriter.print("</div></body></html>");

            req.setAttribute("StudentId", student.getId());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("StudentDashboard.html");
            requestDispatcher.include(req, resp);
        } else {
            printWriter.print("<html><body>"
                    + "<h1>Student data does not exist</h1>"
                    + "</body></html>");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("FetchStudent2.html");
            requestDispatcher.include(req, resp);
        }
	}
}
