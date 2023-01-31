package fr.eni.ecole.enchere.bll;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.DAOFactory;
import fr.eni.ecole.enchere.dal.EnchereDAO;
import fr.eni.ecole.enchere.exception.BusinessException;

public class EnchereManager {
	private EnchereDAO enchereDAO;

	
	public EnchereManager() {

		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	

	public Utilisateur validerUtilisateur(String pseudo, String pwd) throws BusinessException {

		Utilisateur userLogin = enchereDAO.connexion(pseudo, pwd);

		return userLogin;
	}
	

	public void insererUtilisateur(Utilisateur user) throws BusinessException {

		enchereDAO.insert(user);
	}
	
	public void supprimerUtilisateur(int id) throws BusinessException{
		
		enchereDAO.supprimerCompte(id);
	}
}
