package fr.eni.ecole.enchere.bll;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.eni.ecole.enchere.bo.Article;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.ArticleDAO;
import fr.eni.ecole.enchere.dal.DAOFactory;
import fr.eni.ecole.enchere.exception.BusinessException;

public class ArticleManager {
	
	private ArticleDAO articleDAO;
	private static ArticleManager mgr;
	
	private ArticleManager() {

		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	public static ArticleManager getInstance() {
		if(mgr == null) {
			mgr = new ArticleManager();
		}
		return mgr;
	}

	public List<Categorie> afficherCategories() throws BusinessException {
		List<Categorie> categoriesListe = articleDAO.selectCategories();
		return categoriesListe;
	}
	
	public void ajouterVente(Article article, int id) throws BusinessException {
		validerArticle(article);
		articleDAO.ajouterVente(article, id);
		 
	}
	
	
	private void validerArticle(Article article) throws BusinessException {
		
		BusinessException be = new BusinessException();
	
	
	
		if (article.getDateDebutEncheres().isBefore(LocalDate.now()) || article.getDateFinEncheres().isBefore(LocalDate.now())) {
			be.addMessage("la date est antérieur à la date du jour");
		}
		
		
		 if (article.getDateDebutEncheres().isAfter(article.getDateFinEncheres())) {
			 be.addMessage("la date de début ne doit pas être ultérieur à la date de fin");
		 }
		
		
		if (article.getNomArticle().isBlank() ) {
			be.addMessage("Le nom de l'article est obligatoire.\n");
        }

        if (article.getDescription().isBlank()) {
        	be.addMessage("La description est obligatoire.\n");
        }
       
        
        if (article.getPrixInitial() <= 0) {
        	be.addMessage("Le prix doit être obligatoire.\n");
        }
        
        
        if (article.getRetraitVendeur().getRue().isBlank()) {
        	be.addMessage("La rue est obligatoire.\n");
        }
        
        if (article.getRetraitVendeur().getCodePostal().isBlank()) {
        	be.addMessage("Le code postal est obligatoire.\n");
        }
        
        if (article.getRetraitVendeur().getVille().isBlank()) {
        	be.addMessage("La ville est obligatoire.\n");
        }
        
//      
        
       System.out.println("servlet valider user");
     
        if(!be.getListeMessage().isEmpty()) {
        
        	throw be;
        	
        }
	}

	
	public LocalDate convretirVersLocalDate(Date dateAconvertir) {
	    return dateAconvertir.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
}

