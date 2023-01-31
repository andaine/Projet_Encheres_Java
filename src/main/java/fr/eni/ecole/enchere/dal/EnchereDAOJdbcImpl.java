package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String LOGIN = "SELECT * FROM UTILISATEURS where pseudo=? and mot_de_passe=?";

	public Utilisateur connexion(String pseudo, String pwd) throws BusinessException {
		
		PreparedStatement stmt = null;
		Utilisateur user = new Utilisateur();

		try(Connection cnx = ConnectionProvider.getConnection()) {

			stmt = cnx.prepareStatement(LOGIN);
			stmt.setString(1, pseudo);
			stmt.setString(2, pwd);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setCredit(rs.getInt("credit"));
				if ( rs.getByte("administrateur") ==0) {
					user.setAdministrateur(false);
				} else {
					user.setAdministrateur(true);
				};
				System.out.println("connecté");
				System.out.println(user.getEmail());
			} else {
				BusinessException businessException = new BusinessException();
				businessException.addMessage("utilisateur inexistant");
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.addMessage("utilisateur inexistant");
			throw businessException;
		}
		
		return user;

}

}
