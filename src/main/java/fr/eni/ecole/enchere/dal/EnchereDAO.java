package fr.eni.ecole.enchere.dal;

import java.util.List;

import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.exception.BusinessException;

public interface EnchereDAO {
	
	

	public List<Enchere> afficherEncheres(int idUser, String categorie, String nomArticle, boolean choixRadiobutton, List<Boolean> listCheckBox) throws BusinessException;
	
	public List<Enchere> afficherAllEncheres(int userId, String categorie, String nomArticle) throws BusinessException;
	
	public void insertEnchere (Enchere enchere, int idArticle) throws BusinessException;
	
	
}
