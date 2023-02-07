package fr.eni.ecole.enchere.dal;

import java.util.List;

import fr.eni.ecole.enchere.bo.Article;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.exception.BusinessException;

public interface EnchereDAO {
	
	

	public List<Enchere> afficherEncheres(int idUser, Categorie categorie, Article article) throws BusinessException;
	
}
