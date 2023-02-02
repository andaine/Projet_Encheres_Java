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
	
	public List<Enchere> afficherAllEncheres() throws BusinessException{
		
		List<Enchere> listeAllsEncheres = enchereDAO.afficherAllEncheres();
		for(Enchere e : listeAllsEncheres) {
			System.out.println("BLL encheres : " + e.getMontantEnchere());
		}
		return listeAllsEncheres;
	}
	
	
	public List<Enchere> afficherAutresEncheres(int id) throws BusinessException{
			
		List<Enchere> listeAutresEncheres = enchereDAO.afficherAutresEncheres(id);
		for(Enchere e : listeAutresEncheres) {
			System.out.println("BLL encheres : " + e.getMontantEnchere());
		}
		return listeAutresEncheres;
	}
	
	public List<Enchere> afficherMesEncheres(int id) throws BusinessException	{
		
		List<Enchere> listeMesEncheres = enchereDAO.afficherMesEncheres(id);
		for(Enchere e : listeMesEncheres) {
			System.out.println("BLL mesEncheres : " + e.getMontantEnchere());
		}
		return listeMesEncheres;
		
	}
}
