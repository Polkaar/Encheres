package fr.eni.encheres.dal.enchere;

public class EnchereDAOFact{
	public static EnchereDAO getInstance() {
//		return new ParticipantDAOMock();
		return new EnchereDAOJdbc();
	}

}
