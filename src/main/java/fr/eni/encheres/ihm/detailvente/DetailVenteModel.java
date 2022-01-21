package fr.eni.encheres.ihm.detailvente;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public class DetailVenteModel {

	private ArticleVendu articleVendu;
	private Utilisateur vendeur;
	private Utilisateur oldAcheteur;
	private Utilisateur newAcheteur;
	private Enchere oldEnchere;
	private String message;
	private String messageVente;
	private String messageModifier;
	
	public String getMessageModifier() {
		return messageModifier;
	}

	public void setMessageModifier(String messageModifier) {
		this.messageModifier = messageModifier;
	}

	public String getMessageVente() {
		return messageVente;
	}

	public void setMessageVente(String messageVente) {
		this.messageVente = messageVente;
	}

	public DetailVenteModel() {
	}

	public DetailVenteModel(ArticleVendu articleVendu, Utilisateur vendeur, Utilisateur oldAcheteur,
			Utilisateur newAcheteur, String message) {
		super();
		this.articleVendu = articleVendu;
		this.vendeur = vendeur;
		this.oldAcheteur = oldAcheteur;
		this.newAcheteur = newAcheteur;
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

	public Enchere getOldEnchere() {
		return oldEnchere;
	}

	public void setOldEnchere(Enchere oldEnchere) {
		this.oldEnchere = oldEnchere;
	}

	public Utilisateur getOldAcheteur() {
		return oldAcheteur;
	}

	public void setOldAcheteur(Utilisateur oldAcheteur) {
		this.oldAcheteur = oldAcheteur;
	}

	public Utilisateur getNewAcheteur() {
		return newAcheteur;
	}

	public void setNewAcheteur(Utilisateur newAcheteur) {
		this.newAcheteur = newAcheteur;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
