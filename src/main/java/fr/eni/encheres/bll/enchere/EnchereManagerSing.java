package fr.eni.encheres.bll.enchere;

public class EnchereManagerSing {

	private static EnchereManager manager = new EnchereManagerImpl();
	
	public static EnchereManager getInstance() {
		if(manager == null) {
			manager = new EnchereManagerImpl();
		}
		return manager;
	}
}
