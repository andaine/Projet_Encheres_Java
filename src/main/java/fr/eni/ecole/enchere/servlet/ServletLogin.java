package fr.eni.ecole.enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.enchere.bll.EnchereManager;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("dopost - servlet login");
		
		if (request.getParameter("identifiant") != null && request.getParameter("mdp") != null) {
			
			String pseudo = request.getParameter("identifiant");
			String mdp = request.getParameter("mdp");
			
//			System.out.println(pseudo + " : " + mdp );
			
			EnchereManager em = new EnchereManager();
			Utilisateur user;
			try {
				user = em.validerUtilisateur(pseudo, mdp);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("erreur servlet");
			}
		} else {
//			throw new BusinessException();
		}
	}

}
