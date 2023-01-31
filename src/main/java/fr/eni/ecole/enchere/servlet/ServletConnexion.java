package fr.eni.ecole.enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.enchere.exception.BusinessException;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget - servlet accueil");
		request.setCharacterEncoding("UTF-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/userConnecte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doget - servlet accueil");
		request.setCharacterEncoding("UTF-8");
		
		if (request.getAttribute("identifiant") != null || request.getAttribute("mdp") != null) {
			
		} else {
//			throw new BusinessException();
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/userConnecte.jsp");
		rd.forward(request, response);
		
		
		
		
		
		
		
	}

}
