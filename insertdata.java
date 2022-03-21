package net.codejava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class insertdata {

   
    private Connection connect() {
        // SQLite connection string
		String jdbcUrl = "jdbc:sqlite:/C:\\SQLite\\sqlite-tools-win32-x86-3380100\\movies.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    
    public void insert (String movie_name,String lead_actor,String lead_actress, Integer release_year,String director) {
        String sql = "INSERT INTO moviedetails (Movie_Name,Lead_Actor,Lead_Actress,release_year, director) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            	pstmt.setString(1, movie_name);
            	pstmt.setString(2, lead_actor);
            	pstmt.setString(3, lead_actress);
		
            	pstmt.setInt(4, release_year);
            	pstmt.setString(5, director);
            	pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        insertdata data = new insertdata();
        
        data.insert("shawshank_redemption", "morgan_freeman","andy dufresne", 2009, "tim");
        data.insert("transformers","shia_laboef","stanley_tucci",2011, "michael_bay");
        data.insert("insception","leonardo_dicaprio","gordon_lewitt",2016, "christopher_nolan");
	
    }


}

