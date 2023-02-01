package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

public class ArticleDAOJdbcImpl implements ArticleDAO{

	private static final String SELECT_CATEGORIES ="SELECT * FROM Articles";

	@Override
	public void selectCategories() throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			Statement pstmt = cnx.createStatement();

			pstmt.executeQuery(SELECT_CATEGORIES);

		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.addMessage("la liste des catégories n'a pas pu être chargée");
			// throw lance l'exception et envoie le message aux couches supérieures
			throw be;
		}
	}
}
