package fr.eni.ecole.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.enchere.bo.Enchere;
import fr.eni.ecole.enchere.exception.BusinessException;


public class EnchereDAOJdbcImpl implements EnchereDAO{

	private static final String SELECT_ENCHERES = "SELECT e.no_utilisateur, e.no_article, e.date_enchere, e.montant_enchere, u.nom, a.nom_article FROM Encheres e INNER JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur INNER JOIN ARTICLES_VENDUS a ON e.no_article = a.no_article";

	@Override
	public List<Enchere> afficherEncheres() throws BusinessException {
		
		List<Enchere> listeEncheres = new ArrayList<>();
	
		
		try (Connection con = ConnectionProvider.getConnection()){
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ENCHERES);	
			while(rs.next()) {
				System.out.println(rs.getInt("montant_enchere"));
				Enchere enchere = new Enchere(rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere"),  rs.getInt("no_utilisateur"), 
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("nom"));
				  
				listeEncheres.add(enchere);
			}
			for(Enchere e : listeEncheres) {
				System.out.println("DAL encheres : " + e.getMontantEnchere());
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
