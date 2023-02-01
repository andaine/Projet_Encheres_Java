package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.exception.BusinessException;


public class EnchereDAOJdbcImpl implements EnchereDAO{

	private static final String SELECT_ENCHERES = "SELECT * FROM Encheres";
	

	@Override
	public List<Enchere> afficherEncheres() throws BusinessException {
		
		List<Enchere> listeEnchere = new ArrayList<>();
		
		try (Connection con = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = con.prepareStatement(SELECT_ENCHERES);
			ResultSet rs = pstmt.executeQuery();
			Enchere enchere = new Enchere();
			LocalDate dateEnchere = rs.getDate("date_enchere").toLocalDate();
			while(rs.next()) {
				enchere = new Enchere(dateEnchere, rs.getInt("montant_enchere"), rs.getInt("no_utilisateur"), rs.getInt("no_article"));
				listeEnchere.add(enchere);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("DAL exception - Impossible d'afficher la liste des enchères");
			throw be;			
		}

		return listeEnchere;
	}

	
	
	
	
	
	
}
