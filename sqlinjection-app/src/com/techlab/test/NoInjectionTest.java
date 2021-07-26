package com.techlab.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.mysql.jdbc.PreparedStatement;

public class NoInjectionTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:4040/swabhav?"+"user=root&password=root");

		PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT EMPNO,ENAME FROM EMP1 WHERE EMPNO=?;");
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter empNo : ");
		String empNo=sc.nextLine();
		
		sc.close();
		
		System.out.println();
		System.out.println("Displaying EMP table from SWABHAV : ");
		ps.setString(1, empNo);
		
		ResultSet rs=ps.executeQuery();
		while(rs.next())
			System.out.println(rs.getInt(1)+" "+rs.getString(2));

		rs.close();
		
		ps.close();
		if(!con.isClosed())
			con.close();
		
		
	}

}
