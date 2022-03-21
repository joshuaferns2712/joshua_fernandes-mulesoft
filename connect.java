package net.codejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connect {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:sqlite:/C:\\SQLite\\sqlite-tools-win32-x86-3380100\\movies.db";
	try {
		Connection connection = DriverManager.getConnection(jdbcUrl);
		String sql = "SELECT * FROM moviedetails";
		
		Statement statement = connection.createStatement();
		statement.executeQuery(sql);
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next())
		{
			String movie_name = result.getString("movie_name");
			String lead_actor = result.getString("lead_actor");
			String lead_actress = result.getString("lead_actress");
			String release_year = result.getString("release_year");
			String director = result.getString("director");
			
			System.out.println(movie_name +  " | "+ lead_actor + " | " + lead_actress + " | " + release_year + " | "+ director);
		}
	}
	catch(SQLException e) {
		System.out.println("Error connecting to SQLite database");
		e.printStackTrace();
	
	}
	}

}
