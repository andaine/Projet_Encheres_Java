package fr.eni.ecole.enchere.bll;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.DAOFactory;
import fr.eni.ecole.enchere.dal.UserDAO;
import fr.eni.ecole.enchere.exception.BusinessException;

public class UserManager {
	private UserDAO userDAO;

	
	public UserManager() {

		this.userDAO = DAOFactory.getUserDAO();
	}
	

	public Utilisateur connecterUtilisateur(String pseudo, String pwd) throws BusinessException {

		Utilisateur userLogin = userDAO.connexion(pseudo, pwd);

		return userLogin;
	} 
	
	public Utilisateur afficherUtilisateur(String pseudo) throws BusinessException{
		
		Utilisateur autreUser = userDAO.selectUser(pseudo);
		
		return autreUser;
		
	}

	public void insererUtilisateur(Utilisateur user) throws BusinessException {

		System.out.println("Manager");
		userDAO.insert(user);
	}
	
	public void supprimerUtilisateur(int id) throws BusinessException{
		
		userDAO.supprimerCompte(id);
	}
	
	public void updateUtilisateur(Utilisateur userUpdate) throws BusinessException {

		validerUtilisateur(userUpdate);
		userDAO.updateUser(userUpdate);

	}
	
	private void validerUtilisateur(Utilisateur userAValider) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		if (userAValider.getPseudo().length()>0) {
            sb.append("Pseudo non renseignée.\n");
        }

        if (userAValider.getNom().length()>0) {
        	sb.append("nom non renseignée.\n");
        }
        if (userAValider.getPrenom().length()>0) {
        	sb.append("prenom non valide.\n");
        }
        
        if (userAValider.getCodePostal().length()>0) {
        	sb.append("Le codepostal non renseigné.\n");
        }
        
        if (userAValider.getEmail().length()>0) {
        	sb.append("L'email non renseigné.\n");
        }
        
        if (userAValider.getRue().length()>0) {
        	sb.append("la rue non renseigné.\n");
        }
        
        
        if (userAValider.getMotDePasse().length()>0) {
        	sb.append("mot de passe non renseigné.\n");
        }
        
       String resultat = sb.toString();
       System.out.println("manager valider user");
        System.out.println(resultat);
        if(sb.length()>0) {
        	BusinessException be = new BusinessException();
			be.addMessage(resultat);
        	throw be;
        }
	}
}
