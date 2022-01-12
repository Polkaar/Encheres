package fr.eni.encheres.dal.categorie;

public class CategorieDAOFact {
	
	public static CategorieDAO getCategorieDAO(){
		return new CategorieDAOJdbc();
	}
}
