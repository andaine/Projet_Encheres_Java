package fr.eni.ecole.enchere.servlet;

import java.io.IOException;
import java.time.LocalDate;

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
import fr.eni.ecole.enchere.bo.Article;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/ServletDetailVente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArticleManager am = ArticleManager.getInstance();
		
		int idArticle=Integer.parseInt(request.getParameter("id"));
		
		try {
			Article infoArt = am.afficherArticle(idArticle);
			System.out.println("ID article = " + idArticle + "-----------------------------------------");
			System.out.println(infoArt.getNomArticle());
			request.setAttribute("infoArt", infoArt);	
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//insertion enchère
		request.setCharacterEncoding("UTF-8");
		int prixPropose  = Integer.parseInt(request.getParameter("prixPropose")) ;
		int noArticle  = Integer.parseInt(request.getParameter("noArticle")) ;
		HttpSession session = request.getSession();
		Utilisateur userConnecte = (Utilisateur) session.getAttribute("userConnecte");
		int idUserConnecte = userConnecte.getNoUtilisateur();
		
		EnchereManager em = EnchereManager.getInstance();
		Enchere enchere = new Enchere(LocalDate.now(), prixPropose, idUserConnecte, noArticle);
		
		//update dé-crédits user
		UserManager um = UserManager.getInstance();
		int newCreditUser = userConnecte.getCredit()- prixPropose;
		userConnecte.setCredit(newCreditUser);
	
		try {
			em.insererEnchere(enchere, noArticle);
			um.updateCredit(userConnecte);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
		rd.forward(request, response);
	}

}
