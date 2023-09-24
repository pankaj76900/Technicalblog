package org.jsp.DepartmentEmployee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jsp.DepartmentEmployee.dto.Department;
import org.jsp.DepartmentEmployee.dto.Employee;

import com.mysql.cj.Query;

public class EmployeeDao {
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public Employee saveEmployee(Employee employee, int dept_id)
	{
		Department d=manager.find(Department.class, dept_id);
				if(d!=null)
				{
					employee.setDepts(d); // assigning department for Employee
					d.getEmps().add(employee); // adding employee for department
					EntityTransaction t=manager.getTransaction();
					manager.persist(employee);
					t.begin();
					t.commit();
					return employee;
				}
				return null;
	}
	
	public Employee updateEmployee(Employee employee, int dept_id)
	{
		Department d=manager.find(Department.class, dept_id);
				if(d!=null)
				{
					employee.setDepts(d); // assigning department for Employee
					d.getEmps().add(employee); // adding employee for department
					EntityTransaction t=manager.getTransaction();
					manager.merge(employee);
					t.begin();
					t.commit();
					return employee;
				}
				return null;
	}
	
	public List<Employee> findEmpsByDeptId(int id)
	{
		String qry="select d.emps from Department d where d.id=?1";
		javax.persistence.Query q=manager.createQuery(qry);
		q.setParameter(1, id);
		return q.getResultList();
	}
	
	public List<Employee> findEmpsByDeptName(String name)
	{
		String qry="select d.emps from Department d where d.name=?1";
		javax.persistence.Query q=manager.createQuery(qry);
		q.setParameter(1, name);
		return q.getResultList();
		
	}
	
	
	public Employee verifyEmployee(int id, String password)
	{
		String qry="select e from Employee e where e.id=?1 and e.password=?2";
		javax.persistence.Query q=manager.createQuery(qry);
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return (Employee) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public Employee verifyEmployee(long phone, String password)
	{
		String qry="select e from Employee e where e.phone=?1 and e.password=?2";
		javax.persistence.Query q=manager.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Employee) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public Employee verifyEmployee(String email, String password)
	{
		String qry="select e from Employee e where e.email=?1 and e.password=?2";
		javax.persistence.Query q=manager.createQuery(qry);
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (Employee) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	

}
