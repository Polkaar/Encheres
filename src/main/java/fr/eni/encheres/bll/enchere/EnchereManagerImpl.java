package fr.eni.encheres.bll.enchere;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.ParameterException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.enchere.EnchereDAO;
import fr.eni.encheres.dal.enchere.EnchereDAOFact;

public class EnchereManagerImpl implements EnchereManager {

	EnchereDAO dao = EnchereDAOFact.getEnchereDAO();

	@Override
	public void ajouterEnchere(Enchere enchere) throws BllException, DALException {
		BllException be = new BllException();
		
		verifAll(enchere.getDateEnchere(), enchere.getMontantEnchere(), enchere.getArticleVendu(), enchere.getUtilisateur(), be);
		
		if (be.hasErreur()) {
			throw be;
		}
		
		dao.insert(enchere);
	}

	@Override
	public void supprimerEnchere(Enchere enchere) throws BllException {
		try {
			dao.delete(enchere);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public List<Enchere> afficherEnchereUtilisateur(Utilisateur utilisateur) throws BllException {
		try {
			return dao.selectByUtilisateur(utilisateur.getNoUtilisateur());
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public List<Enchere> afficherEnchereArticle(ArticleVendu article) throws BllException {
		try {
			return dao.selectByArticle(article.getNoArticle());
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}
	
	@Override
	public List<Enchere> afficherTousEnchere() throws BllException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public Enchere selectDerniereEnchere(Integer noArticle) throws BllException {
		try {
			return dao.selectDerniereEnchere(noArticle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	private void verifAll(LocalDate dateEnchere, Integer montantEnchere, ArticleVendu article, Utilisateur utilisateur, BllException be)
			throws BllException {
		verifDateEnchere(dateEnchere, be);
		verifMontantEnchere(montantEnchere, be);
		verifArticleVendu(article, be);
		verifUtilisateur(utilisateur, be);

		if (be.hasErreur()) {
			throw be;
		}
	}

	private void verifDateEnchere(LocalDate dateEnchere, BllException be) throws BllException {
		if (dateEnchere == null) {
			be.ajouterErreur(new ParameterException("La date de l'enchère est obligatoire."));
		}
	}
	
	private void verifMontantEnchere(Integer montantEnchere, BllException be) throws BllException {
		if (montantEnchere == null) {
			be.ajouterErreur(new ParameterException("Le montant de l'enchère est obligatoire."));
		}
	}
	
	private void verifArticleVendu(ArticleVendu article, BllException be) throws BllException {
		if (article.getNoArticle() == null) {
			be.ajouterErreur(new ParameterException("Le numéro de l'article lié à l'enchère est obligatoire."));
		}
	}
	
	private void verifUtilisateur(Utilisateur utilisateur, BllException be) throws BllException {
		if (utilisateur.getNoUtilisateur() == null) {
			be.ajouterErreur(new ParameterException("Le numéro utilisateur de l'acheteur est obligatoire."));
		}
	}

	

	
	
}
