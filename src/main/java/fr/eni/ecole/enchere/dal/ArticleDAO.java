package fr.eni.ecole.enchere.dal;

import java.util.List;

import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.exception.BusinessException;

public interface ArticleDAO {

	public List<Categorie>selectCategories() throws BusinessException;
	
	
}
