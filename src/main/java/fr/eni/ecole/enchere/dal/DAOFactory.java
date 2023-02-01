package fr.eni.ecole.enchere.dal;

public abstract class DAOFactory {

	public static UserDAO getUserDAO() {
		return new UserDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	
	
}
