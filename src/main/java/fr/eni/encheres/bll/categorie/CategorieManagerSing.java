package fr.eni.encheres.bll.categorie;

public class CategorieManagerSing {

	private static CategorieManager manager = new CategorieManagerImpl();
	
	public static CategorieManager getInstance() {
		if(manager == null) {
			manager = new CategorieManagerImpl();
		}
		return manager;
	}
}
