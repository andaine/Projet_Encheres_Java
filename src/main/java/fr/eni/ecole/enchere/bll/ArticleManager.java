package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.dal.ArticleDAO;
import fr.eni.ecole.enchere.dal.DAOFactory;

public class ArticleManager {
	
	private ArticleDAO articleDAO;

	
	public ArticleManager() {

		this.articleDAO = DAOFactory.getArticleDAO();
	}
}
