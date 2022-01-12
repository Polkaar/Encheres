package fr.eni.encheres.bll.retrait;

public class RetraitManagerSing {

	private static RetraitManager manager = new RetraitManagerImpl();
	
	public static RetraitManager getInstance() {
		if(manager == null) {
			manager = new RetraitManagerImpl();
		}
		return manager;
	}
}
