package com.techlab.db.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectTest {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//load specific driver class
		Class.forName("com.mysql.jdbc.Driver");

		//connection
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/swabhav?"+"user=root&password=root");
		System.out.println("Connection established successfully");
		
		System.out.println("Class Name : "+con.getClass());

		System.out.println("Timeout : "+con.getNetworkTimeout());
		
		DatabaseMetaData metaData = con.getMetaData();
		String product_name = metaData.getDatabaseProductName();
		System.out.println("Server name : "+product_name);
		
		System.out.println("Database name : "+con.getCatalog());
		
		//dispose
		if(!con.isClosed())
		{
			con.close();
			System.out.println("Connection closed successfully");
		}	
	}
}
