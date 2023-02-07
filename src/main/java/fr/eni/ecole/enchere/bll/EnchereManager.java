package fr.eni.ecole.enchere.bll;

import java.util.Iterator;
import java.util.List;

import fr.eni.ecole.enchere.bo.Article;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.dal.DAOFactory;
import fr.eni.ecole.enchere.dal.EnchereDAO;
import fr.eni.ecole.enchere.exception.BusinessException;

public class EnchereManager {

	private EnchereDAO enchereDAO;
	private static EnchereManager mgr;
	
	private EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
		
	}
	
	public static EnchereManager getInstance() {
		if(mgr == null) {
			mgr = new EnchereManager();
		}
		return mgr;
	}
	public List<Enchere> afficherEncheres(int userId, String categorie, String article) throws BusinessException{
		
		List<Enchere> listeEncheres = enchereDAO.afficherEncheres(userId, categorie, article);
		return listeEncheres;
	}
	
	public void insererEnchere (Enchere enchere, int idArticle) throws BusinessException {
		enchereDAO.insertEnchere(enchere, idArticle);
	}
}
