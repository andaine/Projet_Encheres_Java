package fr.eni.ecole.enchere.servlet;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
			List<Enchere> listeEncheres = mgr.afficherAllEncheres(0, null, null);
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

		EnchereManager mgr = EnchereManager.getInstance();
		HttpSession session = req.getSession();
		Utilisateur userConnecte = (Utilisateur) session.getAttribute("userConnecte");
		int userId;
		if (userConnecte == null) {
			userId = 0;
		} else {
			userId = userConnecte.getNoUtilisateur();
		}

		// recup catégorie choisie
		String categorieChoisie = req.getParameter("selectCategorie");
		req.setAttribute("categorieChoisie", categorieChoisie);
		System.out.println("test = categorie : " + categorieChoisie);

		// recup le champ de texte
		String textFieldResult = req.getParameter("textFiltreArticle");
		System.out.println("test = article : " + textFieldResult);
		
		//recup le radiobouton
		boolean radioButton = true;	
		String rbAchats = null;
		if (req.getParameter("radioButton") != null) {
			 rbAchats =  req.getParameter("radioButton");			
			 if (rbAchats.equals("radioVente")) {
				 radioButton = false;
			 }
		}
		System.out.println(rbAchats);
		System.out.println( "/" + req.getParameter("radioButton") + "/"+ " filtreRadioButton");
		System.out.println(radioButton);
//		if (rbAchats == "false") {
//			radioButton = false;
//		} 
		
		//recup checkbox
		List<Boolean> listCheckbox = new ArrayList<>();
		String Cb1;
		String Cb2;
		String Cb3;
		if (radioButton == true){
			 Cb1 = req.getParameter("encheresOuvertes");
			 Cb2 = req.getParameter("mesEncheres");		
			 Cb3 = req.getParameter("encheresRemportes");
		} else {
			Cb1 = req.getParameter("ventesEnCours");
			 Cb2 = req.getParameter("ventesNonDebutees");		
			 Cb3 = req.getParameter("ventesTerminees");
		}
		
		
		Boolean Cb1Boolean = false;
		Boolean Cb2Boolean = false;
		Boolean Cb3Boolean = false;
		
		if (Cb1 != null) {
			Cb1Boolean = true;
		}
		if (Cb2 != null) {
			Cb2Boolean = true;
		}
		if (Cb3 != null) {
			Cb3Boolean = true;
		}
		
		listCheckbox.add(Cb1Boolean);
		listCheckbox.add(Cb2Boolean);
		listCheckbox.add(Cb3Boolean);
		
		
		try {
			List<Enchere> listeEncheresFiltres = mgr.afficherEncheres(userId, categorieChoisie, textFieldResult, radioButton, listCheckbox);
			
			System.out.println("333333333333333333333333333");
			for (Enchere enchere : listeEncheresFiltres) {
				System.out.println(enchere);
			}
			System.out.println("333333333333333333333333333");

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
