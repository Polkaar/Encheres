package fr.eni.encheres.dal.utilisateur;

public class UtilisateurDAOFact {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbc();
	}
}
