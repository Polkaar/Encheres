package fr.eni.encheres.bo;

import java.util.List;

public class Categorie {

	private Integer noCategorie;
	private String libelle;
	private List<ArticleVendu> lstArticleVendus;
	
	public Categorie() {
	}

	public Categorie(String libelle, List<ArticleVendu> lstArticleVendus) {
		super();
		this.libelle = libelle;
		this.lstArticleVendus = lstArticleVendus;
	}

	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Integer getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ArticleVendu> getLstArticleVendus() {
		return lstArticleVendus;
	}

	public void setLstArticleVendus(List<ArticleVendu> lstArticleVendus) {
		this.lstArticleVendus = lstArticleVendus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [noCategorie=");
		builder.append(noCategorie);
		builder.append(", libelle=");
		builder.append(libelle);
		builder.append(", lstArticleVendus=");
		builder.append(lstArticleVendus);
		builder.append("]");
		return builder.toString();
	}
}
