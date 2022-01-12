package fr.eni.encheres.bll.retrait;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;

public interface RetraitManager {

	public void ajouterRetrait(Retrait retrait) throws BllException;
	public void modifierRetrait(Retrait retrait) throws BllException;
	public void supprimerRetrait(Retrait retrait) throws BllException, DALException;
	public Retrait afficherRetrait(ArticleVendu article) throws BllException;
	
}
