package fr.eni.encheres.ihm.admin.gestionUtilisateurs.listeUtilisateurs;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public class ListeUtilisateursModel {

	private Utilisateur utilisateur;
	private Integer nbEncheres;
	private Integer nbVentes;
	
	private List<ListeUtilisateursModel> lstUsersDonnees = new ArrayList<>();
	
	public void addlstUsersDonnees (ListeUtilisateursModel listeUtilisateurModel) {
		lstUsersDonnees.add(listeUtilisateurModel);
	}
	
	public ListeUtilisateursModel() {
		super();
	}
	public ListeUtilisateursModel(Utilisateur utilisateur, Integer nbEncheres, Integer nbVentes) {
		super();
		this.utilisateur = utilisateur;
		this.nbEncheres = nbEncheres;
		this.nbVentes = nbVentes;
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


	public List<ListeUtilisateursModel> getLstUsersDonnees() {
		return lstUsersDonnees;
	}

	public void setLstUsersDonnees(List<ListeUtilisateursModel> lstUsersDonnees) {
		this.lstUsersDonnees = lstUsersDonnees;
	}

	@Override
	public String toString() {
		return "ListeUtilisateursModel [utilisateur=" + utilisateur + ", nbEncheres=" + nbEncheres + ", nbVentes="
				+ nbVentes + ", lstUsersDonnees=" + lstUsersDonnees + "]";
	}
	
	
}
