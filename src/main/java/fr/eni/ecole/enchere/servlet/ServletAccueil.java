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
import javax.websocket.Session;

import fr.eni.ecole.enchere.bll.EnchereManager;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.bo.Utilisateur;
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
		System.out.println("doget - servlet accueil\n");

		EnchereManager mgr = EnchereManager.getInstance();

		try {
			List<Enchere> listeEncheres = mgr.afficherEncheres(0, null, null);
			request.setAttribute("listeEncheres", listeEncheres);
		} catch (BusinessException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("Impossible d'afficher la liste des enchères");
		}

		request.setCharacterEncoding("UTF-8");

		ArticleManager am = ArticleManager.getInstance();
				

		try {
			List<Categorie> listeCategories = am.afficherCategories();

			request.setAttribute("categorie", listeCategories);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("dopost - servlet accueil\n");

		req.setCharacterEncoding("UTF-8");
		EnchereManager mgr = EnchereManager.getInstance();
		HttpSession session = req.getSession();
		Utilisateur userConnecte = (Utilisateur) session.getAttribute("userConnecte");
		int userId;
		if(userConnecte == null) {
			userId = 0;
		}else {
			userId = userConnecte.getNoUtilisateur();
		}
		
			// recup catégorie choisie
			String categorieChoisie = req.getParameter("selectCategorie");
			req.setAttribute("categorieChoisie", categorieChoisie);
			System.out.println("test = categorie : " + categorieChoisie);

			// recup le champ de texte
			String textFieldResult = req.getParameter("textFiltreArticle");
			System.out.println("test = article : " + textFieldResult);
			
			try {
				List<Enchere> listeEncheresFiltres = mgr.afficherEncheres(userId, categorieChoisie, textFieldResult);
				req.setAttribute("listeEncheres", listeEncheresFiltres);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
			
			

		
		ArticleManager am = ArticleManager.getInstance();

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
