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
	private int nb=4;
	

	@Override
	public List<Categorie> selectCategories() throws BusinessException {

		List<Categorie> categoriesListe = new ArrayList<Categorie>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_CATEGORIES);
			
			
			
			while (rs.next()) {
				//crée une catégorie avec id et libelle afin de constituer la liste java ace les données de la bdd
				Categorie categorie = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));
				categoriesListe.add(categorie);
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
