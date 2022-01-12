package fr.eni.encheres.dal.categorie;

public class CategorieDAOFact {
	
	public static CategorieDAO getInstance(){
		return new CategorieDAOJdbc();
	}
}
