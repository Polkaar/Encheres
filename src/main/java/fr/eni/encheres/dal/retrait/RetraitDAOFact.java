package fr.eni.encheres.dal.retrait;

public class RetraitDAOFact {

	public static RetraitDAO getInstance(){
		return new RetraitDAOJdbc();
	}
}
