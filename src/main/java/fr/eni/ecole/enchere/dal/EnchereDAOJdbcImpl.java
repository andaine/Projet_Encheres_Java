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

import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String SELECT_ALL_ENCHERES = "SELECT e.no_utilisateur, e.no_article, a.date_fin_enchere, e.montant_enchere, u.pseudo, a.nom_article, c.libelle FROM Encheres e "
			+ "INNER JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN ARTICLES_VENDUS a ON e.no_article = a.no_article "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie";
	private static final String FILTRE_USER = "e.no_utilisateur= ?";
	private static final String FILTRE_CATEGORIE = "c.libelle= ?";
	private static final String FILTRE_NOM_ARTICLE = "a.nom_article LIKE ?";
	private static final String CATEGORIE_DEFAUT = "Toutes";
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES (?,?,?,?)";

	public static boolean filtreCategorie = false, filtreNomArticle = false, filtreAchats = false, encheresOuvertes = false, mesEncheres = false,
				mesEncheresRemportees = false, mesVentesEnCours = false, ventesNonDebutees = false, ventesTerminees = false;

	
	
	private int count = 1;

	@Override
	public List<Enchere> afficherEncheres(int userId, String categorie, String nomArticle) throws BusinessException {

		boolean filtreCategorie = false;
//		afficher enchère
		String requete = SELECT_ALL_ENCHERES;
		List<Enchere> listeEncheres = new ArrayList<>();
		ResultSet rs;

		try (Connection con = ConnectionProvider.getConnection()) {

			
			
			// ACCUEIL DEFAUT
			if (categorie == null && nomArticle == null) {
				System.out.println("DEFAUT\n");
				System.out.println("userId : " + userId);
				System.out.println("categorie : " + categorie);
				System.out.println("nom article : " + nomArticle);
				System.out.println("-------------------------------------------------------\n");
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(requete);
			} else {
				
				//CATEGORIE TOUTES
				if(categorie.equals(CATEGORIE_DEFAUT)) {
					Statement stmt = con.createStatement();
					rs = stmt.executeQuery(requete);
					stmt.close();
					rs.close();
				}
		
				
				// CHOIX CATEGORIE
				if (!categorie.equals(CATEGORIE_DEFAUT)) {
					

					System.out.println("-------------------------------------------------------\n");
					System.out.println("CATEGORIE CHOISIE - ARTICLE VIDE");
					System.out.println("userId : " + userId);
					System.out.println("categorie : " + categorie);
					System.out.println("nom article : " + nomArticle);

					if (requete.contains("WHERE")) {
						requete += " AND ";
					} else {
						requete += " WHERE ";
					}
					System.out.println("filtre erreur 2");
					requete += FILTRE_CATEGORIE;
					filtreCategorie = true;
					System.out.println("requete = " + requete);
					System.out.println("\n-------------------------------------------------------\n");			
					System.out.println("count = " + count);
					System.out.println(categorie);
					System.out.println("count = " + count);				
								
					
					
				}

				// CHOIX ARTICLE
				if (!nomArticle.isEmpty()) {
					
					System.out.println("CATEGORIE TOUTES - ARTICLE CHOISI\n -------------------------------------");
					System.out.println("userId : " + userId);
					System.out.println("categorie : " + categorie);
					System.out.println("nom article : " + nomArticle);

					if (requete.contains("WHERE")) {
						requete += " AND ";
					} else {
						requete += " WHERE ";
					}
					
					requete += FILTRE_NOM_ARTICLE;
					filtreNomArticle = true;

					System.out.println("requete = " + requete);
					System.out.println("\n-------------------------------------------------------\n");
					System.out.println("count = " + count);
					
				}
							
				//definir ?
				PreparedStatement pstmt = con.prepareStatement(requete);
				if(filtreCategorie == true) {
					System.out.println("erreur filtre 3");
					pstmt.setString(count, categorie);
					count++;
				}
				if(filtreNomArticle == true) {
					pstmt.setString(count, "%" + nomArticle + "%");
					count++;
				}
				// TODO CHECKBOX DE L'ENFER
				if(filtreAchats) {
					
				}
				
				
				
				
				
				rs = pstmt.executeQuery();
				
				count = 1;

			}
			while (rs.next()) {
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

	@Override
	public void insertEnchere (Enchere enchere, int idArticle) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE);
			pstmt.setInt(1, (enchere.getNoUser()));
			pstmt.setInt(2, idArticle);
			pstmt.setDate(3, java.sql.Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, (enchere.getMontantEnchere()));

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("DAL exception - insertion de l'enchère impossible");
			throw be;

		}
	}
}
