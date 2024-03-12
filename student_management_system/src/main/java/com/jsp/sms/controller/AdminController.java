package com.jsp.sms.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jsp.sms.model.Admin;
import com.jsp.sms.model.Student;

public class AdminController {

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgsql");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public boolean addStudent(Student student, Admin admin) {
		if (student != null) {
			entityTransaction.begin();
			entityManager.persist(student);
			entityManager.merge(admin);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	public Admin findAdmin(int admin_id) {
		return entityManager.find(Admin.class, admin_id);
	}
	public Student findStudent(int student_id) {
		return entityManager.find(Student.class, student_id);
	}

	public void updateStudent(int student_id, String student_name, String student_email, long student_contact,
			String student_address, String student_password, String student_branch, String student_gender) {
		Student student = entityManager.find(Student.class, student_id);
		if (student != null) {
			student.setName(student_name);
			student.setContact(student_contact);
			student.setAddress(student_address);
			student.setBranch(student_branch);
			student.setEmail(student_email);
			student.setPassword(student_password);
			student.setGender(student_gender);
			entityTransaction.begin();
			entityManager.merge(student);
			entityTransaction.commit();
		}
	}
	public boolean removeStudent(int student_id) {
		Student student = entityManager.find(Student.class, student_id);
		if (student!=null) {
			entityTransaction.begin();
			entityManager.remove(student);
			entityTransaction.commit();
			return true;
		}
		return false;
	}

	
	
	public boolean deleteParticularStudent(int studentIdToDelete) {
		Admin admin = entityManager.find(Admin.class, 1);
		if (admin != null) {
			List<Student> students = admin.getStudents();
			if (students == null) {
				// Account does not exist in bank
				return false;
			} else {
				Student studentToRemove = null;
				for (Student student : students) {
					if (student.getId() == studentIdToDelete) {
						studentToRemove =student;
					} 
				}
				if (studentToRemove != null) {
					entityTransaction.begin();
					students.remove(studentToRemove);
					entityTransaction.commit();

					entityTransaction.begin();
					entityManager.remove(studentToRemove);
					entityTransaction.commit();

					// Account deleted
					return true;
				} else {
					// Account with given id does not exist
					return false;
				}
			}
		}
		// Bank with given id does not exist
		return false;
	}

}
