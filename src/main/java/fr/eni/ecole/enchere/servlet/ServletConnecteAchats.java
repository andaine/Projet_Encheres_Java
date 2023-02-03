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

import fr.eni.ecole.enchere.bll.ArticleManager;
import fr.eni.ecole.enchere.bll.EnchereManager;
import fr.eni.ecole.enchere.bll.UserManager;
import fr.eni.ecole.enchere.bo.Categorie;
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
			for (Enchere e : listeAutresEncheres) {
				System.out.println("Servlet encheres : " + e.getMontantEnchere());
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("ca marche pas");
		}

		ArticleManager am = new ArticleManager();

		try {
			List<Categorie> listeCategories = am.afficherCategories();

			request.setAttribute("categorie", listeCategories);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/userConnecteAchats.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("dopost - servlet accueil");

		EnchereManager mgr = new EnchereManager();
		HttpSession session = req.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("userConnecte");
		
		try {
			int id = u.getNoUtilisateur();
			List<Enchere> listeEncheres = mgr.afficherAutresEncheres(id);
			List<Enchere> listeEncheresAGarder = new ArrayList<>();
			// recup catégorie choisie
			String categorieChoisie = req.getParameter("selectCategorie");
			System.out.println("categorie choisie : " +categorieChoisie);
			
			for (Enchere e : listeEncheres) {
				System.out.println("categorie dispo : " + e.getNomCategorie());
				if (e.getNomCategorie().equals(categorieChoisie)) {
					listeEncheresAGarder.add(e);
				}
			}
			req.setAttribute("listeEncheres", listeEncheresAGarder);
			req.setAttribute("categorieChoisie", categorieChoisie);


		} catch (BusinessException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("Aucune enchère pour cette catégorie");		
		}
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/userConnecteAchats.jsp");
		rd.forward(req, resp);
	}
}
