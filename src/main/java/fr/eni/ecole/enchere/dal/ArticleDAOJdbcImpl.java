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
	private static final String CREATE_ARTICLE = "INSERT INTO Articles_Vendus (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, no_utilisateur, no_categorie, etat_vente)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, 'CR')";
	private static final String CREATE_RETRAITS_ARTICLE = "INSERT INTO Retraits (no_article, rue, code_postal, ville) VALUES (?,?,?,?)";
	private static final String SELECT_ARTICLE = "SELECT a.no_article, a.nom_article, a.description, c.libelle, e.montant_enchere, a.prix_initial, a.date_fin_enchere, a.no_utilisateur, u.pseudo"
			+ "FROM UTILISATEURS u INNER JOIN ARTICLES_VENDUS a ON a.no_utilisateur = u.no_utilisateur "
								+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "
								+ "LEFT JOIN ENCHERES e ON e.no_article= a.no_article "
								+ "LEFT JOIN RETRAITS r ON a.no_article = r.no_article "
								+ "WHERE a.no_article=?";
	
	
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
				pstmt.setString(2, article.getRetraitVendeur().getRue());
				pstmt.setString(3, article.getRetraitVendeur().getCodePostal());
				pstmt.setString(4, article.getRetraitVendeur().getVille());
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

	public Article afficherArticle(int idArticle) throws BusinessException {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {

			Article artRetourne = new Article();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE);
			pstmt.setInt(1, (artRetourne.getNoArticle()));

			ResultSet rs = pstmt.executeQuery();
			Categorie cat = new Categorie(idArticle, CREATE_ARTICLE);
			if (rs.next()) {
				artRetourne.setNoArticle(rs.getInt("noArticle"));
				artRetourne.setNomArticle(rs.getString("nomArticle"));
				artRetourne.setDescription(rs.getString("description"));
				//artRetourne.setnoCategorie(rs.getString("nomArticle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("DAL exception - insertion de l'article impossible");
			throw be;
		}
		return null;

	}
}
