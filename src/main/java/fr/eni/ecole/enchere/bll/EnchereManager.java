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
	

	public Utilisateur connecterUtilisateur(String pseudo, String pwd) throws BusinessException {

		Utilisateur userLogin = enchereDAO.connexion(pseudo, pwd);

		return userLogin;
	} 
	

	public void insererUtilisateur(Utilisateur user) throws BusinessException {

		System.out.println("Manager");
		enchereDAO.insert(user);
	}
	
	public void supprimerUtilisateur(int id) throws BusinessException{
		
		enchereDAO.supprimerCompte(id);
	}
	
	public void updateUtilisateur(Utilisateur userUpdate) throws BusinessException {

		validerUtilisateur(userUpdate);
		enchereDAO.updateUser(userUpdate);

	}
	
	private void validerUtilisateur(Utilisateur userAValider) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		if (userAValider.getPseudo()==null) {
            sb.append("Pseudo non renseignée.\n");
        }

        if (userAValider.getNom()==null) {
        	sb.append("nom non renseignée.\n");
        }
        if (userAValider.getPrenom() == null) {
        	sb.append("prenom non valide.\n");
        }
        if (userAValider.getTelephone()==null) {
        	sb.append("Quantité en stock non valide.\n");
        }
        
        if (userAValider.getCodePostal()== null) {
        	sb.append("Le codepostal non renseigné.\n");
        }
        
        if (userAValider.getEmail() == null) {
        	sb.append("L'email non renseigné.\n");
        }
        
        if (userAValider.getRue() == null) {
        	sb.append("la rue non renseigné.\n");
        }
        
        
        if (userAValider.getMotDePasse() == null) {
        	sb.append("mot de passe non renseigné.\n");
        }
        
       String resultat = sb.toString();
        
        if(sb.length()>0) {
        	BusinessException be = new BusinessException();
			be.addMessage(resultat);
        	
        }
	}
}
