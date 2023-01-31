package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.exception.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String LOGIN = "SELECT * FROM UTILISATEURS where pseudo=? and mot_de_passe=?";
	private static final String CREATE_USER = "INSERT INTO UTILSATEURS (pseudo, nom, prenom, email, telephone, rue, code postal, ville, mot_de_passe, credit, administrateur)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 100, 0)";
	private static final String DELETE_USER = "DELETE * FROM Utilisateurs WHERE no_utilisateur=?";
	private final String UPDATE_USER = "UPDATE Utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";


	public Utilisateur connexion(String pseudo, String pwd) throws BusinessException {

		PreparedStatement stmt = null;
		Utilisateur user = new Utilisateur();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			stmt = cnx.prepareStatement(LOGIN);
			stmt.setString(1, pseudo);
			stmt.setString(2, pwd);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
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
				;
				System.out.println("connecté");
				System.out.println(user.getEmail());
			} else {
				BusinessException businessException = new BusinessException();
				businessException.addMessage("utilisateur inexistant");
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.addMessage("utilisateur inexistant");
			throw businessException;
		}

		return user;

	}
	
	@Override
	public void insert(Utilisateur user) throws BusinessException {

		Utilisateur userCree = new Utilisateur();
		
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
			BusinessException be = new BusinessException();
			be.addMessage("L'insertion d'un repas a généré une erreur");
			//throw lance l'exception et envoie le message aux couches supérieures
			throw be;	
		}
	}

	@Override
	public void supprimerCompte(int id) throws BusinessException {
		try(Connection con = ConnectionProvider.getConnection()){
			
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(DELETE_USER);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void updateUser(Utilisateur userUpdate) throws BusinessException {

		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			
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
				
				
		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.addMessage("l'update de l'utilisateur à généré une erreur dans jdbcImpl");
			//throw lance l'exception et envoie le message aux couches supérieures
			throw be;	
		}
	}
	
}
