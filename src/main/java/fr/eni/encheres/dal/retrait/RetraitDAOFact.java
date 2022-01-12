package fr.eni.encheres.dal.retrait;

public class RetraitDAOFact {

	public static RetraitDAO getRetraitDAO(){
		return new RetraitDAOJdbc();
	}
}
