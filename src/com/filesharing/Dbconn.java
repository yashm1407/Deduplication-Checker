package com.filesharing;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class Dbconn {
 public static int key=0;
	public Dbconn() throws SQLException {
		// initComponents();
		// Connection con;

	}

	public static Connection conn() throws SQLException, ClassNotFoundException {
		Connection con;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/secure_encrypted_data", "root", "admin");

		return (con);

	}

//	public static String server(String DbName) throws SQLException,
//			ClassNotFoundException {
//		System.out.println("Server Selected Name " + DbName);
//		String Db="data";
//		if (DbName.equals("Server1"))
//			Db = "data1";
//		else if (DbName.equals("Server2"))
//			Db = "data2";
//		else if (DbName.equals("Server3"))
//			Db = "data3";
//		else if (DbName.equals("Server4"))
//			Db = "data4";
//		System.out.println("Server Selected " + Db);
//
//		return (Db);
//
//	}

}
