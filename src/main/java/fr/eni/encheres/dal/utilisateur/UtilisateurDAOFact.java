package fr.eni.encheres.dal.utilisateur;

public class UtilisateurDAOFact {
	
	public static UtilisateurDAO getInstance() {
		return new UtilisateurDAOJdbc();
	}
}
