package fr.eni.ecole.enchere.servlet;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.enchere.bll.ArticleManager;
import fr.eni.ecole.enchere.bo.Article;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Retrait;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/ServletNouvelleVente") 
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		ArticleManager am = ArticleManager.getInstance();

		try {
			List<Categorie> listeCategories = am.afficherCategories();
			request.setAttribute("categorie", listeCategories);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		Utilisateur userConnecte = (Utilisateur) session.getAttribute("userConnecte");
		if (request.getAttribute("rue") == null) {
			request.setAttribute("rue", userConnecte.getRue());
			request.setAttribute("postal", userConnecte.getCodePostal());
			request.setAttribute("ville", userConnecte.getVille());

		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/nouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recupérer les textfield
		request.setCharacterEncoding("UTF-8");
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		int noCategorie = Integer.parseInt(request.getParameter("categorie"));
		int prix = Integer.parseInt(request.getParameter("prix"));
		LocalDateTime debutVente = LocalDateTime.parse(request.getParameter("debut"));
		LocalDateTime finVente = LocalDateTime.parse(request.getParameter("fin"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("postal");
		String ville = request.getParameter("ville");

		Categorie cat = new Categorie(noCategorie, null);
		// set l'attribut catégorie pour la réafficher en cas d'erreur
		request.setAttribute("categorieChoisie", noCategorie);

		ArticleManager am = ArticleManager.getInstance();

		try {

			Retrait retraitVendeur = new Retrait(rue, codePostal, ville);
			Article art = new Article(article, description, debutVente, finVente, prix, prix, cat, "CR",
					retraitVendeur);

			HttpSession session = request.getSession();
			Utilisateur userConnecte = (Utilisateur) session.getAttribute("userConnecte");
			int id = userConnecte.getNoUtilisateur();
			am.ajouterVente(art, id);

		} catch (BusinessException e) {
			request.setAttribute("article", article);
			request.setAttribute("description", description);
			request.setAttribute("prix", prix);
			request.setAttribute("debut", debutVente);
			request.setAttribute("fin", finVente);
			request.setAttribute("rue", rue);
			request.setAttribute("postal", codePostal);
			request.setAttribute("ville", ville);
			request.setAttribute("listeErreur", e.getListeMessage());
			doGet(request, response);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueil");
		rd.forward(request, response);
	}

}
