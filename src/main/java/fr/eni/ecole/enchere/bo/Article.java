package fr.eni.ecole.enchere.bo;


import java.time.LocalDateTime;
import java.util.List;


public class Article {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private Categorie categorie;
	private Utilisateur userA;
	private Utilisateur userV;
	private String etatVente;
	private List<Enchere> enchereListe;
	private Retrait retraitVendeur;
	


	public Article() {
		super();
	}
	
	
	//constructeur utilisé dans DAL Articles pour afficher detailsVente
	public Article(int noArticle, String nomArticle, String description, LocalDateTime dateFinEncheres, int prixInitial, int prixVente, 
			Categorie categorie, Utilisateur userV, Utilisateur userA, Retrait retraitVendeur) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.categorie = categorie;
		this.userA = userA;
		this.userV = userV;
		this.retraitVendeur = retraitVendeur;
	}


	public Article(String nomArticle, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres,
			int prixInitial, int prixVente, Categorie categorie, String etatVente,
			 Retrait retraitVendeur) {
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
	


	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	


	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}
	


	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
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


	public Utilisateur getUserA() {
		return userA;
	}


	public void setUserA(Utilisateur userA) {
		this.userA = userA;
	}


	public Utilisateur getUserV() {
		return userV;
	}


	public void setUserV(Utilisateur userV) {
		this.userV = userV;
	}
	
	
}



