package srv;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticoloDao;
import model.Articolo;

public class ArticoloSrv extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_TOP = ""
		    + "<html>"
		    + "<head>"
		    + "<title> Servlet Database </title>"
		    + "</head>"
		    + "<body>"
		;

		//fine pagina
		private static final String PAGE_BOTTOM = ""
			+ "<br><br><br><br><br>"
			+ "<center> <footer> Copyright Sara Esposito </footer> </center>\r\n"
		    + "</body>"
		    + "</html>"
	    ;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException
	{
	   
	    ArticoloDao aDao = new ArticoloDao();
	    
	    String descrizione = request.getParameter("descrizione");
	    String quant = request.getParameter("quantita");
	    int quantita = Integer.parseInt(quant);
	    
	    
	    Articolo a = new Articolo();
	    
	    a.setDescrizione(descrizione);
	    a.setQuantita(quantita);
	    
	    aDao.insert(a);
	    
	    select(a, request, response);
	     
	}

	
	public void select(Articolo a, HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		String url = "jdbc:mysql://localhost:3306/esercizio_servlet";
	    String user = "adminsara";
	    String password = "admin";
	    String driver = "com.mysql.cj.jdbc.Driver";

	    Writer w = response.getWriter();
        
	    w.write(PAGE_TOP);
	    
	    Connection conn = null;

	    try 
	    {
	        Class.forName(driver);
	        
	        conn = DriverManager.getConnection(url, user, password);
	        
	        System.out.println("Connesso correttamente al database");
	        
	        String query_da_eseguire = "SELECT * FROM articolo";
	        
	        PreparedStatement stmt = conn.prepareStatement(query_da_eseguire);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        w.write("<center> <h3> Tabella Articolo </h3> </center>");
            
            w.write("<center> <table border=1 width=50% height=50%> </center>");  
            
            w.write(  "<tr>"
            		+ "<th> CODICE </th>"
            		+ "<th> DESCRIZIONE </th>"
            		+ "<th> QUANTITA </th>"
            		+ "<tr>");  
            
	        while(rs.next())
	        {
	        	w.write(  "<tr>"
	        			+ "<td> <center>" + rs.getInt("codice") + "</center> </td>"
	        			+ "<td> <center>" + rs.getString("descrizione") + "</center> </td>"
	        			+ "<td> <center>" + rs.getInt("quantita") + "</center> </td>"
	        			+ "</tr>");
	        }
	        
	        w.write("</table>");
	        
	        stmt.close();
	        
	    } catch (ClassNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }finally {
	    	try 
	    	{
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    w.write(PAGE_BOTTOM);
	    
	}
	
	
}
