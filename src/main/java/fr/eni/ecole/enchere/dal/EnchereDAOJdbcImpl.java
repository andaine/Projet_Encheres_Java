package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.exception.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String SELECT_ARTICLE = "SELECT * FROM Articles_Vendus a "
			+ " INNER JOIN Utilisateurs u ON  a.no_utilisateur = u.no_utilisateur "
			+ " INNER JOIN Categories c ON a.no_categorie = c.no_categorie "
			+ " LEFT JOIN Encheres e ON  a.no_article = e.no_article " + " WHERE a.etat_vente != 'CR'";

	private static final String FILTRE_CATEGORIE = "c.libelle= ?";
	private static final String FILTRE_NOM_ARTICLE = "a.nom_article LIKE ?";
	private static final String CATEGORIE_DEFAUT = "Toutes";
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES (?,?,?,?)";
	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET no_utilisateur = ?, date_enchere = ?, montant_enchere = ? WHERE no_article = ?";

	
	  public static boolean filtreCategorie = false, filtreNomArticle = false,
	  filtreAchats = false, encheresOuvertes = false, mesEncheres = false,
	  mesEncheresRemportees = false, mesVentesEnCours = false, ventesNonDebutees =
	  false, ventesTerminees = false;
	 

	private int count = 1;

	public List<Enchere> afficherEncheres(int userId, String categorie, String nomArticle, boolean choixRadiobutton,
			List<Boolean> listCheckBox) throws BusinessException {

		boolean filtreCategorie = false;
		boolean filtreNomArticle = false;
		String requete = SELECT_ARTICLE;
		List<Enchere> listeEncheres = new ArrayList<>();
		ResultSet rs;

		try (Connection con = ConnectionProvider.getConnection()) {

			// ACCUEIL DEFAUT
			if (categorie == null && nomArticle == null) {
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(requete);
			} else {

				// CATEGORIE TOUTES
				if (categorie.equals(CATEGORIE_DEFAUT)) {
					Statement stmt = con.createStatement();
					rs = stmt.executeQuery(requete);

				}

				// CHOIX CATEGORIE
				if (!categorie.equals(CATEGORIE_DEFAUT)) {

					if (requete.contains(" WHERE")) {
						requete += " AND ";
					} else {
						requete += " WHERE ";
					}

					requete += FILTRE_CATEGORIE;
					filtreCategorie = true;
				}

				// CHOIX ARTICLE
				if (!nomArticle.isEmpty()) {

					if (requete.contains(" WHERE")) {
						requete += " AND ";
					} else {
						requete += " WHERE ";
					}

					requete += FILTRE_NOM_ARTICLE;
					filtreNomArticle = true;

				}

				// USER CONNECTE
				/*
				 * // Choix CheckBox + radiobutton if (choixRadiobutton == true) { if
				 * (requete.contains("AND")) { requete += " OR "; } else if
				 * (requete.contains("WHERE")) { requete += " AND "; } else { requete +=
				 * " WHERE "; } requete += " a.no_utilisateur !=" + userId; if
				 * (listCheckBox.get(0) == true) { if (requete.contains("AND")) { requete +=
				 * " OR "; } else if (requete.contains("WHERE")) { requete += " AND "; } else {
				 * requete += " WHERE "; } requete += " etat_vente='EC'"; } if
				 * (listCheckBox.get(1) == true) { if (requete.contains("AND")) { requete +=
				 * " OR "; } else if (requete.contains("WHERE")) { requete += " AND "; } else {
				 * requete += " WHERE "; } requete += " e.no_utilisateur=" + userId; } if
				 * (listCheckBox.get(2) == true) { if (requete.contains("AND")) { requete +=
				 * " OR "; } else if (requete.contains("WHERE")) { requete += " AND "; } else {
				 * requete += " WHERE"; } requete += " e.no_utilisateur=" + userId +
				 * " OR etat_vente='VD'"; } } else if (choixRadiobutton == false) { if
				 * (requete.contains("AND")) { requete += " OR "; } else if
				 * (requete.contains("WHERE")) { requete += " AND "; } else { requete +=
				 * " WHERE "; } requete += "a.no_utilisateur=" + userId; if (listCheckBox.get(0)
				 * == true) { if (requete.contains("AND")) { requete += " OR "; } else if
				 * (requete.contains("WHERE")) { requete += " AND "; } else { requete +=
				 * " WHERE "; } requete += " etat_vente='EC'"; } if (listCheckBox.get(1) ==
				 * true) { if (requete.contains("AND")) { requete += " OR "; } else if
				 * (requete.contains("WHERE")) { requete += " AND "; } else { requete +=
				 * " WHERE"; } requete += " etat_vente='CR'"; } if (listCheckBox.get(2) == true)
				 * { if (requete.contains("AND")) { requete += " OR "; } else if
				 * (requete.contains("WHERE")) { requete += " AND "; } else { requete +=
				 * " WHERE "; } requete += " etat_vente='VD'"; } }
				 */
				// definir ?
				PreparedStatement pstmt = con.prepareStatement(requete);
				if (filtreCategorie == true) {
					pstmt.setString(count, categorie);
					count++;
				}
				if (filtreNomArticle == true) {
					pstmt.setString(count, "%" + nomArticle + "%");
					count++;
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

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("DAL exception - Impossible d'afficher la liste des enchères");
			throw be;
		}

		return listeEncheres;
	}

	@Override
	public void insertEnchere(Enchere enchere, int idArticle) throws BusinessException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE);
			pstmt.setInt(1, (enchere.getNoUser()));
			pstmt.setInt(2, idArticle);
			pstmt.setDate(3, java.sql.Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, (enchere.getMontantEnchere()));

			pstmt.executeUpdate();

		} catch (SQLException e) {
			try {
				cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
				pstmt.setInt(1, (enchere.getNoUser()));
				pstmt.setDate(2, java.sql.Date.valueOf(enchere.getDateEnchere()));
				pstmt.setInt(3, (enchere.getMontantEnchere()));
				pstmt.setInt(4, (idArticle));

				pstmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
				BusinessException be = new BusinessException();
				be.addMessage("DAL exception - mise à jour de l'article impossible");
				throw be;
			}

		}
	}

	public List<Enchere> afficherAllEncheres(int userId, String categorie, String nomArticle) throws BusinessException {

		boolean filtreCategorie = false;
		boolean filtreNomArticle = false;
		String requete = SELECT_ARTICLE + " AND a.etat_vente = 'EC'";
		List<Enchere> listeEncheres = new ArrayList<>();
		ResultSet rs;

		try (Connection con = ConnectionProvider.getConnection()) {

			// ACCUEIL DEFAUT
			if (categorie == null && nomArticle == null) {

				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(requete);
			} else {

				// CATEGORIE TOUTES
				if (categorie.equals(CATEGORIE_DEFAUT)) {
					Statement stmt = con.createStatement();
					rs = stmt.executeQuery(requete);

				}

				// CHOIX CATEGORIE
				if (!categorie.equals(CATEGORIE_DEFAUT)) {

					if (requete.contains("WHERE")) {
						requete += " AND ";
					} else {
						requete += " WHERE ";
					}
					requete += FILTRE_CATEGORIE;
					filtreCategorie = true;

				}

				// CHOIX ARTICLE
				if (!nomArticle.isEmpty()) {

					if (requete.contains("WHERE")) {
						requete += " AND ";
					} else {
						requete += " WHERE ";
					}

					requete += FILTRE_NOM_ARTICLE;
					filtreNomArticle = true;

				}

				// definir ?
				PreparedStatement pstmt = con.prepareStatement(requete);
				if (filtreCategorie == true) {
					pstmt.setString(count, categorie);
					count++;
				}
				if (filtreNomArticle == true) {
					pstmt.setString(count, "%" + nomArticle + "%");
					count++;
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

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("DAL exception - Impossible d'afficher la liste des enchères");
			throw be;
		}

		return listeEncheres;
	}

}
