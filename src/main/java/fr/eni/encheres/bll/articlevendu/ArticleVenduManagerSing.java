package fr.eni.encheres.bll.articlevendu;

public class ArticleVenduManagerSing {

	private static ArticleVenduManager manager = new ArticleVenduManagerImpl();
	
	public static ArticleVenduManager getInstance() {
		if(manager == null) {
			manager = new ArticleVenduManagerImpl();
		}
		return manager;
	}
}
