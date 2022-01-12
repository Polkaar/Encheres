package fr.eni.encheres.dal.enchere;

public class EnchereDAOFact{
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbc();
	}
}
