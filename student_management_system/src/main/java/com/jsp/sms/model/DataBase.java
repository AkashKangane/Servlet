package com.jsp.sms.model;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DataBase {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgsql");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();
	
	private static ArrayList<Student> Students = new ArrayList<Student>();
	
	public static ArrayList<Student> getStudents() {
		return Students;
	}

	public static void setStudents(ArrayList<Student> students) {
		DataBase.Students = students;
	}
	
	
	public Admin findAdmin(int admin_id) {
		return entityManager.find(Admin.class, admin_id);
	}
	
}
