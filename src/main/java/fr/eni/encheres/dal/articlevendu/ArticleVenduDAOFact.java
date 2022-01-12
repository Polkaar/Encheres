package fr.eni.encheres.dal.articlevendu;

public class ArticleVenduDAOFact {

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOJdbc();
	}
}
