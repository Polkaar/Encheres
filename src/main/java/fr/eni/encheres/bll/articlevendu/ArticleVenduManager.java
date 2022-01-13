package fr.eni.encheres.bll.articlevendu;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;

public interface ArticleVenduManager {

	public void ajouterArticleVendu(ArticleVendu articleVendu) throws BllException, DALException;
	public void modifierArticleVendu(ArticleVendu articleVendu) throws BllException, DALException;
	public void supprimerArticleVendu(ArticleVendu articleVendu) throws BllException;
	public Utilisateur afficherArticleVendu(Integer noArticleVendu) throws BllException;
	public List<Utilisateur> afficherTousArticleVendu() throws BllException;
}
