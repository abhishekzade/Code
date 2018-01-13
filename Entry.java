package com;


import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Entry
{
	static String uname;
	static String upass;
	static Configuration cfg ;
	static SessionFactory factory;
	static Session s;
	public Entry()
	{
		
	}
	public static void main(String[] args)
	{
		String opt = "Y";		
		while (opt.contains("Y"))
		{
			System.out.println("Choose Option");
			
			System.out.println("1:Sign In");
			System.out.println("2:Sign Up");
			System.out.println("3:Show All");
			System.out.println("4:Delete Entry");
			System.out.println("5:Table Per Sub-Class Example.");
			Scanner sc  =  new Scanner(System.in);
			int i= Integer.parseInt(sc.nextLine());
		switch(i)
		{
			case 1:
				signIn();
				break;
			case 2:
				signUp();
				break;
			case 3:
				ShwoAll();
				break;
			case 4:
				System.out.println("Enter the name to be delete");
				Serializable key=sc.nextLine();
				delete(Model.class,key);
				ShwoAll();
				break;
				
			case 5:
				signup1();
				break;
			}
		System.out.println("Do you Wish to continue Press Y for Yes else N for No");
		sc  =  new Scanner(System.in);
		opt = sc.nextLine();
		}
	}	
	private static boolean delete(Class<?> type, Serializable id) {
		
		System.out.println("Deleting.... ");
		System.out.println("type "+type);
		System.out.println("id "+id);				
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session  = sf.openSession();
		Object o=session.get(type,id);
		System.out.println("Object  "+o);
		Model m = (Model)o;
		System.out.println("name==  "+m.getSname());
		Transaction tx = session.beginTransaction();
	//Object persistentInstance = session.load(type, id);
		 if (m != null) 
		 {			 
		        session.delete(m);
		        tx.commit();
		        session.close();
		        return true;
		    }			 
		 	tx.commit();
	        session.close();
		    return false;	
	}
	private static void signIn() 
	{
		Scanner sc=  new Scanner(System.in);
		System.out.println("Enter Username:");
		uname  = sc.nextLine();
		System.out.println("Enter Userpass:");
		upass  = sc.nextLine();
		
		cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
		s= factory.openSession();
		s.beginTransaction();
		
		String queryString = "from Model as M where M.sname=:name";
		Query query =  s.createQuery(queryString);
		query.setParameter("name",uname);
		List m =query.list();
		//Query query = s.createQuery("from employee where "+"("+"uname = "+uname +"AND"+"upass =" +upass+")"  );
		System.out.println(m.toString());
		s.close();		
	}
	
	private static void signup1() 
	{
		
		System.out.println("Add for Teacher ");
		
		Scanner sc=  new Scanner(System.in);
		System.out.println("Enter Username:");
		uname  = sc.nextLine();
		System.out.println("Enter Userpass:");
		upass  = sc.nextLine();	
		
		System.out.println("Enter Subject:");
		 String Subject   = sc.nextLine();	
		 Teacher m = new Teacher();
	     m.setSname(uname);
	     m.setSpass(upass);
	     m.setSubjects(Subject);
		 
		 System.out.println("Add for student ");
		 
		
		System.out.println("Enter Username:");
		uname  = sc.nextLine();
		System.out.println("Enter Userpass:");
		upass  = sc.nextLine();	
		System.out.println("Enter Branch:");
		String branch   = sc.nextLine();	
		
		Student stud = new Student();
		stud.setBranch(branch);
		stud.setSname(uname);
		stud.setSpass(upass);
				
		
		 
		cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
		s = factory.openSession();	
		Transaction t =		s.beginTransaction();       
      
         try
         {
        	 s.save(m);
        	 s.save(stud);	
         }
         catch(Exception e)
         {
        	 System.out.println("Save Error "+e.toString());
         }
         s.getTransaction().commit();
        	// t.commit();
         System.out.println("Command successfully executed....");
	     s.close();
         factory.close();
	}
	private static void signUp()
	{
		Scanner sc=  new Scanner(System.in);
		System.out.println("Enter Username:");
		uname  = sc.nextLine();
		System.out.println("Enter Userpass:");
		upass  = sc.nextLine();		
		cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
		s = factory.openSession();	
		Transaction t =		s.beginTransaction();       
        Model m = new Model();
        /* String id="(CONCAT( CHAR(FLOOR(RAND())+77)";
         m.setSid((id);*/
         m.setSname(uname);
         m.setSpass(upass);
         try
         {
        	 s.save(m);
         }
         catch(Exception e)
         {
        	 System.out.println("Save Error "+e.toString());
         }
         s.getTransaction().commit();
        	// t.commit();
         System.out.println("Command successfully executed....");
	     s.close();
         factory.close();
	}
	private static void ShwoAll()
	{
		cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
		s=factory.openSession();
		String hql  =  "from Model";
		Query<Model> m = s.createQuery(hql);
		List<Model> mlist=  m.getResultList();
		for(Model s:mlist)
		{
			System.out.println(s.sid +"-"+ s.sname +"-"+s.spass );
		}
	}
}
