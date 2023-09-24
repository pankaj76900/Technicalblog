package org.jsp.userproduct.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.userproduct.dao.ProductDao;
import org.jsp.userproduct.dao.UserDao;
import org.jsp.userproduct.dto.Product;
import org.jsp.userproduct.dto.User;


public class UserProductController {
	static UserDao userDao = new org.jsp.userproduct.dao.UserDao();
	static ProductDao productDao = new org.jsp.userproduct.dao.ProductDao();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("1 : Register User");
		System.out.println("2 : varify User by Phone And password");
		System.out.println("3 : varify User by email and password");
		System.out.println("4 : Update User");
		System.out.println("5 : Add product ");
		System.out.println("6 : view Product by userId");
		System.out.println("7 : view Product by user category");
		System.out.println("Enter Choice");
		int choice = sc.nextInt();
		switch (choice) {
		case 1: {
			saveUser();
			break;
		}
		case 2: {
			verifyUserBYPaP();
            break;
		}
		case 3: {
			verifyUserBYEaP();
			break;
		}
		case 4: {
			updateUser();
			break;
		}
		case 5: {
			AddProduct();
			break;
		}
		case 6: {
			viewproductbyid();
			break;
		}
		case 7: {
			viewproductbycategory();
			break;
		}
		}
	}
		public static void saveUser(){
			System.out.println("Enter User name , phone ,email,password");
			String name=sc.next();
			long phone =sc.nextLong();
			String email=sc.next();
			String password=sc.next();
			
			User u = new User();
			u.setName(name);
			u.setPhone(phone);
			u.setEmail(email);
			u.setPassword(password);
			
			u=userDao.RegisterUser(u);
			System.out.println("User Register !!!!");
			
		}
		public static void updateUser(){
			System.out.println("Enter user id to update user");
			int id = sc.nextInt();
			System.out.println("Enter User name , phone ,email,password");
			String name=sc.next();
			long phone =sc.nextLong();
			String email=sc.next();
			String password=sc.next();
			
			User u = new User();
			u.setId(id);
			u.setName(name);
			u.setPhone(phone);
			u.setEmail(email);
			u.setPassword(password);
			
			u=userDao.updateUser(u);
			System.out.println("User Register !!!!");
			
		}
		
		public static void verifyUserBYPaP() {
			System.out.println("Enter phone and password");
			long phone = sc.nextLong();
			String password=sc.next();
			
			User u=new User();
			u=userDao.varifyUser(phone, password);
			if(u!=null) {
				System.out.println(u);
			}else {
				System.err.println("invalied user");
			}
			
		}
		
		public static void verifyUserBYEaP() {
			System.out.println("Enter Email and password");
			String email=sc.next();
			String password=sc.next();
			
			User u=new User();
			u=userDao.varifyUser(email, password);
			if(u!=null) {
				System.out.println(u);
			}else {
				System.err.println("invalied user");
			}
			
		}
		
		public static void AddProduct() {
			System.out.println("Enter user id to add product");
			int user_id=sc.nextInt();
			System.out.println("Enter Product name,description,brand,category,price");
			String name=sc.next();
			String descrip=sc.next();
			String brand=sc.next();
			String categery=sc.next();
			double price=sc.nextDouble();
			
			Product pro=new Product();
			pro.setName(name);
			pro.setDescrip(descrip);
			pro.setBrand(brand);
			pro.setCategery(categery);
			pro.setPrice(price);
			
			pro=productDao.AddProduct(pro, user_id);
			System.out.println("product Add");
			
			
		}
		
		public static void viewproductbyid() {
			System.out.println("Enter user id");
			int user_id=sc.nextInt();
			
		List<Product> p=productDao.viewProduct(user_id);
			if(p!=null) {
				System.out.println(p);
			}else {
				System.err.println("invalied user");
			}
			
		}
		
		public static void viewproductbycategory() {
			System.out.println("Enter user categery");
			String categery=sc.next();
			Product p =new Product();
			p=productDao.viewProductBY(categery);
			if(p!=null) {
				System.out.println("product id:"+p.getId());
				System.out.println("product name:"+p.getName());
				System.out.println("product category:"+p.getCategery());
				System.out.println("product dec:"+p.getDescrip());
				System.out.println("product brand:"+p.getBrand());
				
			}else {
				System.err.println("invalied user");
			}
			
		}

}