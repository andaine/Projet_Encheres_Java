package fr.eni.ecole.enchere.dal;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

public interface UserDAO {

	public Utilisateur connexion(String pseudo, String pwd) throws BusinessException;
	
	public void insert(Utilisateur user) throws BusinessException;
	
	public void supprimerCompte(int id) throws BusinessException;

	public void updateUser(Utilisateur userUpdate) throws BusinessException;
	
	public Utilisateur selectUser(int id) throws BusinessException;

	public void updateCreditUser(Utilisateur user) throws BusinessException;
}
