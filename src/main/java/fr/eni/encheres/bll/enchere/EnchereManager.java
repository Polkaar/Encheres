package fr.eni.encheres.bll.enchere;

import java.util.List;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;

public interface EnchereManager {

	public void ajouterEnchere(Enchere enchere) throws BllException, DALException;
	public void supprimerEnchere(Enchere enchere) throws BllException;
	public List<Enchere> afficherEnchereUtilisateur(Utilisateur utilisateur) throws BllException;
	public List<Enchere> afficherEnchereArticle(ArticleVendu article) throws BllException;
	public List<Enchere> afficherTousEnchere() throws BllException;
	Enchere selectDerniereEnchere(Integer noArticle) throws BllException;
	
}
