package fr.eni.ecole.enchere.dal;

public abstract class DAOFactory {

	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
}
