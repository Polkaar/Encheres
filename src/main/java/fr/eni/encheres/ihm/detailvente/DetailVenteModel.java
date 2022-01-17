package fr.eni.encheres.ihm.detailvente;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

public class DetailVenteModel {

	private ArticleVendu articleVendu;
	private Utilisateur vendeur;
	private Utilisateur acheteur;
	private String message;
	
	public DetailVenteModel() {
	}

	

	public DetailVenteModel(ArticleVendu articleVendu, Utilisateur vendeur, Utilisateur acheteur, String message) {
		super();
		this.articleVendu = articleVendu;
		this.vendeur = vendeur;
		this.acheteur = acheteur;
		this.message = message;
	}



	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
