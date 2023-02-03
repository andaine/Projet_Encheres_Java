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

	private static final String SELECT_ALL_ENCHERES = "SELECT e.no_utilisateur, e.no_article, a.date_fin_enchere, e.montant_enchere, u.pseudo, a.nom_article, c.libelle FROM Encheres e INNER JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur INNER JOIN ARTICLES_VENDUS a ON e.no_article = a.no_article INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie";
	private static final String SELECT_AUTRES_ENCHERES = SELECT_ALL_ENCHERES + "WHERE NOT e.no_utilisateur=?";
	private static final String SELECT_MES_ENCHERES = SELECT_ALL_ENCHERES + "WHERE e.no_utilisateur=?";
	
	
	
	@Override
	public List<Enchere> afficherAllEncheres() throws BusinessException {
		
		List<Enchere> listeAllEncheres = new ArrayList<>();
	
		
		try (Connection con = ConnectionProvider.getConnection()){
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_ENCHERES);	
			while(rs.next()) {
				System.out.println(rs.getInt("montant_enchere"));
				Enchere enchere = new Enchere(rs.getDate("date_fin_enchere").toLocalDate(), rs.getInt("montant_enchere"),  rs.getInt("no_utilisateur"), 
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("pseudo"), rs.getString("libelle"));
				  
				listeAllEncheres.add(enchere);
			}
			for(Enchere e : listeAllEncheres) {
				System.out.println("DAL allEncheres : " + e.getMontantEnchere());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("DAL exception - Impossible d'afficher la liste des enchères");
			throw be;			
		}

		return listeAllEncheres;
	}



	@Override
	public List<Enchere> afficherMesEncheres(int id) throws BusinessException {

		List<Enchere> listeMesEncheres = new ArrayList<>();
		
		try (Connection con = ConnectionProvider.getConnection()){
			
	
			PreparedStatement pstmt = con.prepareStatement(SELECT_MES_ENCHERES);			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("montant_enchere"));
				Enchere enchere = new Enchere(rs.getDate("date_fin_enchere").toLocalDate(), rs.getInt("montant_enchere"),  rs.getInt("no_utilisateur"), 
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("pseudo"), rs.getString("libelle"));
				  
				listeMesEncheres.add(enchere);
			}
			for(Enchere e : listeMesEncheres) {
				System.out.println("DAL mesEncheres : " + e.getMontantEnchere());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("DAL exception - Impossible d'afficher la liste des enchères");
			throw be;			
		}

		return listeMesEncheres;
	}



	@Override
	public List<Enchere> afficherAutresEncheres(int id) throws BusinessException {
		
			
			List<Enchere> listeAutresEncheres = new ArrayList<>();
		
			
			try (Connection con = ConnectionProvider.getConnection()){
				
				PreparedStatement pstmt = con.prepareStatement(SELECT_AUTRES_ENCHERES);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();	
				while(rs.next()) {
					System.out.println(rs.getInt("montant_enchere"));
					Enchere enchere = new Enchere(rs.getDate("date_fin_enchere").toLocalDate(), rs.getInt("montant_enchere"),  rs.getInt("no_utilisateur"), 
							rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("pseudo"), rs.getString("libelle"));
					  
					listeAutresEncheres.add(enchere);
				}
				for(Enchere e : listeAutresEncheres) {
					System.out.println("DAL autresEncheres : " + e.getMontantEnchere());
				}

			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException be = new BusinessException();
				be.addMessage("DAL exception - Impossible d'afficher la liste des enchères");
				throw be;			
			}

			return listeAutresEncheres;
		}



	@Override
	public Enchere ajouterVente(Enchere enchere) throws BusinessException {
		
		return null;
	}

	


	
	
	
	
	
	
}
