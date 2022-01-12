package fr.eni.encheres.bll.utilisateur;

public class UtilisateurManagerSing {

	private static UtilisateurManager manager = new UtilisateurManagerImpl();
	
	public static UtilisateurManager getInstance() {
		if(manager == null) {
			manager = new UtilisateurManagerImpl();
		}
		return manager;
	}
}
