package fr.eni.ecole.enchere.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import fr.eni.ecole.enchere.bo.Article;
import fr.eni.ecole.enchere.bo.Categorie;
import fr.eni.ecole.enchere.dal.ArticleDAO;
import fr.eni.ecole.enchere.dal.DAOFactory;
import fr.eni.ecole.enchere.exception.BusinessException;

public class ArticleManager {

	private ArticleDAO articleDAO;
	private static ArticleManager mgr;

	private ArticleManager() {

		this.articleDAO = DAOFactory.getArticleDAO();
	}

	public static ArticleManager getInstance() {
		if (mgr == null) {
			mgr = new ArticleManager();
		}
		return mgr;
	}

	public List<Categorie> afficherCategories() throws BusinessException {
		List<Categorie> categoriesListe = articleDAO.selectCategories();
		return categoriesListe;
	}

	public void ajouterVente(Article article, int id) throws BusinessException {
		validerArticle(article);
		articleDAO.ajouterVente(article, id);
	}
	
	public Article afficherArticle(int idArticle) throws BusinessException {
		Article art = articleDAO.afficherArticle(idArticle);
		return art;
	}

	private void validerArticle(Article article) throws BusinessException {

		BusinessException be = new BusinessException();

		if (article.getDateDebutEncheres().isBefore(LocalDateTime.now())) {
			be.addMessage("La date de début d'enchère est antérieure à la date du jour.");
		}
		
		if (article.getDateFinEncheres().isBefore(LocalDateTime.now())) {
			be.addMessage("La date de fin d'enchère est antérieure à la date du jour.");
		}

		if (article.getDateDebutEncheres().isAfter(article.getDateFinEncheres())) {
			be.addMessage("La date de début d'enchère ne doit pas être postérieure à la date de fin.");
		}

		if (article.getNomArticle().isBlank()) {
			be.addMessage("Le nom de l'article est obligatoire.");
		}

		if (article.getDescription().isBlank()) {
			be.addMessage("La description est obligatoire.");
		}

		if (article.getPrixInitial() <= 0) {
			be.addMessage("Le prix doit être obligatoire.");
		}

		if (article.getRetraitVendeur().getRue().isBlank()) {
			be.addMessage("La rue est obligatoire.");
		}

		if (article.getRetraitVendeur().getCodePostal().isBlank()) {
			be.addMessage("Le code postal est obligatoire.");
		}

		if (article.getRetraitVendeur().getVille().isBlank()) {
			be.addMessage("La ville est obligatoire.");
		}


		if (!be.getListeMessage().isEmpty()) {

			throw be;

		}
	}

}
