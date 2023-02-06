package fr.eni.ecole.enchere.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		ArticleManager am = new ArticleManager();

		try {
			List<Categorie> listeCategories = am.afficherCategories();

			request.setAttribute("categorie", listeCategories);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/nouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		ArticleManager am = new ArticleManager();
		try {
			validerArticle(request);
			String article = request.getParameter("pseudo");
			String description = request.getParameter("nom");
			int noCategorie = Integer.parseInt(request.getParameter("categorie")) ;
			int prix  = Integer.parseInt(request.getParameter("prix")) ;
			LocalDate debutVente = LocalDate.parse(request.getParameter("debut"), DateTimeFormatter.ISO_DATE);
			LocalDate finVente = LocalDate.parse(request.getParameter("fin"), DateTimeFormatter.ISO_DATE);
			
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("postal");
			String ville = request.getParameter("ville");
			
			Retrait retraitVendeur = new Retrait(rue, codePostal, ville);
			
			Article art = new Article(article, description, debutVente, finVente, prix, prix, noCategorie, "CR", retraitVendeur);
			HttpSession session = request.getSession();
			Utilisateur userConnecte = (Utilisateur) session.getAttribute("userConnecte");
			int id = userConnecte.getNoUtilisateur();
			
			
			
			am.ajouterVente(art, id);
		} catch (BusinessException e) {
			request.setAttribute("listeErreur", e.getListeMessage());
		}
		

		RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueil");
		rd.forward(request, response);
	}
	
	
	protected void validerArticle(HttpServletRequest request) throws BusinessException {
		
		BusinessException be = new BusinessException();
		
		
		if (request.getParameter("article").isEmpty()) {
			be.addMessage("Le nom de l'article est obligatoire.\n");
        }

        if (request.getParameter("description").isEmpty()) {
        	be.addMessage("La description est obligatoire.\n");
        }
       
        
        if (request.getParameter("prix").isEmpty()) {
        	be.addMessage("Le prix doit être obligatoire.\n");
        }
        
        if (request.getParameter("debut").isEmpty()) {
        	be.addMessage("La date de début est obligatoire.\n");
        }
        
        if (request.getParameter("fin").isEmpty()) {
        	be.addMessage("La date de fin est obligatoire.\n");
        }
        
        if (request.getParameter("rue").isEmpty()) {
        	be.addMessage("La rue est obligatoire.\n");
        }
        
        if (request.getParameter("postal").isEmpty()) {
        	be.addMessage("Le code postal est obligatoire.\n");
        }
        
        if (request.getParameter("ville").isEmpty()) {
        	be.addMessage("La ville est obligatoire.\n");
        }
        
//      
        
       System.out.println("servlet valider user");
     
        if(!be.getListeMessage().isEmpty()) {
        
        	throw be;
        	
        }
	}

}
