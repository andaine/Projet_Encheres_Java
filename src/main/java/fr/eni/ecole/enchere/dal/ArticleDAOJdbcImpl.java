package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.enchere.bo.Article;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.exception.BusinessException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String SELECT_CATEGORIES = "SELECT * FROM Categories";
	private static final String CREATE_ARTICLE = "INSERT INTO Articles (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, no_utilisateur, no_categorie, etat_vente)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'CR')";
	private static final String CREATE_RETRAITS_ARTICLE = "INSERT INTO Retraits (no_article, rue, code_postal, ville) VALUES (?,?,?,?)";

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
	
    
	@Override
	public void ajouterVente(Article article, int idUtilisateur) throws BusinessException {
		
		
			try (Connection cnx = ConnectionProvider.getConnection()) {

				cnx.setAutoCommit(false);
				//création de l'article
				PreparedStatement pstmt = cnx.prepareStatement(CREATE_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, (article.getNomArticle()));
				pstmt.setString(2, (article.getDescription()));
				pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
				pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
				pstmt.setInt(5, article.getPrixInitial());
	
				pstmt.setInt(6, idUtilisateur);
				pstmt.setInt(7, article.getNoCategorie());

				int idNoArticle = 0;

				
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));	
					idNoArticle = rs.getInt(1);
				}
				
				pstmt.close();
				//création du retrait associé à l'article
				pstmt = cnx.prepareStatement(CREATE_RETRAITS_ARTICLE);
				
				
				pstmt.setInt(1, idNoArticle);
				pstmt.setString(1, article.getRetraitVendeur().getRue());
				pstmt.setString(2, article.getRetraitVendeur().getCodePostal());
				pstmt.setString(3, article.getRetraitVendeur().getVille());
				pstmt.executeUpdate();
				
				pstmt.close();
				cnx.commit();
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException be = new BusinessException();
				be.addMessage("DAL exception - insertion de l'article impossible");
				throw be;

			}
		}


}




