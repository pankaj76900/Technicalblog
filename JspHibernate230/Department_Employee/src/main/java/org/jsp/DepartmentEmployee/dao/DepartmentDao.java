package org.jsp.DepartmentEmployee.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jsp.DepartmentEmployee.dto.Department;

public class DepartmentDao {
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public Department saveDepartment(Department department)
	{
		EntityTransaction t=manager.getTransaction();
		manager.persist(department);
		t.begin();
		t.commit();
		return department;
		
	}
	
	public Department updateDepartment(Department department)
	{
		EntityTransaction t=manager.getTransaction();
		manager.merge(department);
		t.begin();
		t.commit();
		return department;
		
	}
	
	public Department findById(int id)
	{
		return manager.find(Department.class, id);
	}
	

}
