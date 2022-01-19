package fr.eni.encheres.ihm.achatCredit;

import fr.eni.encheres.bo.Utilisateur;

public class AchatCreditModel {

	private Utilisateur utilisateur;

	public AchatCreditModel() {
		super();
	}

	public AchatCreditModel(Utilisateur utilisateur) {
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
		return "AchatCreditModel [utilisateur=" + utilisateur + "]";
	}
	
	
}
