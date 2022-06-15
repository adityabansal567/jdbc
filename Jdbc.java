package com.assignment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc {
public static void main(String args[]) throws SQLException, ClassNotFoundException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/adii","root","aditya");
	
	if(cnn!=null) {
		System.out.println("Database Connected");
		Scanner sc= new Scanner(System.in);
		System.out.println("Welcome to my Database");
		System.out.println("1. Insert into table");
		System.out.println("2. Update data");
		System.out.println("3. Delete from table");
		System.out.println("4. Display table");
		System.out.println("5. Display using Id");
		System.out.println("6. Search into database");
		System.out.println("7. Sort and Display using name");
		System.out.println("0. EXIT");
		
		System.out.println("Enter your choice: ");
		int choice=sc.nextInt();
		
		while(choice!=0) {
			if(choice==1)insert(sc);
			else if(choice==2)upadte(sc);
			else if(choice==3)delete(sc);
			else if(choice==4)display();
			else if(choice==5)displayid(sc);
			else if(choice==6)searchname(sc);
			else if(choice==7)sortd(sc);
			
			System.out.println("Welcome to my Database");
			System.out.println("1. Insert into table");
			System.out.println("2. Update data");
			System.out.println("3. Delete from table");
			System.out.println("4. Display table");
			System.out.println("5. Display using Id");
			System.out.println("6. Search into database");
			System.out.println("7. Sort and Display using name");
			System.out.println("0. EXIT");
			
			System.out.println("Enter your choice: ");
			choice=sc.nextInt();
		}

	}
}

private static void sortd(Scanner sc) throws ClassNotFoundException, SQLException {
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/adii","root","aditya");
	Statement smt=cnn.createStatement();
	System.out.println("Sorting table data using name");
	String q="SELECT * FROM people ORDER BY fname;";
	ResultSet rs=smt.executeQuery(q);
		//to print result in console
		if(rs.next())
		{
			do{
				System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getInt(4)+","+rs.getString(5));
			}while(rs.next());
		}
		else
		{
			System.out.println("No data to display...");
		}
		
	
	
}

private static void searchname(Scanner sc) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/adii","root","aditya");
	Statement smt=cnn.createStatement();
	System.out.println("Enter name you want to search in table: ");
	String name=sc.next();
	String q="Select * from people where fname like '%"+name+"%'";
	ResultSet rs=smt.executeQuery(q);
		//to print result in console
		if(rs.next())
		{
			do{
				System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getInt(4)+","+rs.getString(5));
			}while(rs.next());
		}
		else
		{
			System.out.println("Record Not Found...");
		}
		
	
	
}

private static void displayid(Scanner sc) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/adii","root","aditya");
	Statement smt=cnn.createStatement();
	System.out.println("Enter id you want to display from table: ");
	int id=sc.nextInt();
	String q="Select * from people where id = "+id;
	ResultSet rs=smt.executeQuery(q);
		//to print result in console
		if(rs.next())
		{
			do{
				System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getInt(4)+","+rs.getString(5));
			}while(rs.next());
		}
		else
		{
			System.out.println("Record Not Found...");
		}
		
	
	
}

private static void display() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/adii","root","aditya");
	Statement st=cnn.createStatement();
	String query="select * from people";
	ResultSet rs= st.executeQuery(query);
	
	while(rs.next())
	{
		System.out.println("id  "+rs.getInt(1)  +" fname "+rs.getString(2)+" lname "+rs.getString(3)+" age "+rs.getInt(4)+" City "+rs.getString(5));
		
	}
	
}

private static void delete(Scanner sc) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/adii","root","aditya");
	
		String deletequery="delete from people where id=?";
		PreparedStatement psde=cnn.prepareStatement(deletequery);
		System.out.println("ENTER ID YOU WANT TO DELETE ");
		int delid=sc.nextInt();
		psde.setInt(1, delid);
	psde.executeUpdate();
	
	System.out.println("Deleted Successfully!!");
}

private static void upadte(Scanner sc) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/adii","root","aditya");
	String updatequery="update people set fname = ? where id=?";
		PreparedStatement psup=cnn.prepareStatement(updatequery);
	System.out.println("Update id: ");
	int idup=sc.nextInt();
	System.out.println("Update fname to ");
	String nameup=sc.next();
	
	psup.setString(1,nameup);
	psup.setInt(2, idup);
	psup.executeUpdate();
	
	System.out.println("Updated Successfully!!");

	
}

private static void insert(Scanner sc) throws SQLException, ClassNotFoundException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/adii","root","aditya");
	
	String insrtquery="insert into people values(?,?,?,?,?)";
	
	int id;
	System.out.println("Enter id: ");
	id=sc.nextInt();
	
	String fname;
	System.out.println("Enter First name: ");
	fname=sc.next();
	
	String lname;
	System.out.println("Enter last name: ");
	lname=sc.next();
	int age;
	System.out.println("Enter Age: ");
	age=sc.nextInt();
	String city;
	System.out.println("Enter City: ");
	city=sc.next();
	
	PreparedStatement ps=cnn.prepareStatement(insrtquery);
	ps.setInt(1,id);
	
	ps.setString(2, fname);
	
	ps.setString(3,lname);
	
	ps.setInt(4, age);
	
	ps.setString(5, city);
	
	ps.executeUpdate();
	
	System.out.println("Data added successfully!!! ");
	
	
}
	
}
