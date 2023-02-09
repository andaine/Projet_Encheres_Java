package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.enchere.bo.Article;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Retrait;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String SELECT_CATEGORIES = "SELECT * FROM Categories";
	private static final String CREATE_ARTICLE = "INSERT INTO Articles_Vendus (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, no_utilisateur, no_categorie, etat_vente)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, 'CR')";
	private static final String CREATE_RETRAITS_ARTICLE = "INSERT INTO Retraits (no_article, rue, code_postal, ville) VALUES (?,?,?,?)";
	private static final String SELECT_ARTICLE = "SELECT a.no_article, a.nom_article, a.description, c.libelle, e.montant_enchere, ua.pseudo pseudoAcheteur, ua.credit creditAcheteur, a.prix_vente, a.prix_initial, a.date_fin_enchere, uv.pseudo pseudoVendeur, r.rue, r.ville, r.code_postal "
			+ " FROM UTILISATEURS uv INNER JOIN ARTICLES_VENDUS a ON a.no_utilisateur = uv.no_utilisateur "
								+ " INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "
								+ " LEFT JOIN ENCHERES e ON e.no_article= a.no_article "
								+ " LEFT JOIN UTILISATEURS ua ON ua.no_utilisateur = e.no_utilisateur "
								+ " LEFT JOIN RETRAITS r ON a.no_article = r.no_article "
								+ " WHERE a.no_article=?";
	
	
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
				pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(article.getDateDebutEncheres()));
				pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(article.getDateFinEncheres()));
				pstmt.setInt(5, article.getPrixInitial());
	
				pstmt.setInt(6, idUtilisateur);
				pstmt.setInt(7, article.getCategorie().getNoCategorie());

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

	
	@Override
	public Article afficherArticle(int idArticle) throws BusinessException {
		
		Article artRetourne = new Article();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE);
			pstmt.setInt(1, idArticle);

			ResultSet rs = pstmt.executeQuery();
		
			if (rs.next()) {
				int noArt = rs.getInt("no_article");
				String nomArt = rs.getString("nom_article");
				String descrArt = rs.getString("description");
				Categorie catArt = new Categorie(rs.getString("libelle"));
				int meilOffre = 0;
				if(artRetourne.getEtatVente()=="VD") {
					meilOffre = rs.getInt("prix_vente");
				}else {
					meilOffre = rs.getInt("montant_enchere"); 
				}
				Utilisateur userA = new Utilisateur(rs.getString("pseudoAcheteur"),rs.getInt("creditAcheteur"));
				int miseAPrix = rs.getInt("prix_initial");
				LocalDateTime  finEnch = rs.getTimestamp("date_fin_enchere").toLocalDateTime();
				Retrait retrait = new Retrait(rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"));
				Utilisateur userV = new Utilisateur(rs.getString("pseudoVendeur"));
				
				artRetourne = new Article(noArt,nomArt,descrArt,finEnch,miseAPrix,meilOffre,catArt,userV,userA,retrait);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("DAL exception - accès à l'article impossible");
			throw be;
		}
		return artRetourne;
	}
}
