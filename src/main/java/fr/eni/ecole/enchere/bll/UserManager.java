package fr.eni.ecole.enchere.bll;

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
		validerUtilisateur(user);
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
		
		if (userAValider.getPseudo().isEmpty()) {
            sb.append("Le pseudo est obligatoire.\n");
        }

        if (userAValider.getNom().isEmpty()) {
        	sb.append("Le nom est obligatoire.\n");
        }
        if (userAValider.getPrenom().isEmpty()) {
        	sb.append("Le prénom est obligatoire.\n");
        }
        
        if (userAValider.getEmail().isEmpty()) {
        	sb.append("L'email est obligatoire.\n");
        }
        
        if (userAValider.getTelephone().length()!=10) {
            sb.append("Le format du telephone est non valide.\n");
        }
        
        if (userAValider.getRue().isEmpty()) {
        	sb.append("La rue est obligatoire.\n");
        }
        
        if (userAValider.getCodePostal().isEmpty()) {
        	sb.append("Le code postal est obligatoire.\n");
        }
        
        if (userAValider.getVille().isEmpty()) {
        	sb.append("La ville est obligatoire.\n");
        }
        
        if (userAValider.getMotDePasse().isEmpty()) {
        	sb.append("Le mot de passe est obligatoire.\n");
        }
        
       System.out.println("manager valider user");
     
        if(sb.length()>0) {
        	String resultat = sb.toString();
        	System.out.println(resultat);
        	BusinessException be = new BusinessException();
			be.addMessage(resultat);
        	throw be;
        	
        }
	}
}
