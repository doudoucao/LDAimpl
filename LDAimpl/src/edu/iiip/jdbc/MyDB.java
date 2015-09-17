package edu.iiip.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDB {

	public static void main(String[] args) {
	  
		Connection conn = DBUtil.open();
		int i = 1;
		String sql = "select text from weibo";
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				String result = rs.getString(1);
				System.out.println((i++)+result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
