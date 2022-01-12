package fr.eni.encheres.dal.articlevendu;

public class ArticleVenduDAOFact {

	public static ArticleVenduDAO getInstance() {
//		return new ParticipantDAOMock();
		return new ArticleVenduDAOJdbc();
	}
}
