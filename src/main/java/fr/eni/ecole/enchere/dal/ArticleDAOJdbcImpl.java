package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.exception.BusinessException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String SELECT_CATEGORIES = "SELECT * FROM Categories";

	@Override
	public List<String> selectCategories() throws BusinessException {

		List<String> categoriesListe = new ArrayList<String>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_CATEGORIES);
			
			while (rs.next()) {
				
				categoriesListe.add(rs.getString("libelle"));
				}

			
		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.addMessage("la liste des catégories n'a pas pu être chargée");
			// throw lance l'exception et envoie le message aux couches supérieures
			throw be;
		}
System.out.println("dal");
		return categoriesListe;

	}
}
