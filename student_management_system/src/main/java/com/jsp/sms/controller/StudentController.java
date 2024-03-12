package com.jsp.sms.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;

import com.jsp.sms.model.Student;

@WebServlet(loadOnStartup = 1)
public class StudentController {
	static EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("pgsql");
	static EntityManager entityManager = createEntityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();
	
	public Student findStudent(int student_id) {
		return entityManager.find(Student.class, student_id);
	}
	
	public void updateStudent(int student_id, Student student) {
		Student students = entityManager.find(Student.class, student_id);
		if (students!=null) {
			
			entityTransaction.begin();
			entityManager.merge(student);
			entityTransaction.commit();
		}
		
	}

}
