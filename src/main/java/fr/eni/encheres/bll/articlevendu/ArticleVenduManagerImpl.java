package fr.eni.encheres.bll.articlevendu;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.ParameterException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.articlevendu.ArticleVenduDAO;
import fr.eni.encheres.dal.articlevendu.ArticleVenduDAOFact;

public class ArticleVenduManagerImpl implements ArticleVenduManager {
	
	ArticleVenduDAO dao = ArticleVenduDAOFact.getArticleVenduDAO();

	@Override
	public void ajouterArticleVendu(ArticleVendu articleVendu) throws BllException {
		BllException be = new BllException();

		verifNomArticle(articleVendu.getNomArticle(), be);
		verifDescriptionArticle(articleVendu.getDescription(), be);
		verifDateDebutEncheresArticle(articleVendu.getDateDebutEncheres(), be);
		verifDateFinEncheresArticle(articleVendu.getDateFinEncheres(), articleVendu.getDateDebutEncheres(), be);
		verifPrixInitialArticle(articleVendu.getPrixInitial(), be);
		verifEtatVenteArticle(articleVendu.isEtatVente(), be);
		verifCategorieArticle(articleVendu.getCategorie(), be);
		verifRetraitArticle(articleVendu.getRetrait(), be);
		verifUtilisateurArticle(articleVendu.getUtilisateur(), be);
		
		if (be.hasErreur()) {
			throw be;
		}
		try {
			dao.insert(articleVendu);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}	
	}

	@Override
	public void modifierArticleVendu(ArticleVendu articleVendu, Integer nouvelleEnchere) throws BllException {
		BllException be = new BllException();
		verifPrixVente(articleVendu, nouvelleEnchere, be);
		if (be.hasErreur()) {
			throw be;
		}
		try {
			dao.update(articleVendu, nouvelleEnchere);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	

	@Override
	public void supprimerArticleVendu(ArticleVendu articleVendu) throws BllException {
		try {
			dao.delete(articleVendu);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public ArticleVendu afficherArticleVendu(Integer noArticleVendu) throws BllException {
		try {
			return dao.selectById(noArticleVendu);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}    
	}

	@Override
	public List<ArticleVendu> afficherTousArticleVendu() throws BllException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}    
	}
	
	@Override
	public List<ArticleVendu> afficherArticleVenduCategorie(Integer noCategorie) throws BllException {
		try {
			return dao.selectArticleByCategorie(noCategorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}    
	}

	@Override
	public List<ArticleVendu> afficherArticleVenduNomEtCategorie(String nomLike, String noCategorie) throws BllException {
		Integer noCategorieInt = Integer.parseInt(noCategorie);
		try {
			return dao.selectByNomAndCat(nomLike, noCategorieInt);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public List<ArticleVendu> afficherEncheresOuvertes(String nomLike, String noCategorie) throws BllException {
		Integer noCategorieInt = Integer.parseInt(noCategorie);
		try {
			return dao.selectEncheresOuvertesByNomAndCat(nomLike, noCategorieInt);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public List<ArticleVendu> afficherEncheresAcheteur(Utilisateur utilisateur, String nomLike, String noCategorie) throws BllException {
		Integer noCategorieInt = Integer.parseInt(noCategorie);
		Integer noUtilisateur = utilisateur.getNoUtilisateur();
		try {
			return dao.selectEncheresByAcheteurByNomAndCat(noUtilisateur, nomLike, noCategorieInt);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public List<ArticleVendu> afficherEncheresRemportees(Utilisateur utilisateur, String nomLike, String noCategorie) throws BllException {
		Integer noCategorieInt = Integer.parseInt(noCategorie);
		Integer noUtilisateur = utilisateur.getNoUtilisateur();
		try {
			return dao.selectEncheresRemporteesByAcheteurByNomAndCat(noUtilisateur, nomLike, noCategorieInt);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public List<ArticleVendu> afficherVentesEnCours(Utilisateur utilisateur, String nomLike, String noCategorie) throws BllException {
		Integer noCategorieInt = Integer.parseInt(noCategorie);
		Integer noUtilisateur = utilisateur.getNoUtilisateur();
		try {
			return dao.selectVentesEnCoursByVendeurByNomAndCat(noUtilisateur, nomLike, noCategorieInt);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public List<ArticleVendu> afficherVentesNonDebutees(Utilisateur utilisateur, String nomLike, String noCategorie) throws BllException {
		Integer noCategorieInt = Integer.parseInt(noCategorie);
		Integer noUtilisateur = utilisateur.getNoUtilisateur();
		try {
			return dao.selectVentesNonDebuteesByVendeurByNomAndCat(noUtilisateur, nomLike, noCategorieInt);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public List<ArticleVendu> afficherVentesTerminees(Utilisateur utilisateur, String nomLike, String noCategorie) throws BllException {
		Integer noCategorieInt = Integer.parseInt(noCategorie);
		Integer noUtilisateur = utilisateur.getNoUtilisateur();
		try {
			return dao.selectVentesTermineesByVendeurByNomAndCat(noUtilisateur, nomLike, noCategorieInt);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}
	

	private void verifPrixVente(ArticleVendu articleVendu, Integer nouvelleEnchere, BllException be) {
		if (articleVendu.getPrixVente() > nouvelleEnchere || articleVendu.getPrixInitial() > nouvelleEnchere) {
			be.ajouterErreur(new ParameterException("L'enchere doit etre superieure au prix initial et a la precedente enchere"));
		}	
	}
	
	private void verifUtilisateurArticle(Utilisateur utilisateur, BllException be) {
		if(utilisateur == null) {
			be.ajouterErreur(new ParameterException("Il doit y avoir un utilisateur"));
		}
	}

	private void verifRetraitArticle(Retrait retrait, BllException be) {
		if(retrait == null) {
			be.ajouterErreur(new ParameterException("Il doit y avoir un lieu de retrait"));
		}
	}

	private void verifCategorieArticle(Categorie categorie, BllException be) {
		if(categorie == null) {
			be.ajouterErreur(new ParameterException("Il doit y avoir une categorie"));
		}
	}

	private void verifEtatVenteArticle(Boolean etatVente, BllException be) {
		if(etatVente == null) {
			be.ajouterErreur(new ParameterException("L'etat de vente doit etre initialise"));
		}
	}

	private void verifPrixInitialArticle(Integer prixInitial, BllException be) {
		if(prixInitial < 0) {
			be.ajouterErreur(new ParameterException("Le prix initial doit etre superieur a 0"));
		}
	}

	private void verifDateFinEncheresArticle(LocalDate dateFinEncheres, LocalDate dateDebutEnchere, BllException be) {
		if(dateFinEncheres == null || dateFinEncheres.isBefore(dateDebutEnchere)) {
			be.ajouterErreur(new ParameterException("La date de fin d'enchere est obligatoire et doit etre posterieur a la date de debut d'enchere"));
		}
	}

	private void verifDateDebutEncheresArticle(LocalDate dateDebutEncheres, BllException be) {
		if(dateDebutEncheres == null || dateDebutEncheres.isBefore(LocalDate.now())) {
			be.ajouterErreur(new ParameterException("La date de debut d'enchere est obligatoire et doit etre posterieur a la date actuelle"));
		}
	}

	private void verifDescriptionArticle(String description, BllException be) {
		if(description == null || description.isBlank() || description.length() > 300){
			be.ajouterErreur(new ParameterException("La description est obligatoire et doit être <= 30"));
		}
	}

	private void verifNomArticle(String nomArticle, BllException be) {
		if(nomArticle == null || nomArticle.isBlank() || nomArticle.length() > 30){
			be.ajouterErreur(new ParameterException("Le nom de l'article est obligatoire et doit être <= 30"));
		}
	}

}
