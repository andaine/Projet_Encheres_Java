package fr.eni.ecole.enchere.bll;

import java.util.List;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.DAOFactory;
import fr.eni.ecole.enchere.dal.UserDAO;
import fr.eni.ecole.enchere.exception.BusinessException;

public class UserManager {
	private UserDAO userDAO;
	private static UserManager mgr;
	
	private UserManager() {

		this.userDAO = DAOFactory.getUserDAO();
	}
	
	public static UserManager getInstance() {
		if(mgr == null) {
			mgr = new UserManager();
		}
		return mgr;
	}
	

	public Utilisateur connecterUtilisateur(String pseudo, String pwd) throws BusinessException {

		Utilisateur userLogin = userDAO.connexion(pseudo, pwd);

		return userLogin;
	} 
	
	public Utilisateur afficherUtilisateur(int id) throws BusinessException{
		
		Utilisateur autreUser = userDAO.selectUser(id);
		
		return autreUser;
		
	}


	public void insererUtilisateur(Utilisateur user) throws BusinessException {

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
	
	public void updateCredit(Utilisateur user) throws BusinessException {
				
		userDAO.updateCreditUser(user);
	}
	
	private void validerUtilisateur(Utilisateur userAValider) throws BusinessException {
	
		BusinessException be = new BusinessException();
		
		if (isValidEmail(userAValider.getEmail()) == false ) {
			be.addMessage("Le format de l'email n'est pas bon");
		}
		
		if (userAValider.getPseudo().isBlank()) {
			be.addMessage("Le pseudo est obligatoire.\n");
        }

        if (userAValider.getNom().isBlank()) {
        	be.addMessage("Le nom est obligatoire.\n");
        }
        if (userAValider.getPrenom().isBlank()) {
        	be.addMessage("Le prénom est obligatoire.\n");
        }
        
        if (userAValider.getEmail().isBlank()) {
        	be.addMessage("L'email est obligatoire.\n");
        }
        
        if (userAValider.getTelephone().length()!=10) {
        	be.addMessage("Le format du telephone est non valide.\n");
        }
        
        if (userAValider.getRue().isBlank()) {
        	be.addMessage("La rue est obligatoire.\n");
        }
        
        if (userAValider.getCodePostal().isBlank()) {
        	be.addMessage("Le code postal est obligatoire.\n");
        }
        
        if (userAValider.getVille().isBlank()) {
        	be.addMessage("La ville est obligatoire.\n");
        }
        
        if (userAValider.getMotDePasse().isBlank()) {
        	be.addMessage("Le mot de passe est obligatoire.\n");
        }
        
       System.out.println("manager valider user");
     
        if(!be.getListeMessage().isEmpty()) {
        
        	throw be;
        	
        }
	}
	
	private static boolean isValidEmail(String email) {
			String regExp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
			return email.matches(regExp);
		}
}
