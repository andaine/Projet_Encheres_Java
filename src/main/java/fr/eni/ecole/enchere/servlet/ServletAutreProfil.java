package fr.eni.ecole.enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.enchere.bll.UserManager;

/**
 * Servlet implementation class ServletAutreProfil
 */
@WebServlet("/ServletAutreProfil")
public class ServletAutreProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomAutreUser = (String) request.getAttribute("nomAutreUser");
		System.out.println("test servlet autreUser " + nomAutreUser);
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/autreProfil.jsp");
		rd.forward(request, response);
	}

}
