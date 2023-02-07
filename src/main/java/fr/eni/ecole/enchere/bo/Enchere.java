package fr.eni.ecole.enchere.bo;

import java.time.LocalDate;

public class Enchere {

	private LocalDate dateEnchere;
	private int montantEnchere;	
	private Utilisateur user;
	private Article article;
	private Categorie categorie;
	
	
	
	
	
	private int noUser;
	private int noArticle;
	private String nomArticle;
	private String pseudoUser;
	private String nomCategorie;


	public Enchere(LocalDate dateEnchere, int montantEnchere, Utilisateur user, Article article, Categorie categorie) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.user = new Utilisateur();
		this.article = new Article();
		this.categorie = new Categorie();		
	}

	public Enchere(LocalDate dateEnchere, int montantEnchere, int noUser, int noArticle, String nomArticle,
			String pseudoUser, String nomCategorie) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noUser = noUser;
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.pseudoUser = pseudoUser;
		this.nomCategorie = nomCategorie;
	}

	public Enchere(LocalDate dateEnchere, int montantEnchere, int noUser, int noArticle) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noUser = noUser;
		this.noArticle = noArticle;
	}

	public Enchere() {
		super();
	}

	public Enchere(LocalDate dateEnchere, int montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public int getNoUser() {
		return noUser;
	}

	public void setNoUser(int noUser) {
		this.noUser = noUser;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getPseudoUser() {
		return pseudoUser;
	}

	public void setPseudoUser(String pseudoUser) {
		this.pseudoUser = pseudoUser;
	}

	
	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + "]";
	}

}
