package fr.eni.encheres.ihm.admin.monProfilAdmin;

import fr.eni.encheres.bo.Utilisateur;

public class MonProfilAdminModel {

	private Utilisateur utilisateur;
	
	public MonProfilAdminModel() {
	}

	public MonProfilAdminModel(Utilisateur utilisateur) {
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
