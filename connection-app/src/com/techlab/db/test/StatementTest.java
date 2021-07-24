package com.techlab.db.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class StatementTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swabhav?"+"user=root&password=root");

		Statement stmt = (Statement) con.createStatement();
		int updated=0;
		int deleted=0;

		System.out.println("Before : ");
		showTable(con);

		System.out.println();
		System.out.println("After inserting emp abc row :");
		updated+=stmt.executeUpdate("INSERT INTO EMP1 VALUES (1001,'abc','Developer',NULL,'1-AUG-21',4500,NULL,10);");
		//updated++;
		showTable(con);
		System.out.println();

		System.out.println("After updating manager id of emp abc to 7839 :");
		updated+=stmt.executeUpdate("UPDATE EMP1 SET MGR=7839 WHERE EMPNO=1001;");
		//updated++;
		showTable(con);
		System.out.println();	

		System.out.println("After deleting record of empno 1001  :");
		deleted+=stmt.executeUpdate("DELETE FROM EMP1 WHERE EMPNO=1001;");
		//deleted++;
		showTable(con);
		System.out.println();	

		System.out.println("No of updations : "+updated);
		System.out.print(" No of deletions : "+deleted);

		stmt.close();
		if(!con.isClosed())
			con.close();

	}

	private static void showTable(Connection con) throws SQLException {
		System.out.println();
		Statement stmt = (Statement) con.createStatement();
		System.out.println("Displaying EMP table from SWABHAV : ");
		ResultSet rs=stmt.executeQuery("SELECT * FROM EMP1;");
		while(rs.next())
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getInt(6)+" "+rs.getString(7)+" "+rs.getInt(8));

		rs.close();
	}
}
