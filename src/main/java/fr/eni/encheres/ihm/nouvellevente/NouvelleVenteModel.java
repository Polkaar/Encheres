package fr.eni.encheres.ihm.nouvellevente;

import fr.eni.encheres.bo.Utilisateur;

public class NouvelleVenteModel {
	
	private Utilisateur utilisateur;
	private String message;
	
	public NouvelleVenteModel() {
	}

	public NouvelleVenteModel(Utilisateur utilisateur, String message) {
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
		builder.append("NouvelleVenteModel [utilisateur=");
		builder.append(utilisateur);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
}
