package fr.eni.encheres.bll.articlevendu;

import java.util.List;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;

public interface ArticleVenduManager {

	public void ajouterArticleVendu(ArticleVendu articleVendu) throws DALException, BllException;
	public void modifierArticleVendu(ArticleVendu articleVendu, Integer nouvelleEnchere) throws BllException, DALException;
	public void supprimerArticleVendu(ArticleVendu articleVendu) throws BllException;
	public ArticleVendu afficherArticleVendu(Integer noArticleVendu) throws BllException, DALException;
	public List<ArticleVendu> afficherArticleVenduCategorie(Integer noCategorie) throws BllException, DALException;
	public List<ArticleVendu> afficherTousArticleVendu() throws BllException, DALException;
}
