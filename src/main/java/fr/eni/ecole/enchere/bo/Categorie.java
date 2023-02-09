package fr.eni.ecole.enchere.bo;

import java.util.List;

public class Categorie {

	private int noCategorie;
	private String libelle;
	private List<Article> categorieArticle;
	
	
	public Categorie() {
		super();
	}

	
	public Categorie(int noCategorie, String libelle, List<Article> categorieArticle) {
		this(noCategorie, libelle);
		this.categorieArticle = categorieArticle;
	}


	public Categorie(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
	
	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}


	public int getNoCategorie() {
		return noCategorie;
	}


	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public List<Article> getCategorieArticle() {
		return categorieArticle;
	}


	public void setCategorieArticle(List<Article> categorieArticle) {
		this.categorieArticle = categorieArticle;
	}
	
	
	
}
