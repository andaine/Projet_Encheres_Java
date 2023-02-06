package fr.eni.ecole.enchere.bll;

import java.util.List;

import fr.eni.ecole.enchere.bo.Categorie;
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
	
}

