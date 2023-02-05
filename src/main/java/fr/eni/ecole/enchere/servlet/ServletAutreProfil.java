package fr.eni.ecole.enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.enchere.bll.UserManager;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

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
		
//		String nomAutreUser =  (String) request.getParameter("nom");
//		UserManager user = new UserManager();
//		Utilisateur infoVendeur;
//		try {
//			infoVendeur = user.afficherUtilisateur(nomAutreUser);
//			System.out.println(infoVendeur.getPseudo());
//			request.setAttribute("infoVendeur", infoVendeur);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/autreProfil.jsp");
		rd.forward(request, response);
	}

}
