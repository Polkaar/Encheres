package fr.eni.encheres.bll.retrait;

import fr.eni.encheres.bll.BllException;
import fr.eni.encheres.bll.ParameterException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.retrait.RetraitDAO;
import fr.eni.encheres.dal.retrait.RetraitDAOFact;
import fr.eni.encheres.dal.DALException;

public class RetraitManagerImpl implements RetraitManager {
	
	RetraitDAO dao = RetraitDAOFact.getInstance();
	

	@Override
	public void ajouterRetrait(Retrait retrait) throws BllException {
		BllException be = new BllException();
		
		verifAll(retrait.getRue(), retrait.getCodePostal(), retrait.getVille(), be);
		
		if (be.hasErreur()) {
			throw be;
		}
		
		try {
			dao.insert(retrait);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public void modifierRetrait(Retrait retrait) throws BllException {
		BllException be = new BllException();
		
		verifAll(retrait.getRue(), retrait.getCodePostal(), retrait.getVille(), be);
		
		if (be.hasErreur()) {
			throw be;
		}
		
		try {
			dao.update(retrait);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public void supprimerRetrait(Retrait retrait) throws BllException, DALException {
		try {
			dao.delete(retrait);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BllException(e);
		}
	}

	@Override
	public Integer afficherRetrait(ArticleVendu article) throws BllException {
		return article.getRetrait().getNoRetrait();
	}

	
	
	private void verifAll(String rue, String codePostal, String ville, BllException be)
			throws BllException {
		verifRue(rue, be);
		verifCodePostal(codePostal, be);
		verifVille(ville, be);

		if (be.hasErreur()) {
			throw be;
		}
	}

	private void verifRue(String rue, BllException be) throws BllException {
		if (rue == null || rue.isBlank() || rue.length() > 30) {
			be.ajouterErreur(new ParameterException("La rue est obligatoire et doit être <= 30"));
		}
	}
	
	private void verifCodePostal(String codePostal, BllException be) throws BllException {
		if (codePostal == null || codePostal.isBlank() || codePostal.length() > 15) {
			be.ajouterErreur(new ParameterException("Le code postal est obligatoire et doit être <= 15"));
		}
	}
	
	private void verifVille(String ville, BllException be) throws BllException {
		if (ville == null || ville.isBlank() || ville.length() > 30) {
			be.ajouterErreur(new ParameterException("La ville est obligatoire et doit être <= 30"));
		}
	}
	
	
}
