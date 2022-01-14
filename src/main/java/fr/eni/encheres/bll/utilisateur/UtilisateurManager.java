package fr.eni.encheres.bll.utilisateur;

import java.util.List;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;

public interface UtilisateurManager {

	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) throws BllException, DALException;
	public void modifierUtilisateur(Utilisateur utilisateur) throws BllException, DALException;
	public void supprimerUtilisateur(Utilisateur utilisateur) throws BllException;
	public Utilisateur afficherUtilisateur(Integer noUtilisateur) throws BllException; //TODO : Que mettre en paramètre ?
	public Utilisateur afficherUtilisateurParPseudo(String pseudo) throws BllException;
	public List<Utilisateur> afficherTousUtilisateurs() throws BllException;

}
