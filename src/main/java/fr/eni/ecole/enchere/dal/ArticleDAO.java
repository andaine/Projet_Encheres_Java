package fr.eni.ecole.enchere.dal;

import fr.eni.ecole.enchere.exception.BusinessException;

public interface ArticleDAO {

	public void selectCategories() throws BusinessException;
	
	
}
