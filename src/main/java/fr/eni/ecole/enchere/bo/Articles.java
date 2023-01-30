package fr.eni.ecole.enchere.bo;

import java.time.LocalDate;
import java.util.List;

public class Articles {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private String etatVente;
	
	
	
	
	
	
	public Articles() {
		super();
	}




	public Articles(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int prixInitial, int prixVente, String etatVente) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}




	public Articles(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int prixInitial, int prixVente, String etatVente) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
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
	
	
}
