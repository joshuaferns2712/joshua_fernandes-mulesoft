package net.codejava;


import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class query {
   

	private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:/C:\\\\SQLite\\\\sqlite-tools-win32-x86-3380100\\\\movies.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    
    public void selectAll(){
		String jdbcUrl = "jdbc:sqlite:/C:\\SQLite\\sqlite-tools-win32-x86-3380100\\movies.db";

        try 
        {
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
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void selectByActor(String actor_name) {
        String sql = "SELECT * FROM moviedetails WHERE lead_actor = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,actor_name);
            
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                		  rs.getString("movie_name") + " | " +
                          rs.getString("lead_actor") + " | " +
                          rs.getString("lead_actress") + " | " +
                          rs.getInt("release_year") + " | " +
                          rs.getString("director"));
   
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectByActress(String actress_name) {
        String sql = "SELECT * FROM moviedetails WHERE lead_actress = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,actress_name);
            
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                		  rs.getString("movie_name") + " | " +
                          rs.getString("lead_actor") + " | " +
                          rs.getString("lead_actress") + " | " +
                          rs.getInt("release_year") + " | " +
                          rs.getString("director"));
   
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        query data = new query();
        
        int choice;
        System.out.println("Options:\n1. Show all\n2. Select by Actor\n3. Select by Actress");
        Scanner in = new Scanner(System.in);
        
            System.out.print("Enter your choice: ");
            choice = in.nextInt();
            in.nextLine();
            switch(choice)
            {
                case 1:data.selectAll();
                	break;
                case 2: System.out.print("\nEnter actor's name: ");
                	String actor_name=in.next();
                	data.selectByActor(actor_name);
                	break;
                case 3: System.out.print("\nEnter actress' name: ");
                    String actress_name=in.next();
                    data.selectByActress(actress_name);
                    break;
                default: System.out.println("Invalid choice");
            }
       
    }
}