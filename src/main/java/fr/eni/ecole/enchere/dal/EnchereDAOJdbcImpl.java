package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.ecole.enchere.bo.Article;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String SELECT_ALL_ENCHERES = "SELECT e.no_utilisateur, e.no_article, a.date_fin_enchere, e.montant_enchere, "
			+ "u.pseudo, a.nom_article, c.libelle, a.etat_vente FROM Encheres e "
			+ "INNER JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN ARTICLES_VENDUS a ON e.no_article = a.no_article "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie";
	private static final String FILTRE_USER = "e.no_utilisateur= ?";
	private static final String FILTRE_CATEGORIE = "c.libelle= ?";
	private static final String FILTRE_NOM_ARTICLE = "a.nom_article LIKE ?";
	private static final String CATEGORIE_DEFAUT = "Toutes";
	private static final String FILTRE_ETAT_VENTE = "a.etat_vente=?";
	private static final String REQUETE_DEFAUT = SELECT_ALL_ENCHERES + " WHERE a.etat_vente= 'EC'";
	
	private List<Integer> nombreFiltres;
	private int index;
	private boolean filtreCategorie, filtreArticle, filtreAchats, encheresOuvertes, mesEncheres, mesEncheresRemportees, mesVentesEnCours, 
					ventesNonDebutees, ventesTerminees;
	
	@Override
	public List<Enchere> afficherEncheres(int userId, Categorie categorie, Article article) throws BusinessException {

		String requete = SELECT_ALL_ENCHERES;
		List<Enchere> listeEncheres = new ArrayList<>();
		ResultSet rs;

		try (Connection con = ConnectionProvider.getConnection()) {

			// ACCUEIL DEFAUT
			if (categorie.getLibelle() == null && article.getNomArticle() == null) {

				
				requete = REQUETE_DEFAUT;
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(requete);
			} else {
						
				// CHOIX CATEGORIE
				if (!categorie.getLibelle().equals(CATEGORIE_DEFAUT)) {
					
					if (requete.contains("WHERE")) {
						requete += " AND ";
					} else {
						requete += " WHERE ";
					}
					
					requete += FILTRE_CATEGORIE;
					filtreCategorie = true;
				       
				    System.out.println(requete);    
					
				}

				// CHOIX ARTICLE
				if (!article.getNomArticle().isEmpty()) {				

					System.out.println("CATEGORIE TOUTES - ARTICLE CHOISI\n -------------------------------------");
					System.out.println("userId : " + userId);
					System.out.println("categorie : " + categorie);
					System.out.println("nom article : " + article.getNomArticle());

					if (requete.contains("WHERE")) {
						requete += " AND ";
					} else {
						requete += " WHERE ";
					}
					requete += FILTRE_NOM_ARTICLE;
					filtreArticle = true;

					System.out.println(requete);
					
				

				}

				// TODO CHECKBOX DE L'ENFER

				
				
				PreparedStatement pstmt = con.prepareStatement(requete);
				
				
				
				
				rs = pstmt.executeQuery();

			}
			while (rs.next()) {
				userId = rs.getInt("no_utilisateur");
			
				
				
				
				
				Enchere enchere = new Enchere(rs.getDate("date_fin_enchere").toLocalDate(),
						rs.getInt("montant_enchere"), rs.getInt("no_utilisateur"), rs.getInt("no_article"),
						rs.getString("nom_article"), rs.getString("pseudo"), rs.getString("libelle"));

				listeEncheres.add(enchere);

			}
			System.out.println("liste encheres = \n");
			for (Enchere e : listeEncheres) {

				System.out.println(e.getNomArticle());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("DAL exception - Impossible d'afficher la liste des enchères");
			throw be;
		}

		return listeEncheres;
	}
}
