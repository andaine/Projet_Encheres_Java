package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.DAOFactory;
import fr.eni.ecole.enchere.dal.EnchereDAO;
import fr.eni.ecole.enchere.exception.BusinessException;


public class EnchereManager {
private EnchereDAO enchereDAO;
	
	public EnchereManager() {
		
		this.enchereDAO=DAOFactory.getEnchereDAO();
	}
	

		public Utilisateur validerUtilisateur(String pseudo, String pwd) throws BusinessException {
			
			Utilisateur userLogin = new Utilisateur();
			
			userLogin = enchereDAO.connexion(pseudo, pwd);
			
			
			
			return userLogin;
	
	}
}
