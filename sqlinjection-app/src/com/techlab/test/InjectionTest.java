package com.techlab.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class InjectionTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:4040/swabhav?"+"user=root&password=root");

		Statement stmt = (Statement) con.createStatement();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter empNo : ");
		String empNo=sc.nextLine();
		
		System.out.println();
		System.out.println("Displaying EMP table from SWABHAV : ");
		ResultSet rs=stmt.executeQuery("SELECT EMPNO,ENAME FROM EMP1 WHERE EMPNO="+empNo+";");
		while(rs.next())
			System.out.println(rs.getInt(1)+" "+rs.getString(2));

		rs.close();
		
		stmt.close();
		if(!con.isClosed())
			con.close();
		
	}
	
}
