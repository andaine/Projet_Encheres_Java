package fr.eni.ecole.enchere.bo;

import java.time.LocalDate;
import java.time.zone.ZoneRulesException;
import java.util.List;


public class Article {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private Categorie categorie;
	private Utilisateur user;
	private String etatVente;
	private List<Enchere> enchereListe;
	private Retrait retraitVendeur;
	
	
	
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Article() {
		super();
	}
	
	public Article(int noArticle, String nomArticle, String description, Categorie categorie, int prixInitial,
			LocalDate dateFinEncheres, int prixVente, List<Enchere> enchereListe, Retrait retraitVendeur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.categorie = categorie;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.enchereListe = enchereListe;
		this.setRetraitVendeur(retraitVendeur);
	}



	public Article(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int prixInitial, int prixVente, Categorie categorie, String etatVente,
			 Retrait retraitVendeur) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.categorie = categorie;

		this.etatVente = etatVente;
		this.setRetraitVendeur(retraitVendeur);
	}

	@Override
	public String toString() {
		return "Articles [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", etatVente=" + etatVente + "]";
	}
	

	public int getNoArticle() {
		return noArticle;
	}
	


	public String getNomArticle() {
		return nomArticle;
	}
	


	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	


	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	


	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	


	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	


	public int getPrixInitial() {
		return prixInitial;
	}



	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}



	public int getPrixVente() {
		return prixVente;
	}
	


	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	


	public String getEtatVente() {
		return etatVente;
	}
	


	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public List<Enchere> getEnchereListe() {
		return enchereListe;
	}

	public void setEnchereListe(List<Enchere> enchereListe) {
		this.enchereListe = enchereListe;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}


	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public Retrait getRetraitVendeur() {
		return retraitVendeur;
	}

	public void setRetraitVendeur(Retrait retraitVendeur) {
		this.retraitVendeur = retraitVendeur;
	}
}



