package fr.eni.encheres.ihm.monprofil;

import fr.eni.encheres.bo.Utilisateur;

public class MonProfilModel {

	private Utilisateur utilisateur;
	
	public MonProfilModel() {
	}

	public MonProfilModel(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MonProfilModel [utilisateur=");
		builder.append(utilisateur);
		builder.append("]");
		return builder.toString();
	}
}
