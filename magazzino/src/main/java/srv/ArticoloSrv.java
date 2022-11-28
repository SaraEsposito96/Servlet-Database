package srv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticoloDao;
import model.Articolo;

public class ArticoloSrv extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
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
	    
	    response.getWriter().append("Served at: ").append(request.getContextPath());
	    
	}

}
