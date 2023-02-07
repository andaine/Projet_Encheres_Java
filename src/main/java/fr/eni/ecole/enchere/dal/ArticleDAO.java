package fr.eni.ecole.enchere.dal;

import java.util.List;

import fr.eni.ecole.enchere.bo.Article;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.exception.BusinessException;

public interface ArticleDAO {

	public List<Categorie>selectCategories() throws BusinessException;

	public void ajouterVente(Article article, int id) throws BusinessException;
	
	public Article afficherArticle(int idArticle) throws BusinessException;
	

}
