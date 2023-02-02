package fr.eni.ecole.enchere.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.enchere.bo.Enchere;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnecte")
public class ServletConnecteAchats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<Enchere> listeEncheres = new ArrayList<>();
	
	
	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
				
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/userConnecteAchats.jsp");
		rd.forward(request, response);
	}

}
