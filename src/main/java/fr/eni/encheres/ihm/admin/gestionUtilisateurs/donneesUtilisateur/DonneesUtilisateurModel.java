package fr.eni.encheres.ihm.admin.gestionUtilisateurs.donneesUtilisateur;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

public class DonneesUtilisateurModel {

	private Utilisateur utilisateur;
	Integer nbEncheres;
	Integer nbVentes;
	List<ArticleVendu> lstArticles = new ArrayList<>();
	
	
	public DonneesUtilisateurModel() {
		super();
	}

	public DonneesUtilisateurModel(Utilisateur utilisateur, Integer nbEncheres, Integer nbVentes,
			List<ArticleVendu> lstArticles) {
		super();
		this.utilisateur = utilisateur;
		this.nbEncheres = nbEncheres;
		this.nbVentes = nbVentes;
		this.lstArticles = lstArticles;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Integer getNbEncheres() {
		return nbEncheres;
	}

	public void setNbEncheres(Integer nbEncheres) {
		this.nbEncheres = nbEncheres;
	}

	public Integer getNbVentes() {
		return nbVentes;
	}

	public void setNbVentes(Integer nbVentes) {
		this.nbVentes = nbVentes;
	}

	public List<ArticleVendu> getLstArticles() {
		return lstArticles;
	}

	public void setLstArticles(List<ArticleVendu> lstArticles) {
		this.lstArticles = lstArticles;
	}

	@Override
	public String toString() {
		return "DonneesUtilisateurModel [utilisateur=" + utilisateur + ", nbEncheres=" + nbEncheres + ", nbVentes="
				+ nbVentes + ", lstArticles=" + lstArticles + "]";
	}
	
	
}
