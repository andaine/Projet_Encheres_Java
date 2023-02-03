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

import fr.eni.ecole.enchere.bll.EnchereManager;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.exception.BusinessException;

import fr.eni.ecole.enchere.bll.ArticleManager;
import fr.eni.ecole.enchere.bll.UserManager;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.exception.BusinessException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doget - servlet accueil");

		EnchereManager mgr = new EnchereManager();

		try {
			List<Enchere> listeEncheres = mgr.afficherAllEncheres();
			request.setAttribute("listeEncheres", listeEncheres);
			for (Enchere e : listeEncheres) {
				System.out.println("Servlet encheres : " + e.getMontantEnchere());
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("ca marche pas");
		}

		request.setCharacterEncoding("UTF-8");

		ArticleManager am = new ArticleManager();

		try {
			List<Categorie> listeCategories = am.afficherCategories();

			request.setAttribute("categorie", listeCategories);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		System.out.println("servletAccueil");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("dopost - servlet accueil");

		EnchereManager mgr = new EnchereManager();
		try {
			List<Enchere> listeEncheres = mgr.afficherAllEncheres();
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

		} catch (BusinessException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("Aucune enchère pour cette catégorie");		
		}
		
		ArticleManager am = new ArticleManager();

		try {
			List<Categorie> listeCategories = am.afficherCategories();

			req.setAttribute("categorie", listeCategories);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
		rd.forward(req, resp);
		
	}

}
