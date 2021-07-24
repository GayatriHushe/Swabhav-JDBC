package com.techlab.db.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class TransactionTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction?"+"user=root&password=root");


		//		System.out.println("With transaction \n");
		//		System.out.println("After customer with id MN spending 200 rupees :");
		//
				




		Scanner sc=new Scanner(System.in);

		System.out.println("Enter purchase amount : ");
		int amt=sc.nextInt();

		System.out.println("Enter customer id : ");
		int cid=sc.nextInt();

		System.out.println("Enter merchant id : ");
		int mid=sc.nextInt();

		Statement stmt = (Statement) con.createStatement();

		System.out.println("__________________________________________________");
		System.out.println("Without transaction \n");

		System.out.println("Before : ");
		showTables(con);
		
		stmt.executeUpdate("UPDATE CUSTOMER SET BAL=(BAL-"+amt+") WHERE ID="+cid+";");
		stmt.executeUpdate("UPDATE MERCHANT SET BAL=(BAL+"+amt+") WHERE ID="+mid+";");
		
		System.out.println("After : ");
		showTables(con);
		
		System.out.println("__________________________________________________");
		System.out.println("With transaction \n");

		System.out.println("Before : ");
		showTables(con);
		
		con.setAutoCommit(false);
	    try (Statement stmt1 = (Statement) con.createStatement()) {
	    	int u1=stmt.executeUpdate("UPDATE CUSTOMER SET BAL=(BAL-"+amt+") WHERE ID="+cid+";");
	    	int u2=stmt.executeUpdate("UPDATE MERCHANT SET BAL=(BAL+"+amt+") WHERE ID="+mid+";");
			if((u1==0) || (u2==0))
			{
				con.rollback();
				throw new SQLException("Transaction not done");
			}
	        con.commit();
	        stmt1.close();
	        System.out.println("Transaction done successfully");
	    } catch (SQLException e) {
	        con.rollback();
	        System.out.println("Transaction not done");
	    }
	   		
		System.out.println("\nAfter : ");
		showTables(con);
		
		if(!con.isClosed())
			con.close();

	}

	private static void showTables(Connection con) throws SQLException {
		
		System.out.println();	

		Statement stmt1=(Statement) con.createStatement();
		System.out.println("Displaying CUSTOMER table from TRANSACTION : ");
		ResultSet rs1=stmt1.executeQuery("SELECT * FROM CUSTOMER;");
		while(rs1.next())
			System.out.println(rs1.getInt(1)+" "+rs1.getString(2)+" "+rs1.getInt(3));

		System.out.println();

		System.out.println("Displaying MERCHANT table from TRANSACTION : ");
		ResultSet rs2=stmt1.executeQuery("SELECT * FROM MERCHANT;");
		while(rs2.next())
			System.out.println(rs2.getInt(1)+" "+rs2.getString(2)+" "+rs2.getInt(3));

		System.out.println();

		rs1.close();
		rs2.close();
		stmt1.close();
	}

}
