package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String LOGIN = "SELECT * FROM UTILISATEURS where  email=?, mot_de_passe=?";

	public Utilisateur connexion(String pseudo, String pwd) throws BusinessException {
		
		PreparedStatement stmt = null;
		Utilisateur user = new Utilisateur();

		try(Connection cnx = ConnectionProvider.getConnection()) {

			stmt = cnx.prepareStatement(LOGIN);
			stmt.setString(1, pseudo);
			stmt.setString(2, pwd);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				user.setMotDePasse(pwd);
				user.setPseudo(pseudo);
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
