package fr.eni.ecole.enchere.bll;

import java.util.List;

import fr.eni.ecole.enchere.bo.Article;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.ArticleDAO;
import fr.eni.ecole.enchere.dal.DAOFactory;
import fr.eni.ecole.enchere.exception.BusinessException;

public class ArticleManager {
	
	private ArticleDAO articleDAO;

	
	public ArticleManager() {

		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	
	public List<Categorie> afficherCategories() throws BusinessException {
		List<Categorie> categoriesListe = articleDAO.selectCategories();
		System.out.println("manager");
		return categoriesListe;
	}
	
	public void ajouterVente(Article article, int id) throws BusinessException {
		articleDAO.ajouterVente(article, id);
		 
	}
	
}

