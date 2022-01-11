package fr.eni.encheres.dal;

public class ArticleVenduDAOFact {

	public static ArticleVenduDAO getInstance() {
//		return new ParticipantDAOMock();
		return new ArticleVenduJdbcImpl();
	}
}
