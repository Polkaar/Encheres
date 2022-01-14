package fr.eni.encheres.bll.categorie;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public interface CategorieManager {

	public void ajouterCategorie(Categorie categorie) throws BllException;
	public Categorie afficherCategorie(ArticleVendu articleVendu) throws BllException;
	public void supprimerCategorie(Categorie categorie) throws BllException;
	public Categorie afficherCategorieById(Integer noCategorie) throws BllException;
	
}
