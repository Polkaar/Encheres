package fr.eni.encheres.bll.articlevendu;

import java.util.List;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

public interface ArticleVenduManager {

	public void ajouterArticleVendu(ArticleVendu articleVendu) throws BllException;
	public void modifierArticleVendu(ArticleVendu articleVendu, Integer nouvelleEnchere) throws BllException;
	public void supprimerArticleVendu(ArticleVendu articleVendu) throws BllException;
	public ArticleVendu afficherArticleVendu(Integer noArticleVendu) throws BllException;
	public List<ArticleVendu> afficherArticleVenduCategorie(Integer noCategorie) throws BllException;
	public List<ArticleVendu> afficherTousArticleVendu() throws BllException;
	
	/**Méthodes utilisées dans la page AccueilConnecte
	 */
	public List<ArticleVendu> afficherArticleVenduNomEtCategorie(String nomLike, String noCategorie) throws BllException;
	public List<ArticleVendu> afficherEncheresOuvertes(String nomLike, String noCategorie) throws BllException;
	public List<ArticleVendu> afficherEncheresAcheteur(Utilisateur utilisateur, String nomLike, String noCategorie) throws BllException;
	public List<ArticleVendu> afficherEncheresRemportees(Utilisateur utilisateur, String nomLike, String noCategorie) throws BllException;
	public List<ArticleVendu> afficherVentesEnCours(Utilisateur utilisateur, String nomLike, String noCategorie) throws BllException;
	public List<ArticleVendu> afficherVentesNonDebutees(Utilisateur utilisateur, String nomLike, String noCategorie) throws BllException;
	public List<ArticleVendu> afficherVentesTerminees(Utilisateur utilisateur, String nomLike, String noCategorie) throws BllException;
	public void modifierArticleVendu(ArticleVendu articleVendu)throws BllException;
	
}
