package fr.eni.encheres.ihm.profilutilisateur;

import fr.eni.encheres.bo.Utilisateur;

public class ProfilUtilisateurModel {
	
	private Utilisateur utilisateur;
	
	public ProfilUtilisateurModel() {
	}

	public ProfilUtilisateurModel(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	
}
