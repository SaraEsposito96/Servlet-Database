package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Articolo;

public class ArticoloDao {

	public void insert(Articolo a)
	{
		String url = "jdbc:mysql://localhost:3306/esercizio_servlet";
	    String user = "adminsara";
	    String password = "admin";
	    String driver = "com.mysql.cj.jdbc.Driver";

	    Connection conn = null;

	    try 
	    {
	        Class.forName(driver);
	        
	        conn = DriverManager.getConnection(url, user, password);
	        
	        System.out.println("Connesso correttamente al database");
	        
	        String query_da_eseguire = "INSERT INTO articolo(descrizione, quantita) VALUES (?,?)";
	        
	        PreparedStatement stmt = conn.prepareStatement(query_da_eseguire);
	        
	        stmt.setString(1, a.getDescrizione());
	        stmt.setInt(2, a.getQuantita());
	        
	        stmt.executeLargeUpdate();
	        
	        stmt.close();
	        
	    } catch (ClassNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	}
}
	