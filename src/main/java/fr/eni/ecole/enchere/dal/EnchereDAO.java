package fr.eni.ecole.enchere.dal;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

public interface EnchereDAO {

	public Utilisateur connexion(String pseudo, String pwd) throws BusinessException;
}