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
import javax.servlet.http.HttpSession;

import fr.eni.ecole.enchere.bll.EnchereManager;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnecteAchats")
public class ServletConnecteAchats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Utilisateur userConnecte = (Utilisateur) session.getAttribute("userConnecte");
		int id = userConnecte.getNoUtilisateur();
		System.out.println(" -------------------------------servlet connecte achats");
		System.out.println(id);
		EnchereManager mgr = new EnchereManager();

		try {
			List<Enchere> listeAutresEncheres = mgr.afficherAutresEncheres(id);
			request.setAttribute("listeEncheres", listeAutresEncheres);
			for(Enchere e : listeAutresEncheres) {
				System.out.println("Servlet encheres : " + e.getMontantEnchere());
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("ca marche pas");
		}
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/userConnecteAchats.jsp");
		rd.forward(request, response);
	}

}
