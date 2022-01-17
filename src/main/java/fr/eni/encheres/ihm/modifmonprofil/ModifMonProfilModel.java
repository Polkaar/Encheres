package fr.eni.encheres.ihm.modifmonprofil;

import fr.eni.encheres.bo.Utilisateur;

public class ModifMonProfilModel {

	private Utilisateur utilisateur;
	private String message;
	
	public ModifMonProfilModel() {
	}

public ModifMonProfilModel(Utilisateur utilisateur, String message) {
		super();
		this.utilisateur = utilisateur;
		this.message = message;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModifMonProfilModel [utilisateur=");
		builder.append(utilisateur);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
}
