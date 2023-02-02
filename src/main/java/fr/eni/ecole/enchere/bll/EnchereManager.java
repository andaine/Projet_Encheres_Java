package fr.eni.ecole.enchere.bll;

import java.util.Iterator;
import java.util.List;

import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.dal.DAOFactory;
import fr.eni.ecole.enchere.dal.EnchereDAO;
import fr.eni.ecole.enchere.exception.BusinessException;

public class EnchereManager {

	private EnchereDAO enchereDAO;
	
	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
		
	}
	
	public List<Enchere> afficherEncheres() throws BusinessException{
			
		List<Enchere> listeEncheres = enchereDAO.afficherEncheres();
		for(Enchere e : listeEncheres) {
			System.out.println("BLL encheres : " + e.getMontantEnchere());
		}
		return listeEncheres;
	}
	
}
