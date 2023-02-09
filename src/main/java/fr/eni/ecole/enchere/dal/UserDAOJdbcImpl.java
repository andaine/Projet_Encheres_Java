package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

public class UserDAOJdbcImpl implements UserDAO {

	private static final String LOGIN = "SELECT * FROM Utilisateurs WHERE pseudo=? and mot_de_passe=?";
	private static final String SELECT_USER = "SELECT * FROM Utilisateurs WHERE no_utilisateur=?";
	private static final String CREATE_USER = "INSERT INTO Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 100, 0)";
	private static final String DELETE_USER = "DELETE FROM Utilisateurs WHERE no_utilisateur=?";
	private static final String DELETE_ARTICLE_VENDU = "DELETE FROM Articles_vendus WHERE no_utilisateur=?";
	private static final String DELETE_ENCHERE = " DELETE ENCHERES FROM ARTICLES_VENDUS a INNER JOIN ENCHERES e ON e.no_article = a.no_article "
			+ "WHERE a.no_utilisateur = ?;";
	private static final String UPDATE_USER = "UPDATE Utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	private static final String CHECK_MDP = "SELECT * FROM Utilisateurs where no_utilisateur=?";
	private static final String UPDATE_CREDIT_USER = "UPDATE Utilisateurs SET credit=? WHERE no_utilisateur = ?";

	public Utilisateur connexion(String pseudo, String pwd) throws BusinessException {

		Utilisateur user = new Utilisateur();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(LOGIN);
			stmt.setString(1, pseudo);
			stmt.setString(2, pwd);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setCredit(rs.getInt("credit"));
				
				
				if (rs.getByte("administrateur") == 0) {
					user.setAdministrateur(false);
				} else {
					user.setAdministrateur(true);
				}

			} else {
				BusinessException businessException = new BusinessException();
				businessException.addMessage("DAL exception - utilisateur inexistant");
				throw businessException;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.addMessage("DAL exception - échec de connexion");
			throw businessException;
		}

		return user;
	}

	@Override
	public void insert(Utilisateur user) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(CREATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, (user.getPseudo()));
			pstmt.setString(2, (user.getNom()));
			pstmt.setString(3, (user.getPrenom()));
			pstmt.setString(4, (user.getEmail()));
			pstmt.setString(5, (user.getTelephone()));
			pstmt.setString(6, (user.getRue()));
			pstmt.setString(7, (user.getCodePostal()));
			pstmt.setString(8, (user.getVille()));
			pstmt.setString(9, (user.getMotDePasse()));

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				user.setNoUtilisateur(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("DAL exception - insertion de l'utilisateur impossible");
			throw be;

		}
	}

	@Override
	public void supprimerCompte(int id) throws BusinessException {
		try (Connection con = ConnectionProvider.getConnection()) {

			con.setAutoCommit(false);

			// supprime les encheres de l'user a supprimer
			PreparedStatement pstmtEnchere = con.prepareStatement(DELETE_ENCHERE);
			pstmtEnchere.setInt(1, id);
			pstmtEnchere.executeUpdate();
			pstmtEnchere.close();

			// supprime les articles de l'user a supprimer
			PreparedStatement pstmtArticle = con.prepareStatement(DELETE_ARTICLE_VENDU);
			pstmtArticle.setInt(1, id);
			pstmtArticle.executeUpdate();
			pstmtArticle.close();

			// supprime l'user
			PreparedStatement pstmtUser = con.prepareStatement(DELETE_USER);
			pstmtUser.setInt(1, id);
			pstmtUser.executeUpdate();
			pstmtUser.close();
			con.commit();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("DAL exception - suppression de l'utilisateur impossible");
			throw be;
		}

	}

	@Override
	public void updateUser(Utilisateur userUpdate) throws BusinessException {

	
		try (Connection cnx = ConnectionProvider.getConnection()) {
			String mdpBDD = null;
			PreparedStatement pstmt = cnx.prepareStatement(CHECK_MDP);

			pstmt.setInt(1, userUpdate.getNoUtilisateur());
			
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				mdpBDD = rs.getString("mot_de_passe");
			}

			pstmt = cnx.prepareStatement(UPDATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);

		
			

				pstmt.setString(1, userUpdate.getPseudo());
				pstmt.setString(2, userUpdate.getNom());
				pstmt.setString(3, userUpdate.getPrenom());
				pstmt.setString(4, userUpdate.getEmail());
				pstmt.setString(5, userUpdate.getTelephone());
				pstmt.setString(6, userUpdate.getRue());
				pstmt.setString(7, userUpdate.getCodePostal());
				pstmt.setString(8, userUpdate.getVille());
				pstmt.setString(9, userUpdate.getMotDePasse());
				pstmt.setInt(10, userUpdate.getNoUtilisateur());
				
				pstmt.executeUpdate();
			

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.addMessage("l'update de l'utilisateur à généré une erreur dans jdbcImpl");
			// throw lance l'exception et envoie le message aux couches supérieures
			throw be;
		}
	}

	@Override
	public Utilisateur selectUser(int id) throws BusinessException {

		Utilisateur user = new Utilisateur();

		try (Connection con = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = con.prepareStatement(SELECT_USER);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
			} else {
				BusinessException businessException = new BusinessException();
				businessException.addMessage("DAL exception - utilisateur inexistant");
				throw businessException;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.addMessage("DAL exception - échec de connexion");
			throw businessException;
		}
		return user;
	}

	@Override
	public void updateCreditUser(Utilisateur user) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CREDIT_USER);

			pstmt.setInt(1, user.getCredit());			
			pstmt.setInt(2, user.getNoUtilisateur());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("La mise à jour des crédit de l'acheteur a généré une erreur dans jdbcImpl");
			throw be;
		}
			
		}
}
